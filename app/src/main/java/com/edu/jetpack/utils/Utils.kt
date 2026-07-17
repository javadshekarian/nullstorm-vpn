package com.edu.jetpack.utils

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object Utils {
    fun sanitizeVlessLink(link: String): String? {
        return try {
            val hashIndex = link.indexOf("#")
            if (hashIndex == -1) return link

            val base = link.substring(0, hashIndex)
            val fragment = link.substring(hashIndex + 1)

            // encode fragment (remove emoji / illegal chars)
            val encodedFragment = URLEncoder.encode(fragment, StandardCharsets.UTF_8.toString())

            "$base#$encodedFragment"

        } catch (e: Exception) {
            link // fallback
        }
    }
}