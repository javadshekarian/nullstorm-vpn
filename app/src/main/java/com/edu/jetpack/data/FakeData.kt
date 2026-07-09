package com.edu.jetpack.data

import com.edu.jetpack.model.Config

val fakeConfigs = listOf(
    Config(
        id = 1,
        name = "Germany #1",
        type = "VLESS",
        address = "104.17.10.20:443",
        network = "TCP",
        sni = "google.com",
        fp = "chrome",
        details = "REALITY"
    ),

    Config(
        id = 2,
        name = "Singapore",
        type = "VMESS",
        address = "45.12.20.80:443",
        network = "TCP",
        sni = "cloudflare.com",
        fp = "firefox",
        details = "XTLS-RPRX-VISION"
    ),

    Config(
        id = 3,
        name = "Japan",
        type = "Trojan",
        address = "20.55.100.44:443",
        network = "UDP",
        sni = "bing.com",
        fp = "chrome",
        details = "NONE"
    )
)