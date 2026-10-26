package com.example.module2

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SampleButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        // растягиваем кнопку по ширине экрана
        modifier = modifier.fillMaxWidth(),
        // прямоугольник со скругленными углами
        shape = RoundedCornerShape(16.dp),
        // серая граница
        border = BorderStroke(2.dp, Color.Gray),
        // тень глубиной 4dp
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        // светло серый фон и черный текст
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Text(text = "Нажми на меня", fontSize = 18.sp)
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun SampleButtonPreview() {
    // отступ сверху нужен потому что в превью строка состояния рисуется поверх контента
    Box(modifier = Modifier.padding(top = 32.dp)) {
        SampleButton()
    }
}
