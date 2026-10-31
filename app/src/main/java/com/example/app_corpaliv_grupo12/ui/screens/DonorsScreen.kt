package com.example.app_corpaliv_grupo12.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_corpaliv_grupo12.model.ContactStatus
import com.example.app_corpaliv_grupo12.model.ContactoDonador

@Composable
fun DonorsScreen(contactos: List<ContactoDonador>, onUpdate: (ContactoDonador, ContactStatus, String) -> Unit) {
    val context = LocalContext.current
    var query by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf<ContactoDonador?>(null) }
    val filtered = contactos.filter { "${it.nombre} ${it.correo} ${it.telefono}".contains(query, ignoreCase = true) }
    Column(Modifier.fillMaxSize().padding(horizontal = 18.dp)) {
        Text("Directorio de donadores", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 14.dp))
        OutlinedTextField(query, { query = it }, placeholder = { Text("Buscar nombre, correo o teléfono") }, leadingIcon = { Icon(Icons.Default.Search, null) }, singleLine = true, modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp), shape = RoundedCornerShape(14.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(filtered, key = { it.id }) { donor ->
                Card(onClick = { selected = donor }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
                    Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                        androidx.compose.material3.Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = CircleShape) { Text(donor.nombre.split(' ').take(2).joinToString("") { it.first().uppercase() }, modifier = Modifier.padding(12.dp), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold) }
                        Column(Modifier.weight(1f).padding(start = 11.dp)) {
                            Text(donor.nombre, fontWeight = FontWeight.Bold)
                            Text(donor.correo, style = MaterialTheme.typography.bodySmall)
                            Text(donor.telefono, style = MaterialTheme.typography.bodySmall)
                            Text(donor.status().label, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelMedium)
                            if (donor.observacion.isNotBlank()) Text("Nota: ${donor.observacion}", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
    selected?.let { donor ->
        var status by remember(donor) { mutableStateOf(donor.status()) }
        var note by remember(donor) { mutableStateOf(donor.observacion) }
        AlertDialog(onDismissRequest = { selected = null }, title = { Text(donor.nombre, fontWeight = FontWeight.Bold) }, text = {
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(donor.correo)
                Text(donor.telefono, color = MaterialTheme.colorScheme.primary)
                OutlinedButton(onClick = { context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${donor.telefono}"))) }) { Text("Llamar") }
                Text("Estado del contacto", fontWeight = FontWeight.SemiBold)
                ContactStatus.entries.forEach { option ->
                    Row(Modifier.fillMaxWidth().clickable { status = option }, verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(status == option, onClick = { status = option })
                        Text(option.label)
                    }
                }
                OutlinedTextField(note, { note = it }, label = { Text("Observaciones") }, minLines = 2, modifier = Modifier.fillMaxWidth())
            }
        }, confirmButton = { TextButton(onClick = { onUpdate(donor, status, note); selected = null }) { Text("Guardar") } }, dismissButton = { TextButton(onClick = { selected = null }) { Text("Cancelar") } })
    }
}
