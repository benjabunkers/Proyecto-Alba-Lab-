package com.example.app_corpaliv_grupo12.ui.theme.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Bienvenido a CORPALIV / Alba Lab")
                }
            )
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            InstitucionalContent()
        }
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