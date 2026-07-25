package com.example.v.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Folders(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String = "",
    val typeCategory: TypeCategory = TypeCategory.OTHER
)