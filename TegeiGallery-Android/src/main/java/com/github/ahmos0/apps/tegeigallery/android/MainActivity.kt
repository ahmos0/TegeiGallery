package com.github.ahmos0.apps.tegeigallery.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.ahmos0.apps.tegeigallery.android.ui.HomeScreen
import com.github.ahmos0.apps.tegeigallery.android.ui.WorkPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "Home",
                    ) {
                        composable("Home") {
                            HomeScreen(navController = navController)
                        }
                        composable("work/{uuid}") { backStackEntry ->
                            val uuid = backStackEntry.arguments?.getString("uuid")

                            if (uuid != null) {
                                WorkPage(uuid = uuid)
                            }

                        }
                    }
                }
            }
        }
    }
}
