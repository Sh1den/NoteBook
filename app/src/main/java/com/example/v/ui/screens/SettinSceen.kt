package com.example.v.ui.screens
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
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
import com.example.v.MainActivity
import com.example.v.R
import com.example.v.data.model.ColorTheme
import com.example.v.data.model.GridColumn
import com.example.v.data.model.TextStyleFontFamilyTheme
import com.example.v.data.model.TextStyleSpTheme
import com.example.v.ui.navigation.NavigationItems
import com.example.v.data.model.getSecondLanguage
import com.example.v.ui.components.GridBottom
import com.example.v.ui.components.LanguageButton
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.ui.components.SettingAlignment
import com.example.v.ui.components.SettingCard
import com.example.v.ui.components.ThemeBottom
import com.example.v.ui.components.TopographyFontBottom
import com.example.v.ui.components.TopographySpBottom
import com.example.v.ui.navigation.Route
import com.example.v.ui.theme.typetheme.MaterialCurrentTheme
import com.example.v.ui.viewmodels.SettingViewModel

@Composable
fun SettingsScreen(
    navController: NavController
){
    val settingViewModel: SettingViewModel = hiltViewModel()
    val currentTheme by settingViewModel.theme.collectAsState()
    val currentGrid by settingViewModel.gridType.collectAsState()
    val currentTopography by settingViewModel.topography.collectAsState()
    val scroll = rememberScrollState()
    Scaffold(
        contentColor = MaterialCurrentTheme.colorSchema.onBackground,
        containerColor = MaterialCurrentTheme.colorSchema.background,
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
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(scroll)
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            SettingCard(R.string.general) {
                SettingAlignment(R.drawable.outline_contrast_24,stringResource(R.string.thema_app),
                    stringResource(currentTheme.idTheme)){
                    Column {
                        ThemeBottom(ColorTheme.Default)
                        ThemeBottom(ColorTheme.Light)
                        ThemeBottom(ColorTheme.Dark)
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.padding(start = 54.dp, end = 12.dp),
                    color = MaterialCurrentTheme.colorSchema.labelComponent.copy(alpha = 0.3f)
                )
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
                Column() {
                    SettingAlignment(
                        R.drawable.outline_space_dashboard_24, stringResource(R.string.view),
                        stringResource(currentGrid.type)
                    ) {
                        Column {
                            GridBottom(GridColumn.ContinuesColumn, settingViewModel)
                            GridBottom(GridColumn.TwoColumn, settingViewModel)
                            GridBottom(GridColumn.ThreeColumn, settingViewModel)
                        }
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 54.dp, end = 12.dp),
                        color = MaterialCurrentTheme.colorSchema.labelComponent.copy(alpha = 0.3f)
                    )
                    SettingAlignment(
                        R.drawable.outline_format_size_24,
                        primaryText = stringResource(R.string.font_size),
                        stringResource(currentTopography.textStyleSpTheme.value)
                    ) {
                        Column() {
                            TopographySpBottom(TextStyleSpTheme.Small)
                            TopographySpBottom(TextStyleSpTheme.Medium)
                            TopographySpBottom(TextStyleSpTheme.Large)
                        }
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(start = 54.dp, end = 12.dp),
                        color = MaterialCurrentTheme.colorSchema.labelComponent.copy(alpha = 0.3f)
                    )
                    SettingAlignment(
                        R.drawable.outline_font_download_24,
                        primaryText = stringResource(R.string.font_type),
                        stringResource(currentTopography.textStyleFontFamilyTheme.value)
                    ) {
                        Column() {
                            TopographyFontBottom(TextStyleFontFamilyTheme.Default)
                            TopographyFontBottom(TextStyleFontFamilyTheme.PlayfairDisplay)
                            TopographyFontBottom(TextStyleFontFamilyTheme.FiraSans)
                            TopographyFontBottom(TextStyleFontFamilyTheme.Montserrat)
                            TopographyFontBottom(TextStyleFontFamilyTheme.Ubuntu)
                        }
                    }
                }
            }
        }

    }
}