package com.example.module2

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// тема приложения с синим основным цветом как у трекера воды
@Composable
fun Mod2Theme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF415F91),
            background = Color(0xFFFAFAFA),
            surface = Color(0xFFFAFAFA)
        ),
        content = content
    )
}
