package com.example.v.data.model

import androidx.annotation.StringRes
import com.example.v.R

data class ModelDialog(
    @StringRes val title: Int = R.string.new_packege,
    val categoryName: String = "",
    val isOpen: Boolean = false
)