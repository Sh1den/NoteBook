package com.example.v.data.local.room.entity

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
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
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
    val foreignCategory: Int? = null,
    val color: Int? = null,
    val isDelete: Boolean = false
)