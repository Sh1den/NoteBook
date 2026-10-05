package com.example.v.ui.theme.typetheme
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.v.data.model.ColorTheme
import com.example.v.data.model.SettingTopography
import com.example.v.data.model.TextStyleFontFamilyTheme
import com.example.v.data.model.TextStyleSpTheme
import com.example.v.ui.viewmodels.SettingViewModel

@Composable
fun CurrentTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    currentTheme: ColorTheme,
    settingTopography: SettingTopography = SettingTopography(),
    onSaveColorTheme: (ColorTheme) -> Unit = {},
    onSaveTopographySp: (TextStyleSpTheme) -> Unit = {},
    onSaveTopographyFont: (TextStyleFontFamilyTheme) -> Unit = {},
    content: @Composable () -> Unit
){
    val colorSchema = currentTheme.toColorSchema(isDark)
    val topography = MainTopography.generateTopography(
        settingTopography.textStyleFontFamilyTheme.fontFamily,
        settingTopography.textStyleSpTheme.currentSp
    )
    CompositionLocalProvider(
        LocalComposites.localCurrentColorSchema provides colorSchema,
        LocalComposites.localSetColorSchema provides {
            onSaveColorTheme(it)
        },
        LocalComposites.localCurrentTopographyTheme provides topography,
        LocalComposites.localSetTopographySp provides {
            onSaveTopographySp(it)
        },
        LocalComposites.localSetTopographyFont provides {
            onSaveTopographyFont(it)
        }
    ) {
        content()
    }
}