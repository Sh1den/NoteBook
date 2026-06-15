package com.example.v

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.v.ui.navigation.NavAppGraph
import com.example.v.ui.theme.VTheme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.v.data.model.Language
import com.example.v.data.model.Theme
import dagger.hilt.android.AndroidEntryPoint

val LocalSharedStateTheme = staticCompositionLocalOf<MutableState<Theme>>{error("")}
val LocalSharedStateLanguage = staticCompositionLocalOf<MutableState<Language>> { error("") }
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var settingsTheme =  remember { mutableStateOf(Theme()) }
            var settingLanguage = remember { mutableStateOf(Language()) }
            VTheme(settingsTheme = settingsTheme.value) {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalSharedStateTheme provides settingsTheme,LocalSharedStateLanguage provides settingLanguage) {
                    NavAppGraph(navController = navController)
                }
            }
        }
    }
}
