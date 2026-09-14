package com.example.v.data.local.room.mapper
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.v.data.local.room.relation.FolderWithNote
import com.example.v.data.model.Note
import com.example.v.data.local.room.entity.Table

fun FolderWithNote.toDomain(): Note{
    return Note(
        id = this.table.id,
        title = this.table.nameNotes,
        text = this.table.text,
        time = this.table.time,
        categoryId = this.folders?.id,
        isBasket = this.table.isDelete,
        color = if(this.table.color == null) null else Color(this.table.color)
    )
}
fun Note.toEntity(): Table{
    return Table(
        id = this.id,
        nameNotes = this.title,
        text = this.text,
        time = this.time,
        foreignCategory = this.categoryId,
        isDelete = this.isBasket,
        color = this.color?.toArgb()
    )
}
