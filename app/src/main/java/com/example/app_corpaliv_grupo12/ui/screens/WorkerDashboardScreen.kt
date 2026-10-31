package com.example.app_corpaliv_grupo12.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_corpaliv_grupo12.model.ContactStatus
import com.example.app_corpaliv_grupo12.model.ContactoDonador
import com.example.app_corpaliv_grupo12.viewmodel.DonacionUiState

@Composable
fun WorkerDashboardScreen(state: DonacionUiState, onDonors: () -> Unit) {
    val pending = state.contactos.count { it.status() == ContactStatus.PENDING }
    val donations = state.contactos.count { it.status() == ContactStatus.DONATED }
    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(15.dp)) {
        Text("Hola!, ${state.nombre.substringBefore(' ').ifBlank { "equipo" }} 👋", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Resumen del seguimiento de donadores.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Metric("${state.contactos.size}", "Contactos", Modifier.weight(1f))
            Metric("$pending", "Pendientes", Modifier.weight(1f))
            Metric("$donations", "Donaron", Modifier.weight(1f))
        }
        Text("Seguimiento reciente", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        state.contactos.take(3).forEach { contact ->
            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                Column(Modifier.fillMaxWidth().padding(15.dp)) {
                    Text(contact.nombre, fontWeight = FontWeight.Bold)
                    Text(contact.correo, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text("Estado: ${contact.status().label}", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelLarge)
                }
            }
        }
        Button(onClick = onDonors, modifier = Modifier.fillMaxWidth()) { Text("Ver lista de donadores") }
        Text("Los contactos precargados son datos de demostración.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun Metric(value: String, label: String, modifier: Modifier = Modifier) {
    Card(modifier, shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Column(Modifier.padding(12.dp)) { Text(value, style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold); Text(label, style = MaterialTheme.typography.bodySmall) }
    }
}
