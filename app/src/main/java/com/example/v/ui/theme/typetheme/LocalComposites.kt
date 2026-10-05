package com.example.v.ui.theme.typetheme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.v.data.model.ColorTheme
import com.example.v.data.model.TextStyleFontFamilyTheme
import com.example.v.data.model.TextStyleSpTheme

internal object LocalComposites {

    val localCurrentColorSchema = staticCompositionLocalOf<ColorSchema>{ error("") }

    val localCurrentTopographyTheme = staticCompositionLocalOf<TextStyles> { error("") }

    val localSetColorSchema = staticCompositionLocalOf<(ColorTheme) -> Unit> { error("") }

    val localSetTopographySp = staticCompositionLocalOf<(TextStyleSpTheme) -> Unit> { error("") }

    val localSetTopographyFont = staticCompositionLocalOf<(TextStyleFontFamilyTheme) -> Unit> { error("") }
}