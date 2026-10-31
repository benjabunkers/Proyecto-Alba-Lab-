package com.example.app_corpaliv_grupo12.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_corpaliv_grupo12.viewmodel.ModoTema
import com.example.app_corpaliv_grupo12.viewmodel.TemaViewModel

@Composable
fun SettingsScreen(viewModel: TemaViewModel) {
    val modo by viewModel.modo.collectAsState()
    Column(Modifier.fillMaxSize().padding(22.dp)) {
        Text("Apariencia", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Elige cómo se muestra la aplicación.", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 5.dp, bottom = 12.dp))
        ModoTema.entries.forEach { option -> Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) { RadioButton(selected = modo == option, onClick = { viewModel.cambiarModo(option) }); Text(option.titulo) } }
    }
}
