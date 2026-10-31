package com.example.app_corpaliv_grupo12.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_corpaliv_grupo12.data.DonacionDatabase
import com.example.app_corpaliv_grupo12.data.DonacionRepository
import com.example.app_corpaliv_grupo12.ui.screens.*
import com.example.app_corpaliv_grupo12.viewmodel.DonacionViewModel
import com.example.app_corpaliv_grupo12.viewmodel.DonacionViewModelFactory
import com.example.app_corpaliv_grupo12.viewmodel.TemaViewModel
import com.example.app_corpaliv_grupo12.model.UserRole

@Composable
fun AppNavigation(temaViewModel: TemaViewModel) {
    val context = LocalContext.current.applicationContext
    val database = remember { DonacionDatabase.get(context) }
    val repository = remember { DonacionRepository(database.donacionDao(), database.contactoDonadorDao()) }
    val donacionViewModel: DonacionViewModel = viewModel(factory = DonacionViewModelFactory(repository))
    val state by donacionViewModel.uiState.collectAsState()
    val navController = rememberNavController()

    LaunchedEffect(state.mensaje) {
        if (state.mensaje != null) {
            kotlinx.coroutines.delay(2500)
            donacionViewModel.limpiarMensaje()
        }
    }

    AlbaScaffold(
        navController = navController,
        role = state.role,
        onLogout = { donacionViewModel.cerrarSesion() },
        onSettings = { navController.navigate(Screen.Settings.route) { launchSingleTop = true } }
    ) { padding ->
        Box(Modifier.fillMaxSize().padding(padding)) {
            NavHost(navController, startDestination = Screen.Home.route, modifier = Modifier.fillMaxSize()) {
                composable(Screen.Home.route) {
                    HomeScreen(
                        state = state,
                        onLogin = { navController.navigate(if (state.role == UserRole.WORKER) Screen.Worker.route else Screen.Login.route) },
                        onParticipate = { navController.navigate(Screen.Contact.route) }
                    )
                }
                composable(Screen.About.route) { AboutScreen() }
                composable(Screen.Catalog.route) { CampaignListScreen(onParticipate = { navController.navigate(Screen.Contact.route) }) }
                composable(Screen.Stories.route) { StoriesScreen() }
                composable(Screen.Contact.route) { ContactScreen(state.contactErrors, donacionViewModel::registrarContacto, donacionViewModel::limpiarErroresContacto) }
                composable(Screen.Login.route) {
                    LoginScreen(state.mensaje, onSubmit = { email, password ->
                        if (donacionViewModel.iniciarSesion(email, password)) {
                            navController.navigate(Screen.Worker.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    })
                }
                composable(Screen.Worker.route) { WorkerDashboardScreen(state, onDonors = { navController.navigate(Screen.Donors.route) }) }
                composable(Screen.Donors.route) { DonorsScreen(state.contactos, donacionViewModel::actualizarContacto) }
                composable(Screen.Settings.route) { SettingsScreen(temaViewModel) }
            }
            state.mensaje?.let { Snackbar(Modifier.align(Alignment.BottomCenter), content = { androidx.compose.material3.Text(it) }) }
        }
    }
}










