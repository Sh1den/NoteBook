package com.example.v.data.model

import androidx.annotation.StringRes
import com.example.v.R

data class ModelFolderDialog(
    @StringRes val title: Int = R.string.new_packege,
    val folder: Folder = Folder(),
    val isOpen: Boolean = false
)