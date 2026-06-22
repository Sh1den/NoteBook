package com.example.v.data.model

import androidx.compose.ui.res.stringResource
import com.example.v.R
import org.intellij.lang.annotations.Language

data class Theme(
    var strTheme: String? = null,
    var colorTheme: ColorTheme = ColorTheme.None
){
    init {
       strTheme?.let{
            it -> when(it){
                "light" -> colorTheme = ColorTheme.Light
                 "dark" -> colorTheme = ColorTheme.Dark
                 else -> colorTheme = ColorTheme.None
            }
        }
    }
    fun getTheme(language: String): String{
        when(language) {
            "ru" -> return when (colorTheme) {
                ColorTheme.Dark -> "Темная"
                ColorTheme.Light -> "Светлая"
                ColorTheme.None -> "По умолчанию"
            }
            "eu" -> return when(colorTheme){
                ColorTheme.Dark -> "Dark"
                ColorTheme.Light -> "Light"
                ColorTheme.None -> "Default"
            }
        }
        return ""
    }
    enum class ColorTheme{
        Dark,
        Light,
        None
    }
}
fun getLang(lang: String): String{
    when(lang){
        "eu" -> return "English"
        "ru" -> return "Русский"
    }
    return "default"
}
enum class TypeSetting{
    Theme,
    Language
}