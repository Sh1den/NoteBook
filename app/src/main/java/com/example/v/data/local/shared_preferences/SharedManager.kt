package com.example.v.data.local.shared_preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.v.data.model.Theme
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val THEME = "theme"
    }

    private val sharedPreferences = context.getSharedPreferences("settings_param",MODE_PRIVATE)
    fun setTheme(theme: Theme) = sharedPreferences.edit().apply {
        putString(THEME, theme.strTheme)
        apply()
    }
    fun getTheme() = sharedPreferences.getString(THEME, "")

}