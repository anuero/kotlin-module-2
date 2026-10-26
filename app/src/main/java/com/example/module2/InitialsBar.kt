package com.example.module2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InitialsBar(initials: String, modifier: Modifier = Modifier) {
    // серый контейнер на всю ширину высотой 100dp
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        // круг по центру с отступами 16dp сверху и снизу
        Box(
            modifier = Modifier
                .padding(vertical = 16.dp)
                // занимает всю доступную высоту
                .fillMaxHeight()
                // ширина равна высоте поэтому получается круг
                .aspectRatio(1f)
                .background(Color(0xFFFF5722), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = initials)
        }
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun InitialsBarPreview() {
    // отступ сверху нужен потому что в превью строка состояния рисуется поверх контента
    Box(modifier = Modifier.padding(top = 32.dp)) {
        InitialsBar(initials = "НС")
    }
}
