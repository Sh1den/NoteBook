package com.example.v.data.local.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class Folders(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String = ""
)