package com.example.app_corpaliv_grupo12.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.app_corpaliv_grupo12.data.DonacionRepository
import com.example.app_corpaliv_grupo12.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ContactErrors(val nombre: String? = null, val correo: String? = null, val telefono: String? = null)

data class DonacionUiState(
    val role: UserRole? = null,
    val nombre: String = "",
    val correo: String = "",
    val campana: Campaign = sampleCampaigns.first(),
    val donaciones: List<Donacion> = emptyList(),
    val contactos: List<ContactoDonador> = emptyList(),
    val contactErrors: ContactErrors = ContactErrors(),
    val mensaje: String? = null
)

class DonacionViewModel(private val repository: DonacionRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(DonacionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.limpiarContactosDemo()
            launch { repository.donaciones.collect { _uiState.value = _uiState.value.copy(donaciones = it) } }
            launch { repository.contactos.collect { _uiState.value = _uiState.value.copy(contactos = it) } }
        }
    }

    fun iniciarSesion(correoSinFormato: String, clave: String): Boolean {
        val correo = correoSinFormato.trim()
        when {
            !correo.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) -> { avisar("Ingresa un correo válido"); return false }
            clave.length < 6 -> { avisar("La contraseña debe tener al menos 6 caracteres"); return false }
            else -> {
                val nombre = correo.substringBefore('@').replace('.', ' ').split(' ').joinToString(" ") { it.replaceFirstChar(Char::uppercase) }
                _uiState.value = _uiState.value.copy(role = UserRole.WORKER, nombre = nombre, correo = correo, mensaje = null)
                return true
            }
        }
    }

    fun cerrarSesion() { _uiState.value = _uiState.value.copy(role = null, nombre = "", correo = "", mensaje = null) }
    fun seleccionarCampana(campana: Campaign) { _uiState.value = _uiState.value.copy(campana = campana) }

    fun registrarContacto(nombreRaw: String, correoRaw: String, telefonoRaw: String): Boolean {
        val nombre = nombreRaw.trim().replace(Regex("\\s+"), " ")
        val correo = correoRaw.trim()
        val telefono = telefonoRaw.trim()
        val errores = ContactErrors(
            nombre = if (!nombre.matches(Regex("^[\\p{L}][\\p{L} .'-]{1,59}$"))) "Ingresa tu nombre completo" else null,
            correo = if (!correo.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))) "Ingresa un correo válido" else null,
            telefono = if (!telefono.matches(Regex("^\\+?[0-9][0-9 ()-]{6,18}$")) || telefono.count(Char::isDigit) !in 8..15) "Ingresa un teléfono válido" else null
        )
        if (errores.nombre != null || errores.correo != null || errores.telefono != null) {
            _uiState.value = _uiState.value.copy(contactErrors = errores, mensaje = null)
            return false
        }
        _uiState.value = _uiState.value.copy(contactErrors = ContactErrors())
        viewModelScope.launch {
            repository.registrarContacto(nombre, correo, telefono)
            avisar("Gracias, tus datos se enviaron a nuestro equipo")
        }
        return true
    }

    fun limpiarErroresContacto() { _uiState.value = _uiState.value.copy(contactErrors = ContactErrors()) }

    fun donar(montoTexto: String) {
        val monto = montoTexto.toLongOrNull()
        if (monto == null || monto < 500) { avisar("La donación mínima es $500"); return }
        val campana = _uiState.value.campana
        val fecha = SimpleDateFormat("dd/MM/yyyy", Locale("es", "CL")).format(Date())
        viewModelScope.launch {
            repository.donar(Donacion(campanaId = campana.id, campanaNombre = campana.title, monto = monto, fecha = fecha))
            avisar("¡Gracias por tu aporte!")
        }
    }

    fun actualizarContacto(contacto: ContactoDonador, estado: ContactStatus, observacion: String) {
        viewModelScope.launch {
            repository.guardarContacto(contacto.copy(estado = estado.name, observacion = observacion.trim()))
            avisar("Seguimiento actualizado")
        }
    }

    fun limpiarMensaje() { _uiState.value = _uiState.value.copy(mensaje = null) }
    private fun avisar(mensaje: String) { _uiState.value = _uiState.value.copy(mensaje = mensaje) }
}

class DonacionViewModelFactory(private val repository: DonacionRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = DonacionViewModel(repository) as T
}



