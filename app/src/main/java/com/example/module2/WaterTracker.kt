package com.example.module2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val primaryColor = Color(0xFF415F91)
private val secondaryColor = Color(0xFF565F71)
private val buttonTextColor = Color.White

private const val GLASS_ML = 250
private const val DAILY_GOAL_ML = 1500

class WaterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod2Theme {
                WaterTracker()
            }
        }
    }
}

@Composable
fun WaterTracker() {
    // remember был забыт и состояние не жило между рекомпозициями
    // rememberSaveable еще и переживает поворот экрана
    var waterCount by rememberSaveable { mutableIntStateOf(0) }
    // новый стейт сколько дней подряд выпито 1500 мл и больше
    var daysStreak by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAFAFA))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            // у этого текста не было параметра text
            text = "$waterCount мл",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryColor
        )
        // у height не было значения
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            // в material3 цвета задаются через buttonColors и containerColor
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            // раньше было waterCount = 250 и счетчик не рос
            onClick = { waterCount += GLASS_ML }
        ) {
            Text(
                text = "+250 мл",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Дней подряд с нормой: $daysStreak",
            fontSize = 20.sp,
            color = secondaryColor
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = {
                // норма выполнена серия растет иначе обнуляется
                daysStreak = if (waterCount >= DAILY_GOAL_ML) daysStreak + 1 else 0
                // новый день начинаем с нуля
                waterCount = 0
            }
        ) {
            Text(
                text = "Завершить день",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun WaterTrackerPreview() {
    WaterTracker()
}
