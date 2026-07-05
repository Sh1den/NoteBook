package com.example.v.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule{
    @Singleton
    @Provides
    fun getDatabase(
        @ApplicationContext context: Context
    ): DataBase
    {
        return Room.databaseBuilder(
            context,
            DataBase::class.java,
            "notes.db"
        ).build()
    }
    @Singleton
    @Provides
    fun getNotesDao(dataBase: DataBase) : NoteDao{
        return dataBase.noteDao()    }
}