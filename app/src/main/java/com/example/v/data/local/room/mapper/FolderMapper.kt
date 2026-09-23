package com.example.v.data.local.room.mapper

import com.example.v.data.model.Folder
import com.example.v.data.local.room.entity.Folders
import com.example.v.data.local.room.relation.CountNotesWithFolders

fun CountNotesWithFolders.toDomain(): Folder {
    return Folder(this.folders.id,this.folders.folderName,this.countNotes)
}

fun Folder.toEntity(): Folders{
    return Folders(this.id,this.name)
}