package com.arpit.mercari.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val MercariLightPalette = lightColors(
    primary = Blue,
    secondary = Blue
)

@Composable
fun MercariTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = MercariLightPalette,
        content = content
    )
}