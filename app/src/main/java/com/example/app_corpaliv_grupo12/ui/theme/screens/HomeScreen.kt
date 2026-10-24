package com.example.app_corpaliv_grupo12.ui.theme.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Bienvenido a CORPALIV / Alba Lab", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun AboutScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Sobre Nosotros - Misión y Propósito", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun CatalogScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Catálogo de Productos y Servicios", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun StoriesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Historias de Éxito e Impacto Social", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun ContactScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Formulario de Contacto y Alianzas", style = MaterialTheme.typography.headlineMedium)
    }
}