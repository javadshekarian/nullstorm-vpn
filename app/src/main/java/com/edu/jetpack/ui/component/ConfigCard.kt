package com.edu.jetpack.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.edu.jetpack.model.Config

@Composable
fun ConfigCard(
    config: Config,
    modifier: Modifier = Modifier,
    onConnectClick: (Config) -> Unit = {},
    onCardClick: (Config) -> Unit = {}
){
    var expanded by remember {
        mutableStateOf(false)
    }

    var connected by remember {
        mutableStateOf(false)
    }

    var protocolColor = when (config.type.uppercase()){
        "VLESS" -> Color(0xFF2563EB)
        "VMESS" -> Color(0xFF7C3AED)
        "TROJAN" -> Color(0xFFF97316)
        "SS" -> Color(0xFF16A34A)
        else -> MaterialTheme.colorScheme.primary
    }

    val connectButtonColor by animateColorAsState(
        if(connected)  Color(0xFF22C55E)
        else protocolColor,
        label = ""
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onCardClick(config)
            },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .background(
                            protocolColor,
                            CircleShape
                        )
                )

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Text(
                    text = config.type.uppercase(),
                    fontWeight = FontWeight.Bold,
                    color = protocolColor
                )

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                Box {
                    IconButton(
                        onClick = {
                            expanded = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text("Edit")
                            },
                            onClick = {}
                        )

                        DropdownMenuItem(
                            text = {
                                Text("Share")
                            },
                            onClick = {}
                        )

                        DropdownMenuItem(
                            text = {
                                Text("Delete")
                            },
                            onClick = {}
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Text(
                text = "#${config.id} ${config.name}",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Language,
                    contentDescription = null,
                    tint = Color.Gray
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(config.address)
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AssistChip(
                    onClick = {},
                    label = {
                        Text(config.network)
                    }
                )

                AssistChip(
                    onClick = {},
                    label = {
                        Text(config.details)
                    }
                )

                AssistChip(
                    onClick = {},
                    label = {
                        Text(config.fp)
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            Text(
                text = "SNI: ${config.sni}",
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Button(
                onClick = {
                    connected = !connected
                    onConnectClick(config)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = connectButtonColor
                )
            ) {
                Icon(
                    imageVector = Icons.Default.PowerSettingsNew,
                    contentDescription = null
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    if(connected) "CONNECTED"
                    else "CONNECT"
                )
            }
        }
    }
}







































