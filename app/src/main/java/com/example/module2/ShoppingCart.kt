package com.example.module2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlin.random.Random

data class Product(
    val id: Int,
    val name: String,
    val price: Int
)

class ShoppingCartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mod2Theme {
                ShoppingCartScreen()
            }
        }
    }
}

// newPrice вынесен параметром чтобы в тесте цена не была случайной
@Composable
fun ShoppingCartScreen(newPrice: () -> Int = { Random.nextInt(0, 100) }) {
    val context = LocalContext.current
    var products by remember {
        mutableStateOf(
            listOf(
                Product(0, "Товар #1", 100),
                Product(1, "Товар #2", 150),
                Product(2, "Товар #3", 56)
            )
        )
    }
    // сумма считается из списка заново а не копится в поле activity
    // раньше totalSum += price шел прямо в composable и рос при каждой рекомпозиции
    val totalSum = products.sumOf { it.price }

    Column(modifier = Modifier.padding(top = 48.dp, start = 16.dp)) {
        for (product in products) {
            Text(text = product.name + " - " + product.price + " рублей")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Товаров на сумму: $totalSum рублей")
        AddProductSection(
            onAdd = {
                val updated = products + Product(
                    id = products.size,
                    name = "Товар #${products.size + 1}",
                    price = newPrice()
                )
                products = updated
                // тост показываем в обработчике нажатия а не во время композиции
                if (updated.sumOf { it.price } > 500) {
                    Toast.makeText(context, "Доставка бесплатная!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
        // кнопка удаления есть только если в корзине что то лежит
        if (products.isNotEmpty()) {
            RemoveProductSection(
                onRemove = { products = products.dropLast(1) }
            )
        }
    }
}

@Composable
fun AddProductSection(onAdd: () -> Unit) {
    Button(onClick = onAdd) {
        Text(text = "Добавить товар")
    }
}

@Composable
fun RemoveProductSection(onRemove: () -> Unit) {
    Button(onClick = onRemove) {
        Text(text = "Удалить товар")
    }
}
