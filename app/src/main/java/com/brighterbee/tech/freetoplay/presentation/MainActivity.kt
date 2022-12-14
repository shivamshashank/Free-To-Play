package com.brighterbee.tech.freetoplay.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.graphics.RectangleShape
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.brighterbee.tech.freetoplay.presentation.components.Drawer
import com.brighterbee.tech.freetoplay.presentation.theme.FreeToPlayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            val scaffoldState = rememberScaffoldState()
            val navController = rememberNavController()

            FreeToPlayTheme(darkTheme = isSystemInDarkTheme()) {
                Scaffold(
                    scaffoldState = scaffoldState,
                    drawerShape = RectangleShape,
                    drawerContent = {
                        Drawer(scaffoldState, navController)
                    },
                ) { innerPadding ->
                    Navigation(innerPadding, scaffoldState, navController)
                }
            }
        }
    }
}