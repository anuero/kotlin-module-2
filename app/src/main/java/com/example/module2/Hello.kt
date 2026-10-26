package com.example.module2

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Hello(name: String?, modifier: Modifier = Modifier) {
    // если имя пришло null показываем заглушку
    Text(
        text = if (name != null) "Привет, $name!" else "Имя не задано",
        modifier = modifier
    )
}
