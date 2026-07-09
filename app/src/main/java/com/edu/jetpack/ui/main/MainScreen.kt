package com.edu.jetpack.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.edu.jetpack.ui.component.DrawerContent
import com.edu.jetpack.ui.home.Body
import com.edu.jetpack.ui.home.Header
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    onScreenChange: (String) -> Unit
){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                onLoginClick = {
                    onScreenChange("login")
                    scope.launch {
                        drawerState.close()
                    }
                },
                onAboutClick = {
                    onScreenChange("about")
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                Header(
                    onMenuClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            },
        ) { padding ->
            Body(
                modifier = Modifier.padding(padding)
            )
        }
    }
}