package com.example.app_corpaliv_grupo12.viewmodel

import androidx.lifecycle.ViewModel
import com.example.app_corpaliv_grupo12.model.Programa

class InstitucionalViewModel : ViewModel() {
    val nombreOrganizacion = "CORPALIV / Alba Lab"

    val descripcionGeneral =
        "Acompañamos a personas con discapacidad múltiple y sus familias, promoviendo apoyos integrales, participación y una vida sin barreras."

    val mision =
        "Acompañar el desarrollo de niños, niñas, jóvenes y adultos con discapacidad múltiple a lo largo de toda su vida, a través de un modelo integral que respete sus necesidades y diferencias."

    val vision =
        "Nuestra gran visión es crear y tener un país sin barreras para la discapacidad múltiple."

    val programas = listOf(
        Programa(
            nombre = "Apoyos a lo largo de la vida",
            descripcion = "Acompañamiento integral y personalizado para cada etapa, considerando las necesidades, preferencias y diferencias de cada persona."
        ),
        Programa(
            nombre = "Comunicación y participación",
            descripcion = "Apoyos y herramientas para expresar decisiones, fortalecer la autonomía y participar en la vida familiar y comunitaria."
        ),
        Programa(
            nombre = "Comunidades sin barreras",
            descripcion = "Promoción de entornos accesibles e inclusivos para avanzar hacia una participación plena."
        )
    )
}
