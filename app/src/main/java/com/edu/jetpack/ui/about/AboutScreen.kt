package com.edu.jetpack.ui.about

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AboutScreen(
    onBackClick: () -> Unit
){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(
                    onClick = {
                        onBackClick()
                    }
                ){
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                Text(
                    text = "About",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = null,
                    modifier = Modifier.size(90.dp),
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "NullStorm VPN",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    text = "Secure. Private. Fast."
                )
            }
        }
        item {
            AboutCard(
                title = "About VPN",
                icon = Icons.Default.Info
            ){
                Text(
                    text =
                        """
                    NullStorm VPN helps you create a secure
                    and private connection to the internet.

                    Your traffic is protected through modern
                    VPN protocols while maintaining speed
                    and reliability.
                    """.trimIndent()
                )
            }
        }

        item {
            AboutCard(
                title = "Features",
                icon = Icons.Default.Star
            ){

                FeatureItem(
                    "Privacy Protection"
                )

                FeatureItem(
                    "Secure Encryption"
                )

                FeatureItem(
                    "Multiple VPN Protocols"
                )

                FeatureItem(
                    "Fast Server Connection"
                )
            }
        }

        item {
            AboutCard(
                title = "Supported Protocols",
                icon = Icons.Default.Settings
            ){
                ProtocolChip("VLESS")
                ProtocolChip("VMESS")
                ProtocolChip("Trojan")
                ProtocolChip("Shadowsocks")
            }

        }

        item {
            AboutCard(
                title = "Application Info",
                icon = Icons.Default.Apps
            ){
                InfoRow(
                    "Version",
                    "1.0.0"
                )

                InfoRow(
                    "Build",
                    "2026"
                )

                InfoRow(
                    "Platform",
                    "Android"
                )
            }
        }

        item {
            Text(
                text = "© 2026 NullStorm VPN",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}



@Composable
private fun AboutCard(
    title:String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content:@Composable ColumnScope.()->Unit
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ){
        Column(
            modifier = Modifier.padding(20.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = icon,
                    contentDescription = null
                )

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            content()
        }
    }
}



@Composable
private fun FeatureItem(
    text:String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Text(text)
    }

}



@Composable
private fun ProtocolChip(
    text:String
){
    AssistChip(
        onClick = {},
        label = {
            Text(text)
        },
        modifier = Modifier.padding(
            end = 8.dp
        )
    )
}



@Composable
private fun InfoRow(
    title:String,
    value:String
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value
        )
    }
}