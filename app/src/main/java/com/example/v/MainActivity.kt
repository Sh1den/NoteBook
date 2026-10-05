package com.example.v

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.v.ui.navigation.NavAppGraph
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.v.data.model.ColorTheme
import com.example.v.ui.theme.typetheme.CurrentTheme
import com.example.v.ui.theme.typetheme.toColorSchema
import com.example.v.ui.viewmodels.SettingViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingViewModel: SettingViewModel = hiltViewModel()
            val currentTheme by settingViewModel.theme.collectAsState()
            val currentTopography by settingViewModel.topography.collectAsState()
            CurrentTheme(
                currentTheme = currentTheme,
                settingTopography = currentTopography,
                onSaveColorTheme = {settingViewModel.saveTheme(it)},
                onSaveTopographySp = {settingViewModel.setTopographySp(it)},
                onSaveTopographyFont = {settingViewModel.setTopographyFont(it)}
            ) {
                val navController = rememberNavController()
                NavAppGraph(navController = navController)
            }
        }
    }
}
