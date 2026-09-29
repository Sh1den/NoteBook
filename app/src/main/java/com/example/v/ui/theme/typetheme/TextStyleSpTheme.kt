package com.example.v.ui.theme.typetheme

enum class TextStyleSpTheme(
    val value: String
) {
    Small(value = "small"),
    Medium(value = "medium"),
    Large(value = "large");

    companion object {
        fun TextStyleSpTheme.toTextStyleSpTheme() = when(this.value) {
            "small" -> Small
            "large" -> Large
            else -> Medium
        }
    }
}