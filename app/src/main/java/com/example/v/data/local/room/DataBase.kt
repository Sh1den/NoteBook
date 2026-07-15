package com.example.v.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.v.data.model.Folders
import com.example.v.data.model.Table

@Database(
    entities = [Table::class, Folders::class],
    version = 1
)
abstract class DataBase: RoomDatabase(){
    abstract fun noteDao(): NoteDao
    abstract fun foldersDao(): FoldersDao
}