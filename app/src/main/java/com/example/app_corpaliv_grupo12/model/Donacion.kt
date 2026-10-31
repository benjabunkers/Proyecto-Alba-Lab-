package com.example.app_corpaliv_grupo12.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "donaciones")
data class Donacion(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val campanaId: Int,
    val campanaNombre: String,
    val monto: Long,
    val fecha: String
)

@Entity(tableName = "seguimiento_donadores")
data class ContactoDonador(
    @PrimaryKey val id: String,
    val nombre: String,
    val correo: String,
    val telefono: String,
    val estado: String = ContactStatus.PENDING.name,
    val observacion: String = ""
) {
    fun status(): ContactStatus = ContactStatus.entries.firstOrNull { it.name == estado } ?: ContactStatus.PENDING
}
