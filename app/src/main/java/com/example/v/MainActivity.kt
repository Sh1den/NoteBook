package com.example.v

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.v.ui.navigation.NavAppGraph
import com.example.v.ui.theme.VTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.v.data.model.ColorTheme
import com.example.v.ui.viewmodels.SettingViewModel

val LocalSharedStateTheme = staticCompositionLocalOf<ColorTheme>{error("")}
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val settingViewModel: SettingViewModel = hiltViewModel()
            val stateTheme by settingViewModel.theme.collectAsState()
            VTheme(colorTheme = stateTheme) {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalSharedStateTheme provides stateTheme) {
                    NavAppGraph(navController = navController)
                }
            }
        }
    }
}
