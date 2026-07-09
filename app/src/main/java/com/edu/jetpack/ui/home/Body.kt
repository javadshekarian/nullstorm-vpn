package com.edu.jetpack.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edu.jetpack.data.fakeConfigs
import com.edu.jetpack.ui.component.ConfigCard

@Composable
fun Body(
    modifier: Modifier = Modifier
){
    var isConnected by remember {
        mutableStateOf(false)
    }

    val buttonColor =
        if (isConnected) Color(0xFF22C55E)
        else Color(0xFF3B82F6)

    val statusColor =
        if(isConnected) Color(0xFF22C55E)
        else Color.Gray

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text =
                    if(isConnected) "CONNECTED"
                    else "DISCONNECTED",
                color = statusColor,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text =
                    if (isConnected) "Your connection is secure"
                    else "Tap the button to connect"
            )

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            Button(
                onClick = {
                    isConnected = !isConnected
                },
                modifier = Modifier
                    .size(180.dp)
                    .shadow(
                        elevation = 18.dp,
                        shape = CircleShape
                    ),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                )
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.PowerSettingsNew,
                        contentDescription = null,
                        modifier = Modifier.size(70.dp),
                        tint = Color.White
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text =
                            if (isConnected) "DISCONNECT"
                            else "CONNECT",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.weight(1f)
                ) {
                    Text("IMPORT CONFIG")
                }

                Button(
                    onClick = {},
                    modifier = Modifier.weight(1f)
                ) {
                    Text("FREE CONFIG")
                }
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(fakeConfigs) {config ->
                    ConfigCard(config)
                }
            }
        }
    }
}






































