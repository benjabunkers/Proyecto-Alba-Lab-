package com.example.app_corpaliv_grupo12.navigation

sealed class Screen {
    object Home : Screen()
    object About : Screen()
    object Catalog : Screen()
    object Stories : Screen()
    object Contact : Screen()
}