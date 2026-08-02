package com.example.v.data.local.entity

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Keep
@Entity(
    tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = Folders::class,
            parentColumns = ["id"],
            childColumns = ["foreignCategory"],
            onDelete = ForeignKey.Companion.CASCADE,
            onUpdate = ForeignKey.Companion.CASCADE
        ),
        ForeignKey(
            entity = Folders::class,
            parentColumns = ["id"],
            childColumns = ["previousForeignCategory"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ]
)
data class Table(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(
        name = "title",
        collate = ColumnInfo.Companion.NOCASE)
    val nameNotes: String = "",
    val text: String = "",
    val time: String = "",
    val foreignCategory: Int = 0,
    val previousForeignCategory: Int = 0,
    val color: Int? = null
)