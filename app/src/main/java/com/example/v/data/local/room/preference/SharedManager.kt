package com.example.v.data.local.room.preference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.v.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val THEME = "theme"
        private const val COLUMN_COUNT = "column"
    }

    private val sharedPreferences = context.getSharedPreferences("settings_param",MODE_PRIVATE)
    fun setTheme(themeId: Int): SharedPreferences.Editor? = sharedPreferences.edit().apply {
        putInt(THEME, themeId)
        apply()
    }
    fun setGrid(count: Int): SharedPreferences.Editor? = sharedPreferences.edit().apply{
        putInt(COLUMN_COUNT, count)
        apply()
    }
    fun getGrid() = sharedPreferences.getInt(COLUMN_COUNT,1)
    fun getTheme() = sharedPreferences.getInt(THEME, R.string.default_theme)

}