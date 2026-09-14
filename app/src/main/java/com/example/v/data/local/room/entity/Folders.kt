package com.example.v.data.local.room.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity
data class Folders(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val folderName: String = ""
)