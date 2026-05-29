package com.zelada.jsonplaceholde

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.runtime.rememberNavBackStack
import com.zelada.jsonplaceholde.navigation.Routes
import com.zelada.jsonplaceholde.presentation.ui.PostsScreen
import com.zelada.jsonplaceholde.ui.theme.JsonplaceholdeTheme
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JsonplaceholdeTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(Routes.Home)

    NavDisplay(
        backStack = backStack,
        entryProvider = { key ->
            when (key) {
                is Routes.Home -> NavEntry(key) { PostsScreen() }
                is Routes.Detail -> NavEntry(key) { PostsScreen() }
                else -> NavEntry(key) { }
            }
        }
    )
}