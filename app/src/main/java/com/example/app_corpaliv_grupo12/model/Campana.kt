package com.example.app_corpaliv_grupo12.model

enum class UserRole(val label: String) { WORKER("Trabajador") }
enum class ContactStatus(val label: String) { PENDING("Pendiente"), CONTACTED("Contactado"), INTERESTED("Interesado"), DONATED("Donó") }

data class Campaign(
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val emoji: String
)

val sampleCampaigns = listOf(
    Campaign(1, "Acompañamiento para toda la vida", "Apoyos integrales", "Aporta a apoyos coordinados para niños, niñas, jóvenes y adultos con discapacidad múltiple, respetando sus necesidades en cada etapa.", "🤝"),
    Campaign(2, "Comunicación para elegir y expresarse", "Comunicación y autonomía", "Facilitamos herramientas de comunicación aumentativa y apoyos personalizados para que cada persona pueda expresar preferencias y participar en decisiones cotidianas.", "💬"),
    Campaign(3, "Espacios accesibles e inclusivos", "Un país sin barreras", "Impulsamos adaptaciones y actividades comunitarias accesibles para que las personas con discapacidad múltiple puedan participar junto a sus comunidades.", "♿")
)
