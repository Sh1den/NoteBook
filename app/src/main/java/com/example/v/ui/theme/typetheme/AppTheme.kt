package com.example.v.ui.theme.typetheme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.v.data.model.ColorTheme
import com.example.v.ui.viewmodels.SettingViewModel

@Composable
fun CurrentTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
   val settingViewModel: SettingViewModel = hiltViewModel()
    val currentTheme = settingViewModel.getTheme()
    val theme = remember {
        mutableStateOf(
            Theme(
                currentTheme,
                currentTheme.toColorSchema(isDark),
                MainTopography
            )
        )
    }
    CompositionLocalProvider(
        LocalComposites.localCurrentTheme provides theme.value,
        LocalComposites.localSetColorSchema provides {
            settingViewModel.saveTheme(it)
            theme.value = theme.value.copy(
                colorSchema = it.toColorSchema(isDark),
                currentTheme = it
            )
        },
        LocalComposites.localSetTopography provides {}
    ) {
        content()
    }
}