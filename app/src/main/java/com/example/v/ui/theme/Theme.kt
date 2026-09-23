package com.example.v.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.v.data.model.ColorTheme


private val DarkColorScheme = darkColorScheme(
    background = DarkBackground,
    primary = DarkPrimary,
    surface =  DarkSurface,
    onSurface = DarkOnSurface,
    tertiary = DarkTertiary,
    onTertiary = DarkSecondary
)

private val LightColorScheme = lightColorScheme(
    background = LightBackground,
    primary = LightPrimary,
    surface = DarkOnSurface,
    onSurface = LightOnSurface,
    tertiary = LightOnTertiary,
    onTertiary = LightSecondary
)

@Composable
fun VTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colorTheme: ColorTheme,
    content: @Composable () -> Unit
) {
   val colorScheme by lazy {
       when(colorTheme){
           ColorTheme.Default -> if (darkTheme) DarkColorScheme else LightColorScheme
           ColorTheme.Light -> LightColorScheme
           ColorTheme.Dark -> DarkColorScheme
       }
   }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}