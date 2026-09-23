package com.example.v.data.local.room.relation

import androidx.annotation.Keep
import androidx.room.Embedded
import com.example.v.data.local.room.entity.Folders

@Keep
data class CountNotesWithFolders(
    @Embedded val folders: Folders,
    val countNotes: Int
)