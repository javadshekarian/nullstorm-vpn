package com.edu.jetpack.parser.dto
data class OutboundTrafficStat(
    val tag: String,
    val direction: String,
    val value: Long,
)