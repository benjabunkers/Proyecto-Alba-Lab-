package com.example.app_corpaliv_grupo12.navigation

sealed class Screen(val route: String, val title: String) {
    data object Home : Screen("inicio", "Inicio")
    data object About : Screen("nosotros", "Sobre nosotros")
    data object Catalog : Screen("campanas", "Programas")
    data object Stories : Screen("historias", "Historias de impacto")
    data object Contact : Screen("contacto", "Contacto")
    data object Login : Screen("acceso", "Acceso trabajadores")
    data object Worker : Screen("dashboard", "Dashboard trabajador")
    data object Donors : Screen("donadores", "Donadores")
    data object Settings : Screen("configuracion", "Configuración")

    companion object {
        fun fromRoute(route: String?): Screen = when (route) {
            About.route -> About
            Catalog.route -> Catalog
            Stories.route -> Stories
            Contact.route -> Contact
            Login.route -> Login
            Worker.route -> Worker
            Donors.route -> Donors
            Settings.route -> Settings
            else -> Home
        }
    }
}
