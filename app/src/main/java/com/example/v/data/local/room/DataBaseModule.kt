package com.example.v.data.local.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.v.data.local.repository.FolderRepository
import com.example.v.data.local.repository.NoteRepository
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
            arrayOf(TypeCategory.MAIN))
        db.execSQL("Insert Into Folders (category,typeCategory) Values ('Basket',?)",
            arrayOf(TypeCategory.BASKET))
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
    fun getFoldersDao(dataBase: DataBase): FoldersDao{
        return dataBase.foldersDao()
    }
    @Singleton
    @Provides
    fun getNoteRepository(noteDao: NoteDao): NoteRepository {
        return NoteRepository(noteDao)
    }
    @Singleton
    @Provides
    fun getFoldersRepository(foldersDao: FoldersDao): FolderRepository{
        return FolderRepository(foldersDao)
    }
}