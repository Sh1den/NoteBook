package com.example.v.ui.theme.typetheme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.v.data.model.ColorTheme

internal object LocalComposites {

    val localCurrentTheme = staticCompositionLocalOf<Theme>{ error("") }

    val localSetColorSchema = staticCompositionLocalOf<(ColorTheme) -> Unit> { error("") }

    val localSetTopography = staticCompositionLocalOf<(TextStyles) -> Unit> { error("") }


}