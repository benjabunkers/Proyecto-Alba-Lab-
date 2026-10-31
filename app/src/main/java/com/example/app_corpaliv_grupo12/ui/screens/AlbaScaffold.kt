package com.example.app_corpaliv_grupo12.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.app_corpaliv_grupo12.R
import com.example.app_corpaliv_grupo12.model.UserRole
import com.example.app_corpaliv_grupo12.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbaScaffold(navController: NavController, role: UserRole?, onLogout: () -> Unit, onSettings: () -> Unit, content: @Composable (PaddingValues) -> Unit) {
    val entry by navController.currentBackStackEntryAsState()
    val route = entry?.destination?.route
    val current = Screen.fromRoute(route)
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet {
            Column(Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
                Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 24.dp)) {
                    Image(painterResource(R.drawable.logo_corpaliv), contentDescription = "Logo CORPALIV", modifier = Modifier.width(190.dp).height(64.dp), contentScale = ContentScale.Fit)
                    Text(role?.label ?: "Fundación solidaria", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 8.dp))
                }
                HorizontalDivider()
                val options = if (role == UserRole.WORKER) {
                    listOf(Screen.Home, Screen.Worker, Screen.Donors, Screen.Catalog, Screen.Stories, Screen.About, Screen.Contact)
                } else {
                    listOf(Screen.Home, Screen.Catalog, Screen.Login, Screen.Stories, Screen.About, Screen.Contact)
                }
                options.forEach { screen ->
                    NavigationDrawerItem(
                        label = { Text(screen.title) }, selected = current.route == screen.route,
                        onClick = { scope.launch { drawerState.close() }; navController.navigate(screen.route) { launchSingleTop = true } },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
                if (role != null) NavigationDrawerItem(
                    label = { Text("Cerrar sesión") }, selected = false,
                    onClick = { scope.launch { drawerState.close() }; onLogout(); navController.navigate(Screen.Home.route) { popUpTo(Screen.Home.route) { inclusive = false }; launchSingleTop = true } },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    }) {
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(current.title) },
                navigationIcon = { IconButton(onClick = { scope.launch { drawerState.open() } }) { Icon(Icons.Default.Menu, "Abrir menú") } },
                actions = { IconButton(onClick = onSettings) { Icon(Icons.Default.Settings, "Configuración") } }
            )
        }) { padding -> content(padding) }
    }
}




