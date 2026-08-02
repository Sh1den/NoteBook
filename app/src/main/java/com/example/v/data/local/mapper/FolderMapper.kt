package com.example.v.data.local.mapper

import com.example.v.data.model.Category
import com.example.v.data.model.Folder
import com.example.v.data.local.entity.Folders

fun Folders.toDomain(): Folder {
    return Folder(this.id,Category(typeCategory = this.typeCategory, stringCategory = this.category, categoryId = this.id))
}

fun Folder.toEntity(): Folders{
    return Folders(this.category.categoryId,this.category.stringCategory, typeCategory = this.category.typeCategory)
}