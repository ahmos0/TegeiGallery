package com.github.ahmos0.apps.tegeigallery

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform