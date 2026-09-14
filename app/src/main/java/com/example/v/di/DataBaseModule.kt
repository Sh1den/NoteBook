package com.example.v.di

import android.content.Context
import androidx.room.Room
import com.example.v.data.local.room.database.DataBase
import com.example.v.data.local.room.dao.FoldersDao
import com.example.v.data.local.room.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun getDatabase(
        @ApplicationContext context: Context
    ): DataBase {
        return Room.databaseBuilder(
            context,
            DataBase::class.java,
            "notes.db"
        ).build()
    }

    @Singleton
    @Provides
    fun getNotesDao(dataBase: DataBase): NoteDao {
        return dataBase.noteDao()
    }
    @Singleton
    @Provides
    fun getFoldersDao(dataBase: DataBase): FoldersDao {
        return dataBase.foldersDao()
    }
}