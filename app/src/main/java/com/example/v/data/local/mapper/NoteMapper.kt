package com.example.v.data.local.mapper
import com.example.v.data.model.Category
import com.example.v.data.model.Folder
import com.example.v.data.model.Note
import com.example.v.data.model.Table
import com.example.v.data.model.TypeCategory
import com.example.v.ui.navigation.Route

fun Table.toDomain(): Note{
    return Note(
        id = this.id,
        title = this.nameNotes,
        text = this.text,
        time = this.time,
        category = Category(this.typeCategory,this.category)
    )
}

fun Note.toEntity(): Table{
    return Table(
        id = this.id,
        nameNotes = this.title,
        text = this.text,
        time = this.time,
        category =  this.category.stringCategory,
        typeCategory = this.category.typeCategory,
        isSystem = this.category.typeCategory != TypeCategory.OTHER
    )
}
