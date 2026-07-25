package com.example.v.data.local.mapper
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.v.data.model.Category
import com.example.v.data.model.FolderWithNote
import com.example.v.data.model.Note
import com.example.v.data.model.Table

fun FolderWithNote.toDomain(): Note{
    return Note(
        id = this.table.id,
        title = this.table.nameNotes,
        text = this.table.text,
        time = this.table.time,
        previousForeignCategory = this.table.previousForeignCategory,
        category = Category(this.folders.typeCategory,this.folders.category,this.folders.id),
        color = if(this.table.color == null) null else Color(this.table.color)
    )
}
fun Note.toEntity(): Table{
    return Table(
        id = this.id,
        nameNotes = this.title,
        text = this.text,
        time = this.time,
        foreignCategory = this.category.categoryId,
        previousForeignCategory = this.previousForeignCategory,
        color = this.color?.toArgb()
    )
}
