package com.example.v

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.v.data.model.Theme
import com.example.v.ui.navigation.NavAppGraph
import com.example.v.ui.theme.VTheme
import com.example.v.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

val LocalSharedStateTheme = staticCompositionLocalOf<Theme>{error("")}
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainViewModel: MainViewModel = hiltViewModel()
            val stateTheme by mainViewModel.theme.collectAsState()
            VTheme(settingsTheme = stateTheme) {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalSharedStateTheme provides stateTheme) {
                    NavAppGraph(navController = navController)
                }
            }
        }
    }
}
