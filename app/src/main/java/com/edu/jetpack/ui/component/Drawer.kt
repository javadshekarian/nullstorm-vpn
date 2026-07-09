package com.edu.jetpack.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(
    onLoginClick: () -> Unit,
    onAboutClick: () -> Unit
){
    ModalDrawerSheet{
        Text(
            text = "Menu",
            modifier = Modifier.padding(
                start = 16.dp,
                top = 32.dp,
                bottom = 16.dp
            ),
            style = MaterialTheme.typography.titleLarge
        )

//        NavigationDrawerItem(
//            label = {
//                Text("Settings")
//            },
//            selected = false,
//            onClick = {}
//        )

        NavigationDrawerItem(
            label = {
                Text("About")
            },
            selected = false,
            onClick = {
                onAboutClick()
            }
        )

        NavigationDrawerItem(
            label = {
                Text("Login")
            },
            selected = false,
            onClick = {
                onLoginClick()
            }
        )
    }
}









































