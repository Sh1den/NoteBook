package com.example.v.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.v.data.local.database.DataBase
import com.example.v.data.local.dao.FoldersDao
import com.example.v.data.local.dao.NoteDao
import com.example.v.data.model.TypeCategory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class CallbackFolder: RoomDatabase.Callback(){
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        db.execSQL("Insert Into Folders (category,typeCategory) Values ('Main',?)",
            arrayOf(TypeCategory.MAIN.name))
        db.execSQL("Insert Into Folders (category,typeCategory) Values ('Basket',?)",
            arrayOf(TypeCategory.BASKET.name))
    }
}
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
        ).addCallback(CallbackFolder()).build()
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