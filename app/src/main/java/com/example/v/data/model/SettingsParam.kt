package com.example.v.data.model

import androidx.compose.ui.res.stringResource
import com.example.v.R

data class Language(var thLanguage: LanguageTypes = LanguageTypes.Russian){
    enum class LanguageTypes{
        English,
        Russian
    }
}

data class Theme(
    var colorTheme: ColorTheme = ColorTheme.None
){
    fun getTheme(): String{
        return when(colorTheme){
            ColorTheme.Dark -> "Темная"
            ColorTheme.Light -> "Светлая"
            ColorTheme.None -> "По умолчанию"
        }
    }
    enum class ColorTheme{
        Dark,
        Light,
        None
    }
}