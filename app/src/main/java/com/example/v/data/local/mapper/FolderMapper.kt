package com.example.v.data.local.mapper

import com.example.v.data.model.Folder
import com.example.v.data.local.entity.Folders

fun Folders.toDomain(): Folder {
    return Folder(this.id,this.category)
}

fun Folder.toEntity(): Folders{
    return Folders(this.id,this.name)
}