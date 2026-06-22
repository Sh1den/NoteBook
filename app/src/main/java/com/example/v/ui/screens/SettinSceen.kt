package com.example.v.ui.screens
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.v.LocalSharedStateTheme
import com.example.v.R
import com.example.v.data.model.NavigationItems
import com.example.v.data.model.TypeSetting
import com.example.v.data.model.getLang
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.ui.components.SettingEligment
import com.example.v.ui.navigation.Route

@Composable
fun SettingsScreen(
    navController: NavController,
    onClick: () -> Unit
){
    Scaffold(
        contentColor = MaterialTheme.colorScheme.onSurface,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            NavigationTopAppBar(stringResource(R.string.setting), NavigationItems.Back,null){
                navController.navigate(Route.HomeScreen)
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it).padding(start = 15.dp)
        ) {
            Text(
                text = stringResource(R.string.general),
                fontSize = 12.sp,
                color = Color.Gray
            )
            Card(
                modifier = Modifier.width(370.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiary
                )
            ) {
                SettingEligment(R.drawable.outline_contrast_24,stringResource(R.string.thema_app),LocalSharedStateTheme.current.value.getTheme(AppCompatDelegate.getApplicationLocales()[0]?.language ?: "ru"),
                    TypeSetting.Theme)
                HorizontalDivider(color = MaterialTheme.colorScheme.onTertiary)
                SettingEligment(R.drawable.outline_language_24,stringResource(R.string.language_app),
                    getLang(AppCompatDelegate.getApplicationLocales()[0]?.language ?: "ru"),
                    TypeSetting.Language
                )
            }
        }

    }
}