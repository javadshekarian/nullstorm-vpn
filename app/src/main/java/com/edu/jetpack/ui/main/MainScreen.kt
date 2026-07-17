package com.edu.jetpack.ui.main

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.edu.jetpack.parser.handler.MmkvManager
import com.edu.jetpack.ui.component.DrawerContent
import com.edu.jetpack.ui.home.Body
import com.edu.jetpack.ui.home.Header
import com.edu.jetpack.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    onScreenChange: (String) -> Unit
){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val viewModel = remember { HomeViewModel(context) }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                viewModel.importConfigFromUri(it)
            }
        }
    )

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
                modifier = Modifier.padding(padding),
                viewModel = viewModel,
                onImportClick = {
                    filePickerLauncher.launch("*/*")
                },
                onFreeConfigClick = {
                    viewModel.downloadFreeConfig()
                }
            )
        }
    }
}