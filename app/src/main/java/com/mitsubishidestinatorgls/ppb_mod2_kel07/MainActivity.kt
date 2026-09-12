package com.mitsubishidestinatorgls.ppb_mod2_kel07

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mitsubishidestinatorgls.ppb_mod2_kel07.ui.theme.PPB_Mod2_Kel07Theme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PPB_Mod2_Kel07Theme {
                AnimeApp()
            }
        }
    }
}

@Composable
fun AnimeApp() {
    val navController = rememberNavController()
    val items = listOf(Screen.Anime, Screen.About)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStack?.destination?.route

                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            when (screen) {
                                Screen.Anime -> Icon(Icons.Default.Movie, contentDescription = "Anime")
                                Screen.About -> Icon(Icons.Default.Info, contentDescription = "About")
                                else -> {}
                            }
                        },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Anime.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Anime.route) {
                AnimeListScreen(
                    onItemClick = { animeId ->
                        navController.navigate(Screen.Detail.createRoute(animeId))
                    }
                )
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("animeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val animeId = backStackEntry.arguments?.getInt("animeId") ?: 0
                AnimeDetailScreen(animeId = animeId, navController = navController)
            }
        }
    }
}
