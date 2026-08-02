package com.example.v.data.model

import androidx.annotation.Keep

@Keep
data class Folder(
    val id: Int = 0,
    val category: Category = Category()
)