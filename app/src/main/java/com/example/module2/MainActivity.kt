package com.example.module2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mod2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // вызываем hello с именем
                    Hello(name = "Android", modifier = Modifier.padding(innerPadding))
                    // для проверки null заменяем строку выше на Hello(name = null)
                }
            }
        }
    }
}
