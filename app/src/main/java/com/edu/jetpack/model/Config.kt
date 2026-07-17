package com.edu.jetpack.model

import com.edu.jetpack.parser.dto.entities.ProfileItem

data class Config (
    val guid: String,
    val name: String,
    val type: String,
    val address: String,
    val network: String,
    val sni: String?,
    val fp: String?,
    val details: String?,
    val content: String,
    val profile: ProfileItem
)