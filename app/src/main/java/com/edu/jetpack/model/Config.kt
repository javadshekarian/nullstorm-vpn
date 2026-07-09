package com.edu.jetpack.model

data class Config (
    val id: Int,
    val name: String,
    val type: String,
    val address: String,
    val network: String,
    val sni: String,
    val fp: String,
    val details: String
)