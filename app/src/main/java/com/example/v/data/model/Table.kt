package com.example.v.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Table(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name_notes")
    val nameNotes: String,
    val text: String,
    val time: String,
    val lenText: Long
)