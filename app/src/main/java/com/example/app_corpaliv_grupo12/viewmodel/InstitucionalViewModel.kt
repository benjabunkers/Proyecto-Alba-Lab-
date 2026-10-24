package com.example.app_corpaliv_grupo12.viewmodel

import androidx.lifecycle.ViewModel
import com.example.app_corpaliv_grupo12.model.Programa

class InstitucionalViewModel: ViewModel() {
    val nombreOrganizacion = "CORPALIV y Alba Lab"

    val descripcionGeneral =
        "Conoce nuestra organización, su propósito " +
                "y las iniciativas que desarrolla."

    val proposito =
        "Este espacio presenta el propósito institucional " +
                "y las oportunidades de participación social."

    val programas = listOf(
        Programa(
            nombre = "Programas de CORPALIV",
            descripcion = "Conoce las iniciativas y actividades " +
                    "de la organización."
        ),
        Programa(
            nombre = "Alba Lab",
            descripcion = "Descubre sus proyectos, iniciativas " +
                    "y oportunidades de colaboración."
        )
    )
}