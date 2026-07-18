package com.example.v.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    primaryKeys = ["category_id","isSystem"]
)
data class Folders(
    val category_id: String,
    val isSystem: Boolean
)