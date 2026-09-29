package com.example.v.ui.theme.typetheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.example.v.data.model.ColorTheme

internal data class Theme(
    val currentTheme: ColorTheme,
    val colorSchema: ColorSchema,
    val topography: TextStyles
)

object MaterialCurrentTheme {
    val currentTheme
        @Composable @ReadOnlyComposable get() = LocalComposites.localCurrentTheme.current.currentTheme

    val colorSchema
        @Composable @ReadOnlyComposable get() = LocalComposites.localCurrentTheme.current.colorSchema

    val topography
        @Composable @ReadOnlyComposable get() = LocalComposites.localCurrentTheme.current.topography

    val themeUpdater
        @Composable @ReadOnlyComposable get() = LocalComposites.localSetColorSchema.current

    val topographyUpdater
        @Composable @ReadOnlyComposable get() = LocalComposites.localSetTopography.current
}
