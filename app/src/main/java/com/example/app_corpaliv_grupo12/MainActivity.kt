package com.example.app_corpaliv_grupo12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_corpaliv_grupo12.navigation.AppNavigation
import com.example.app_corpaliv_grupo12.ui.theme.AlbaTheme
import com.example.app_corpaliv_grupo12.viewmodel.ModoTema
import com.example.app_corpaliv_grupo12.viewmodel.TemaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val temaViewModel: TemaViewModel = viewModel()
            val modo by temaViewModel.modo.collectAsState()
            val oscuro = when (modo) {
                ModoTema.SISTEMA -> isSystemInDarkTheme()
                ModoTema.CLARO -> false
                ModoTema.OSCURO -> true
            }
            AlbaTheme(darkTheme = oscuro) { AppNavigation(temaViewModel) }
        }
    }
}
