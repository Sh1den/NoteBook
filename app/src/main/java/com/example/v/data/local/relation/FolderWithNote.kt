package com.example.v.data.local.relation

import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Relation
import com.example.v.data.local.entity.Folders
import com.example.v.data.local.entity.Table

@Keep
data class FolderWithNote(
    @Embedded val table: Table,
    @Relation(
        parentColumn = "foreignCategory",
        entityColumn = "id"
    )
    val folders: Folders
)