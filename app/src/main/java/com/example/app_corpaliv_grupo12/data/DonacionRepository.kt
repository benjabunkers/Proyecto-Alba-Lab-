package com.example.app_corpaliv_grupo12.data

import com.example.app_corpaliv_grupo12.model.ContactoDonador
import com.example.app_corpaliv_grupo12.model.Donacion
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class DonacionRepository(private val donacionDao: DonacionDao, private val contactoDao: ContactoDonadorDao) {
    val donaciones: Flow<List<Donacion>> = donacionDao.observarDonaciones()
    val contactos: Flow<List<ContactoDonador>> = contactoDao.observarContactos()

    suspend fun limpiarContactosDemo() = contactoDao.limpiarContactosDemo()

    suspend fun donar(donacion: Donacion) = donacionDao.insertar(donacion)

    suspend fun registrarContacto(nombre: String, correo: String, telefono: String) {
        contactoDao.insertar(ContactoDonador(id = UUID.randomUUID().toString(), nombre = nombre, correo = correo, telefono = telefono))
    }

    suspend fun guardarContacto(contacto: ContactoDonador) = contactoDao.actualizar(contacto)
}

