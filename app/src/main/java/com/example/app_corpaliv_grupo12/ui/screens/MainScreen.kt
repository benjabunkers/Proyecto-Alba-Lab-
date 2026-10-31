package com.example.app_corpaliv_grupo12.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_corpaliv_grupo12.navigation.AppNavigation
import com.example.app_corpaliv_grupo12.viewmodel.TemaViewModel

@Composable
fun MainScreen(temaViewModel: TemaViewModel = viewModel()) {
    AppNavigation(temaViewModel)
}
