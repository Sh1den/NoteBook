package com.example.v.ui.theme.typetheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.example.v.data.model.ColorTheme


object MaterialCurrentTheme {

    val colorSchema
        @Composable @ReadOnlyComposable get() = LocalComposites.localCurrentColorSchema.current

    val topography
        @Composable @ReadOnlyComposable get() = LocalComposites.localCurrentTopographyTheme.current

    val themeUpdater
        @Composable @ReadOnlyComposable get() = LocalComposites.localSetColorSchema.current

    val topographySpUpdater
        @Composable @ReadOnlyComposable get() = LocalComposites.localSetTopographySp.current

    val topographyFontUdapter
        @Composable @ReadOnlyComposable get() = LocalComposites.localSetTopographyFont.current
}
