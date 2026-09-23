package com.example.v.ui.screens
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.v.LocalSharedStateTheme
import com.example.v.MainActivity
import com.example.v.R
import com.example.v.data.model.ColorTheme
import com.example.v.data.model.GridColumn
import com.example.v.ui.navigation.NavigationItems
import com.example.v.data.model.getSecondLanguage
import com.example.v.ui.components.GridBottom
import com.example.v.ui.components.LanguageButton
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.ui.components.SettingAlignment
import com.example.v.ui.components.SettingCard
import com.example.v.ui.components.ThemeBottom
import com.example.v.ui.navigation.Route
import com.example.v.ui.viewmodels.SettingViewModel

@Composable
fun SettingsScreen(
    navController: NavController
){
    Scaffold(
        contentColor = MaterialTheme.colorScheme.onSurface,
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            NavigationTopAppBar({},stringResource(R.string.setting), NavigationItems.Back,null){
                navController.navigate(Route.HomeScreen){
                    launchSingleTop = true
                    popUpTo(navController.graph.startDestinationId){
                        saveState = true
                    }
                    restoreState = true
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it).padding(start = 15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            val activity = LocalContext.current
            val settingViewModel: SettingViewModel = hiltViewModel(activity as MainActivity)
            SettingCard(R.string.general) {
                SettingAlignment(R.drawable.outline_contrast_24,stringResource(R.string.thema_app),
                    stringResource(LocalSharedStateTheme.current.idTheme)){
                    Column {
                        ThemeBottom(ColorTheme.Light,settingViewModel)
                        ThemeBottom(ColorTheme.Dark,settingViewModel)
                    }
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.onTertiary)
                SettingAlignment(R.drawable.outline_language_24,stringResource(R.string.language_app),
                    getSecondLanguage(AppCompatDelegate.getApplicationLocales()[0]?.language ?: stringResource(R.string.language_tabs_rus))
                ) {
                    Column {
                        LanguageButton(
                            stringResource(R.string.language_rus),
                            stringResource(R.string.language_tabs_rus)
                        )
                        LanguageButton(
                            stringResource(R.string.language_en),
                            stringResource(R.string.language_tabs_eu)
                        )
                    }
                }
            }
            SettingCard(titleId = R.string.display) {
                val currentGrid by settingViewModel.gridType.collectAsState()
                SettingAlignment(R.drawable.outline_space_dashboard_24,stringResource(R.string.view),
                    stringResource(currentGrid.type)
                ) {
                    Column{
                        GridBottom(GridColumn.ContinuesColumn,settingViewModel)
                        GridBottom(GridColumn.TwoColumn,settingViewModel)
                        GridBottom(GridColumn.ThreeColumn,settingViewModel)
                    }

                }
            }
        }

    }
}