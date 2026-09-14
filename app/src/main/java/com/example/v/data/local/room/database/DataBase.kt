package com.example.v.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.v.data.local.room.dao.FoldersDao
import com.example.v.data.local.room.dao.NoteDao
import com.example.v.data.local.room.entity.Folders
import com.example.v.data.local.room.entity.Table

@Database(
    entities = [Table::class, Folders::class],
    version = 2
)
abstract class DataBase: RoomDatabase(){
    abstract fun noteDao(): NoteDao
    abstract fun foldersDao(): FoldersDao
}