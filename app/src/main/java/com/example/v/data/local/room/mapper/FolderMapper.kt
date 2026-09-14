package com.example.v.data.local.room.mapper

import com.example.v.data.model.Folder
import com.example.v.data.local.room.entity.Folders

fun Folders.toDomain(): Folder {
    return Folder(this.id,this.folderName)
}

fun Folder.toEntity(): Folders{
    return Folders(this.id,this.name)
}