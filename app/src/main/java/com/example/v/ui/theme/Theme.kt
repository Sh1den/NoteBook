package com.example.v.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.v.data.model.Theme

private val DarkColorScheme = darkColorScheme(
    background = DarkBackground,
    primary = DarkPrimary,
    surface =  DarkSurface,
    onSurface = DarkOnSurface,
    tertiary = DarkTertiary,
    onTertiary = DarkSecondary
)

private val LightColorScheme = lightColorScheme(
    background = Color.White,
    primary = LightPrimary,
    surface = DarkOnSurface,
    onSurface = LightOnSurface,
    tertiary = LightOnTertiary,
    onTertiary = LightSecondary
)

@Composable
fun VTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    settingsTheme: Theme,
    content: @Composable () -> Unit
) {
   val colorScheme by lazy {
       when(settingsTheme.colorTheme){
           Theme.ColorTheme.None -> if (darkTheme) DarkColorScheme else LightColorScheme
           Theme.ColorTheme.Light -> LightColorScheme
           Theme.ColorTheme.Dark -> DarkColorScheme
       }
   }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}