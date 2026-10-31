package com.example.app_corpaliv_grupo12.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_corpaliv_grupo12.R
import com.example.app_corpaliv_grupo12.model.sampleCampaigns
import com.example.app_corpaliv_grupo12.ui.components.CampaignCard
import com.example.app_corpaliv_grupo12.viewmodel.ContactErrors
import com.example.app_corpaliv_grupo12.viewmodel.DonacionUiState

@Composable
fun HomeScreen(state: DonacionUiState, onLogin: () -> Unit, onParticipate: () -> Unit) {
    var showHero by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { showHero = true }
    LazyColumn(Modifier.fillMaxSize(), contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 18.dp, vertical = 10.dp)) {
        item {
            AnimatedVisibility(showHero, enter = fadeIn() + slideInVertically { it / 8 }) {
                Surface(color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(24.dp), modifier = Modifier.fillMaxWidth().padding(bottom = 18.dp)) {
                    Column(Modifier.padding(22.dp)) {
                        Surface(color = Color.White, shape = RoundedCornerShape(14.dp)) {
                            androidx.compose.foundation.Image(
                                painter = painterResource(R.drawable.logo_corpaliv),
                                contentDescription = "Logo CORPALIV",
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp).width(190.dp).height(62.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                        Text("Pequeños gestos,\ngrandes cambios.", color = Color.White, style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(top = 20.dp))
                        Text("Acompañamos a personas con discapacidad múltiple durante toda su vida, respetando sus necesidades y diferencias.", color = Color.White.copy(alpha = .86f), modifier = Modifier.padding(top = 8.dp))
                        Button(onClick = onLogin, modifier = Modifier.padding(top = 16.dp)) { Text(if (state.role == null) "Iniciar sesión" else "Dashboard trabajador") }
                    }
                }
            }
            Text("Nuestros programas", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Apoya el desarrollo, la autonomía y la inclusión de personas con discapacidad múltiple.", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(bottom = 6.dp))
        }
        items(sampleCampaigns) { program -> CampaignCard(program, onParticipate) }
    }
}

@Composable
fun CampaignListScreen(onParticipate: () -> Unit) {
    LazyColumn(Modifier.fillMaxSize(), contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 18.dp, vertical = 12.dp)) {
        item {
            Text("Nuestros programas", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Text("Conoce las iniciativas y participa en la que más te interese.", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(bottom = 8.dp))
        }
        items(sampleCampaigns) { program -> CampaignCard(program, onParticipate) }
    }
}

@Composable
fun AboutScreen() { InstitucionalContent() }

@Composable
fun StoriesScreen() {
    val stories = listOf(
        Triple("Emilia: una voz propia", "8 años · Niñez · Perfil simulado", "Emilia es una niña con discapacidad múltiple. Le gusta la música y elegir las canciones de las actividades. Con apoyos de comunicación aumentativa puede expresar sus preferencias y participar en decisiones cotidianas."),
        Triple("Tomás: aprender y participar", "17 años · Juventud · Perfil simulado", "Tomás es un joven con discapacidad múltiple que disfruta compartir con sus compañeros y explorar nuevos intereses. Los apoyos personalizados y los espacios accesibles favorecen su participación con los demás."),
        Triple("Rosa: proyectos en cada etapa", "34 años · Adultez · Perfil simulado", "Rosa es una adulta con discapacidad múltiple que construye su proyecto de vida junto a su familia y su red de apoyo. El acompañamiento integral respeta sus decisiones y promueve su participación en la comunidad.")
    )
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text("Historias de impacto", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Perfiles ficticios creados para ilustrar el acompañamiento a lo largo de la vida. No representan personas reales.", color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 5.dp, bottom = 12.dp))
        Column(Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            stories.forEach { (title, stage, body) ->
                Card(shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), elevation = CardDefaults.cardElevation(2.dp)) {
                    Column(Modifier.fillMaxWidth().padding(18.dp)) {
                        Text(stage.uppercase(), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                        Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 5.dp))
                        Text(body, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ContactScreen(errors: ContactErrors, onSubmit: (String, String, String) -> Boolean, onChange: () -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Contáctanos", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Déjanos tus datos y nuestro equipo se pondrá en contacto contigo para contarte cómo colaborar.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        OutlinedTextField(value = nombre, onValueChange = { nombre = it; onChange() }, label = { Text("Nombre completo") }, singleLine = true, isError = errors.nombre != null, supportingText = { errors.nombre?.let { Text(it) } }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = correo, onValueChange = { correo = it; onChange() }, label = { Text("Correo electrónico") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), singleLine = true, isError = errors.correo != null, supportingText = { errors.correo?.let { Text(it) } }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = telefono, onValueChange = { telefono = it; onChange() }, label = { Text("Número de teléfono") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), singleLine = true, isError = errors.telefono != null, supportingText = { errors.telefono?.let { Text(it) } }, modifier = Modifier.fillMaxWidth())
        Button(onClick = { if (onSubmit(nombre, correo, telefono)) { nombre = ""; correo = ""; telefono = "" } }, modifier = Modifier.fillMaxWidth()) { Text("Enviar mis datos") }
        Text("Usaremos estos datos únicamente para comunicarnos contigo sobre nuestras campañas.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
