package com.example.app_corpaliv_grupo12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.app_corpaliv_grupo12.ui.theme.App_Corpaliv_Grupo12Theme
import com.example.app_corpaliv_grupo12.ui.theme.screens.InstitucionalScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_Corpaliv_Grupo12Theme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    InstitucionalScreen()

                }
            }
        }
    }
}