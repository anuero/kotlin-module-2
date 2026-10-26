package com.example.module2

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.shadows.ShadowToast

// проверка исправлений задания 10 свойства а не реализация
@RunWith(AndroidJUnit4::class)
class ShoppingCartTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    private fun add() = rule.onNodeWithText("Добавить товар").performClick()

    @Test
    fun sumIsNeverInflatedByRecomposition() {
        rule.setContent { ShoppingCartScreen(newPrice = { 10 }) }
        rule.onNodeWithText("Товаров на сумму: 306 рублей").assertIsDisplayed()
        add()
        rule.onNodeWithText("Товаров на сумму: 316 рублей").assertIsDisplayed()
        add()
        rule.onNodeWithText("Товаров на сумму: 326 рублей").assertIsDisplayed()
    }

    @Test
    fun toastOnlyWhenSumAbove500() {
        rule.setContent { ShoppingCartScreen(newPrice = { 100 }) }
        add() // 406
        assertNull(ShadowToast.getLatestToast())
        add() // 506
        assertEquals("Доставка бесплатная!", ShadowToast.getTextOfLatestToast())
    }

    @Test
    fun toastIsNotShownWithoutClick() {
        rule.setContent { ShoppingCartScreen(newPrice = { 300 }) }
        // стартовая сумма 306 меньше 500 и тоста нет
        assertNull(ShadowToast.getLatestToast())
        add() // 606
        rule.onNodeWithText("Товаров на сумму: 606 рублей").assertIsDisplayed()
        assertEquals(1, ShadowToast.shownToastCount())
        // перерисовка без нажатия новых тостов не рождает
        rule.waitForIdle()
        assertEquals(1, ShadowToast.shownToastCount())
    }

    @Test
    fun removeButtonOnlyWithProductsAndRemovesLast() {
        rule.setContent { ShoppingCartScreen(newPrice = { 10 }) }
        rule.onNodeWithText("Удалить товар").assertIsDisplayed()
        rule.onNodeWithText("Удалить товар").performClick()
        rule.onNodeWithText("Товар #3 - 56 рублей").assertDoesNotExist()
        rule.onNodeWithText("Товаров на сумму: 250 рублей").assertIsDisplayed()
        rule.onNodeWithText("Удалить товар").performClick()
        rule.onNodeWithText("Удалить товар").performClick()
        rule.onNodeWithText("Товаров на сумму: 0 рублей").assertIsDisplayed()
        rule.onNodeWithText("Удалить товар").assertDoesNotExist()
        add()
        rule.onNodeWithText("Удалить товар").assertIsDisplayed()
    }
}
