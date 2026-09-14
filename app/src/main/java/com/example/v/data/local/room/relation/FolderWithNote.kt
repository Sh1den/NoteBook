package com.example.v.data.local.room.relation

import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Relation
import com.example.v.data.local.room.entity.Folders
import com.example.v.data.local.room.entity.Table

@Keep
data class FolderWithNote(
    @Embedded val table: Table,
    @Relation(
        parentColumn = "foreignCategory",
        entityColumn = "id"
    )
    val folders: Folders?
)