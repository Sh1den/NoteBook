package com.example.v.data.local.mapper

import com.example.v.data.model.Category
import com.example.v.data.model.Folder
import com.example.v.data.model.Folders
import com.example.v.data.model.TypeCategory

fun Folders.toDomain(): Folder {
    return Folder(Category(typeCategory = TypeCategory.OTHER, stringCategory = this.category_id))
}

fun Folder.toEntity(): Folders{
    return Folders(this.category.stringCategory, isSystem = false)
}