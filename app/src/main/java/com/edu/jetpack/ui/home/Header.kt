package com.edu.jetpack.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Header(
    onMenuClick: () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ){
        IconButton(
            onClick = {
                onMenuClick()
            },
            modifier = Modifier.align (
                Alignment.CenterStart
            )
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = Color.Black
            )
        }

        Text(
            text = "NullStorm VPN",
            color = Color.Black
        )
    }
}