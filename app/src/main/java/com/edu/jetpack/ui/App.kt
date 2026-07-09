package com.edu.jetpack.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.edu.jetpack.ui.about.AboutScreen
import com.edu.jetpack.ui.home.Body
import com.edu.jetpack.ui.component.DrawerContent
import com.edu.jetpack.ui.home.Header
import com.edu.jetpack.ui.login.LoginScreen
import com.edu.jetpack.ui.main.MainScreen
import kotlinx.coroutines.launch

@Composable
fun App(){
    var currentScreen by remember {
        mutableStateOf("home")
    }

    when(currentScreen) {
        "home" -> {
            MainScreen {
                currentScreen = it
            }
        }
        "login" -> {
            LoginScreen(
                onBackClick = {
                    currentScreen = "home"
                }
            )
        }
        "about" -> {
            AboutScreen (
                onBackClick = {
                    currentScreen = "home"
                }
            )
        }
    }
}