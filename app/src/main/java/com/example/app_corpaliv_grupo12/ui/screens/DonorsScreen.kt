package com.example.app_corpaliv_grupo12.ui.screens

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_corpaliv_grupo12.model.ContactStatus
import com.example.app_corpaliv_grupo12.model.ContactoDonador
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
                        ContactAvatar(donor.nombre, donor.fotoUri, Modifier.size(48.dp))
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
        var photoUri by remember(donor) { mutableStateOf(donor.fotoUri) }
        val photoPicker = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                runCatching {
                    context.contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                photoUri = uri.toString()
            }
        }
        AlertDialog(onDismissRequest = { selected = null }, title = { Text(donor.nombre, fontWeight = FontWeight.Bold) }, text = {
            Column(Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ContactAvatar(donor.nombre, photoUri, Modifier.size(84.dp).align(Alignment.CenterHorizontally))
                OutlinedButton(onClick = {
                    photoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(if (photoUri == null) "Añadir foto" else "Cambiar foto")
                }
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
        }, confirmButton = { TextButton(onClick = { onUpdate(donor.copy(fotoUri = photoUri), status, note); selected = null }) { Text("Guardar") } }, dismissButton = { TextButton(onClick = { selected = null }) { Text("Cancelar") } })
    }
}

@Composable
private fun ContactAvatar(name: String, photoUri: String?, modifier: Modifier = Modifier.size(48.dp)) {
    val context = LocalContext.current
    val photo by produceState<ImageBitmap?>(null, photoUri) {
        value = if (photoUri.isNullOrBlank()) null else withContext(Dispatchers.IO) { loadContactPhoto(context, photoUri) }
    }
    if (photo != null) {
        Image(photo!!, contentDescription = "Foto de $name", modifier = modifier.clip(CircleShape), contentScale = ContentScale.Crop)
    } else {
        Surface(modifier = modifier, color = MaterialTheme.colorScheme.primaryContainer, shape = CircleShape) {
            Box(contentAlignment = Alignment.Center) {
                Text(name.split(' ').take(2).mapNotNull { it.firstOrNull()?.uppercaseChar() }.joinToString(""), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)
            }
        }
    }
}

private fun loadContactPhoto(context: android.content.Context, photoUri: String): ImageBitmap? = runCatching {
    val uri = Uri.parse(photoUri)
    val resolver = context.contentResolver
    val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
    resolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it, null, bounds) }
    var sample = 1
    while (bounds.outWidth / sample > 320 || bounds.outHeight / sample > 320) sample *= 2
    val options = BitmapFactory.Options().apply { inSampleSize = sample.coerceAtLeast(1) }
    resolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it, null, options)?.asImageBitmap() }
}.getOrNull()

