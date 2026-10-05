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

        private const val TOPOGRAPHY_SP = "topography_sp"
        private const val TOPOGRAPHY_FONT = "topography_font"
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

    fun setTopographySp(newSp: Int) {
        sharedPreferences.edit().putInt(TOPOGRAPHY_SP, newSp).apply()
    }

    fun setTopographyFont(newFont: Int) {
        sharedPreferences.edit().putInt(TOPOGRAPHY_FONT, newFont).apply()
    }

    fun getGrid(): Int = try {
        sharedPreferences.getInt(COLUMN_COUNT, 1)
    } catch (e: Exception) {
        1
    }

    fun getTheme(): Int = try {
        sharedPreferences.getInt(THEME, R.string.default_theme)
    } catch (e: Exception) {
        R.string.default_theme
    }

    fun getTopographySp(): Int = try {
        sharedPreferences.getInt(TOPOGRAPHY_SP, R.string.small_font)
    } catch (e: Exception) {
        R.string.small_font
    }

    fun getTopographyFont(): Int = try {
        sharedPreferences.getInt(TOPOGRAPHY_FONT, R.string.default_font)
    } catch (e: Exception) {
        R.string.default_font
    }
}