package com.example.module2

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WaterTrackerUiTest {

    // правило дает доступ к activity и позволяет задать контент
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private fun addGlasses(count: Int) {
        repeat(count) { composeTestRule.onNodeWithText("+250 мл").performClick() }
    }

    // 1 кнопка добавления есть и увеличивает счетчик
    @Test
    fun addButtonExistsAndAddsWater() {
        composeTestRule.setContent { WaterTracker() }

        composeTestRule.onNodeWithText("+250 мл").assertIsDisplayed().assertIsEnabled()
        composeTestRule.onNodeWithText("0 мл").assertIsDisplayed()

        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("250 мл").assertIsDisplayed()

        composeTestRule.onNodeWithText("+250 мл").performClick()
        composeTestRule.onNodeWithText("500 мл").assertIsDisplayed()
    }

    // 2 кнопка завершить день есть и сбрасывает счетчик воды
    @Test
    fun finishDayButtonExistsAndResetsWater() {
        composeTestRule.setContent { WaterTracker() }

        composeTestRule.onNodeWithText("Завершить день")
            .assertIsDisplayed().assertIsEnabled()

        addGlasses(2)
        composeTestRule.onNodeWithText("500 мл").assertIsDisplayed()

        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("0 мл").assertIsDisplayed()
    }

    // 3 текст с количеством дней подряд меняется по правилам
    @Test
    fun streakTextShowsDaysInARow() {
        composeTestRule.setContent { WaterTracker() }

        composeTestRule.onNodeWithText("Дней подряд с нормой: 0")
            .assertIsDisplayed()

        // день 1 ровно 1500 мл значит серия 1
        addGlasses(6)
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("Дней подряд с нормой: 1")
            .assertIsDisplayed()

        // день 2 тоже норма значит серия 2
        addGlasses(7)
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("Дней подряд с нормой: 2")
            .assertIsDisplayed()

        // день 3 меньше нормы значит серия обнуляется
        addGlasses(5)
        composeTestRule.onNodeWithText("Завершить день").performClick()
        composeTestRule.onNodeWithText("Дней подряд с нормой: 0")
            .assertIsDisplayed()
    }
}
