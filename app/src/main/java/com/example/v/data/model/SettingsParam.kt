package com.example.v.data.model

import androidx.annotation.Keep

@Keep
data class Theme(
    val strTheme: String? = null,
    val colorTheme: ColorTheme = parseColorTheme(strTheme)
){
    companion object {
        fun parseColorTheme(strTheme: String?): ColorTheme {
            return when (strTheme) {
                "light" -> ColorTheme.Light
                "dark" -> ColorTheme.Dark
                else -> ColorTheme.None
            }
        }
    }
    fun getTheme(language: String): String{
        return when(language) {
            "ru" -> when (colorTheme) {
                ColorTheme.Dark -> "Темная"
                ColorTheme.Light -> "Светлая"
                ColorTheme.None -> "По умолчанию"
            }

            else -> when(colorTheme){
                ColorTheme.Dark -> "Dark"
                ColorTheme.Light -> "Light"
                ColorTheme.None -> "Default"
            }
        }
    }
    @Keep
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