package com.example.app_corpaliv_grupo12.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(message: String?, onSubmit: (String, String) -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().padding(22.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Acceso para trabajadores", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Si usted es parte del equipo Albalab Inicie sesion aqui!", color = MaterialTheme.colorScheme.onSurfaceVariant)
        OutlinedTextField(email, { email = it }, label = { Text("Correo institucional") }, placeholder = { Text("nombre@fundacion.cl") }, singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp))
        OutlinedTextField(password, { password = it }, label = { Text("Contraseña") }, singleLine = true, visualTransformation = PasswordVisualTransformation(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(14.dp))
        if (message != null) Text(message, color = MaterialTheme.colorScheme.error)
        Button(onClick = { onSubmit(email, password) }, modifier = Modifier.fillMaxWidth().height(52.dp)) { Text("Iniciar sesión") }
        Text("Demostración local: cualquier correo válido y contraseña de seis o más caracteres.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
