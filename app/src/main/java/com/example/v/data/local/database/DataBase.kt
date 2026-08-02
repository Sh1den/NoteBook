package com.example.v.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.v.data.local.dao.FoldersDao
import com.example.v.data.local.dao.NoteDao
import com.example.v.data.local.entity.Folders
import com.example.v.data.local.entity.Table

@Database(
    entities = [Table::class, Folders::class],
    version = 1
)
abstract class DataBase: RoomDatabase(){
    abstract fun noteDao(): NoteDao
    abstract fun foldersDao(): FoldersDao
}