package com.example.v

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.ui.platform.LocalContext
import com.example.v.data.model.Theme
import dagger.hilt.android.AndroidEntryPoint
val LocalSharedStateTheme = staticCompositionLocalOf<MutableState<Theme>>{error("")}
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val sharedPreferences = this.applicationContext.getSharedPreferences("settings_param",MODE_PRIVATE)
            val theme = sharedPreferences.getString("theme",null)
            var settingsTheme =  remember { mutableStateOf(Theme(theme)) }
            VTheme(settingsTheme = settingsTheme.value) {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalSharedStateTheme provides settingsTheme) {
                    NavAppGraph(navController = navController)
                }
            }
        }
    }
}
