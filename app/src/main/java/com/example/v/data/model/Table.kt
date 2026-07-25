package com.example.v.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = Folders::class,
            parentColumns = ["id"],
            childColumns = ["foreignCategory"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Table(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(
        name = "title",
        collate = ColumnInfo.NOCASE)
    val nameNotes: String = "",
    val text: String = "",
    val time: String = "",
    val foreignCategory: Int = 0,
    val previousForeignCategory: Int? = null,
    val color: Int? = null
)