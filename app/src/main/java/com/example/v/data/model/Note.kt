package com.example.v.data.model

import androidx.annotation.Keep
import androidx.compose.ui.graphics.Color

@Keep
data class Note(
    val id: Int = 0,
    val title: String = "",
    val text: String = "",
    val time: String = "",
    val categoryId: Int? = null,
    val isBasket: Boolean = false,
    val color: Color? = null
)
