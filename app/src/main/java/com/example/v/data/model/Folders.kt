package com.example.v.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Folders(
    @PrimaryKey
    val category_id: String,
    val isSystem: Boolean = false
)