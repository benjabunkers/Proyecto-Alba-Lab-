package com.example.app_corpaliv_grupo12.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_corpaliv_grupo12.model.ContactoDonador
import com.example.app_corpaliv_grupo12.model.Donacion
import kotlinx.coroutines.flow.Flow

@Dao
interface DonacionDao {
    @Query("SELECT * FROM donaciones ORDER BY id DESC")
    fun observarDonaciones(): Flow<List<Donacion>>

    @Insert
    suspend fun insertar(donacion: Donacion)
}

@Dao
interface ContactoDonadorDao {
    @Query("SELECT * FROM seguimiento_donadores ORDER BY nombre")
    fun observarContactos(): Flow<List<ContactoDonador>>

    @Query("DELETE FROM seguimiento_donadores WHERE id IN ('camila', 'matias', 'valentina', 'diego')")
    suspend fun limpiarContactosDemo()

    @Insert
    suspend fun insertar(contacto: ContactoDonador)

    @Update
    suspend fun actualizar(contacto: ContactoDonador)
}


