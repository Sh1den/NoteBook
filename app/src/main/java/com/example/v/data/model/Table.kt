package com.example.v.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = Folders::class,
            parentColumns = ["category_id","isSystem"],
            childColumns = ["category","isSystem"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Table(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(
        name = "name_notes",
        collate = ColumnInfo.NOCASE)
    val nameNotes: String = "",
    val text: String = "",
    val time: String = "",
    var category: String = "Main",
    val isSystem: Boolean = true,
    var typeCategory: TypeCategory = TypeCategory.MAIN
)