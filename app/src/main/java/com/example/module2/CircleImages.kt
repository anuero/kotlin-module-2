package com.example.module2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// круг исходного размера в правом верхнем углу черного компонента
@Composable
fun CircleTopEnd(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(240.dp, 120.dp)
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopEnd)
        )
    }
}

// синий фон круг по центру растянут и перекрашен в фиолетовый
@Composable
fun CirclePurpleStretched(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(240.dp, 120.dp)
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            // растягиваем по размерам контейнера без сохранения пропорций
            contentScale = ContentScale.FillBounds,
            // цвет круга меняем через colorFilter
            colorFilter = ColorFilter.tint(Color(0xFF800080))
        )
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun CircleTopEndPreview() {
    // отступ сверху нужен потому что в превью строка состояния рисуется поверх контента
    Box(modifier = Modifier.padding(top = 32.dp)) {
        CircleTopEnd()
    }
}

@Preview(name = "portrait", showSystemUi = true)
@Composable
fun CirclePurpleStretchedPreview() {
    // отступ сверху нужен потому что в превью строка состояния рисуется поверх контента
    Box(modifier = Modifier.padding(top = 32.dp)) {
        CirclePurpleStretched()
    }
}
