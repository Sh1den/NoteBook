package com.example.v.data.model

data class Theme(
    var strTheme: String? = null,
    var colorTheme: ColorTheme = ColorTheme.None
){
    init {
       strTheme?.let{
            it ->
           colorTheme = when(it){
               "light" -> ColorTheme.Light
               "dark" -> ColorTheme.Dark
               else -> ColorTheme.None
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