package com.example.v.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class FolderWithNote(
    @Embedded val table: Table,
    @Relation(
        parentColumn = "foreignCategory",
        entityColumn = "id"
    )
    val folders: Folders
)