package com.example.app_corpaliv_grupo12.ui.theme.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_corpaliv_grupo12.navigation.Screen
import com.example.app_corpaliv_grupo12.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val currentScreen by viewModel.currentScreen.collectAsState()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "CORPALIV / Alba Lab",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                HorizontalDivider()

                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    selected = currentScreen == Screen.Home,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.Home)
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Sobre Nosotros") },
                    icon = { Icon(Icons.Default.Info, contentDescription = null) },
                    selected = currentScreen == Screen.About,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.About)
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Catálogo") },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) },
                    selected = currentScreen == Screen.Catalog,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.Catalog)
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Historias de Impacto") },
                    icon = { Icon(Icons.Default.Star, contentDescription = null) },
                    selected = currentScreen == Screen.Stories,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.Stories)
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Contacto y Alianzas") },
                    icon = { Icon(Icons.Default.Email, contentDescription = null) },
                    selected = currentScreen == Screen.Contact,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.Contact)
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        val title = when (currentScreen) {
                            Screen.Home -> "Inicio"
                            Screen.About -> "Sobre Nosotros"
                            Screen.Catalog -> "Catálogo"
                            Screen.Stories -> "Historias de Impacto"
                            Screen.Contact -> "Contacto"
                        }
                        Text(text = title)
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (currentScreen) {
                    Screen.Home -> HomeScreen()
                    Screen.About -> AboutScreen()
                    Screen.Catalog -> CatalogScreen()
                    Screen.Stories -> StoriesScreen()
                    Screen.Contact -> ContactScreen()
                }
            }
        }
    }
}