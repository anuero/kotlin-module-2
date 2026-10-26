package com.example.module2

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

// три макета для одной функции с системным ui
@Preview(
    name = "portrait",
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,orientation=portrait"
)
@Preview(
    name = "landscape",
    showSystemUi = true,
    device = "spec:width=891dp,height=411dp,orientation=landscape"
)
@Preview(
    name = "round",
    showSystemUi = true,
    // круглый дисплей 200 на 200 и желтый фон
    device = "spec:width=200dp,height=200dp,dpi=320,isRound=true",
    showBackground = true,
    backgroundColor = 0xFFFFFF00
)
@Composable
fun HelloPreview() {
    Hello(
        name = "Android",
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}
