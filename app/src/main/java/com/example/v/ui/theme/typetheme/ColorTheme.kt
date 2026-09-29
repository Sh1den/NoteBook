package com.example.v.ui.theme.typetheme

import androidx.compose.ui.graphics.Color
import com.example.v.data.model.ColorTheme

val WarningColor = Color(0xFFD71A1A)

val paletteColors = listOf(
    Color(0xFFFFE4E1),
    Color(0xFFFFB3BA),
    Color(0xFFFF4255),
    Color(0xFFE2F0CB),
    Color(0xFFBAFFC9),
    Color(0xFF46E37D),
    Color(0xFFD6EFFF),
    Color(0xFFBAE1FF),
    Color(0xFF5DB8FF),
    Color(0xFFF3E5F5),
    Color(0xFFE8DFF5),
    Color(0xFF905DF5),
    Color(0xFFFFF5CC),
    Color(0xFFFFE599),
    Color(0xFFFFD235)
)

val darkTheme = ColorSchema(
    background = Color(0xFF171616),
    onBackground = Color(0xFFE6E1E5),
    titleIcon = Color(0xFFE6E1E5),
    component = Color(0xFF262525),
    onComponent = Color(0xFFE6E1E5),
    titleComponent = Color(0xFFFFFFFF),
    labelComponent = Color(0xFF9E9E9E),
    componentIcon = Color(0xFFE6E1E5),
    primary = Color(0xFF8B6BEE),
    onPrimary = Color(0xFFFFFFFF),
    titleNavigation = Color(0xFF8B6BEE),
    onTitleNavigation = Color(0xFFE6E1E5),
    selectNavigationComponent = Color(0xFF382F58)
)

val lightTheme = ColorSchema(
    background = Color(0xFFEEF2FA),
    onBackground = Color(0xFF171616),
    titleIcon = Color(0xFF171616),
    component = Color(0xFFFAF8F8),
    onComponent = Color(0xFF171616),
    titleComponent = Color(0xFF171616),
    labelComponent = Color(0xFF7C7C7C),
    componentIcon = Color(0xFF171616),
    primary = Color(0xFF6750A4),
    onPrimary = Color(0xFFFFFFFF),
    titleNavigation = Color(0xFF6750A4),
    onTitleNavigation = Color(0xFF171616),
    selectNavigationComponent = Color(0xFFE2DDF7)
)

data class ColorSchema(
    val background: Color,
    val onBackground: Color,
    val titleIcon: Color = onBackground,
    val component: Color = background,
    val onComponent: Color = onBackground,
    val titleComponent: Color = onComponent,
    val labelComponent: Color = onComponent,
    val componentIcon: Color = onComponent,
    val primary: Color = Color(0xFF6750A4),
    val onPrimary: Color = onBackground,
    val titleNavigation: Color = primary,
    val onTitleNavigation: Color = onBackground,
    val selectNavigationComponent: Color = primary.copy(alpha = 0.15f),
)

fun ColorTheme.toColorSchema(
    isDark: Boolean
): ColorSchema {
    return when(this){
        ColorTheme.Default -> if(isDark) darkTheme else lightTheme
        ColorTheme.Dark -> darkTheme
        ColorTheme.Light -> lightTheme
    }
}
