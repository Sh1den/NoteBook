package com.example.v.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.v.R
import com.example.v.data.model.NavigationItems
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.ui.components.SettingEligment
import com.example.v.ui.navigation.Route
import com.example.v.ui.navigation.isTopLevelRoute

@Composable
fun SettingsScreen(
    navController: NavController,
    onClick: () -> Unit
){

    Scaffold(
        contentColor = MaterialTheme.colorScheme.onSurface,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            NavigationTopAppBar("Настройки", NavigationItems.Back,null){
                navController.navigate(Route.HomeScreen)
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it).padding(start = 15.dp)
        ) {
            Text(
                text = "Общие",
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
                SettingEligment(R.drawable.outline_contrast_24,"Тема приложения","По умолчанию")
                HorizontalDivider()
                SettingEligment(R.drawable.outline_language_24,"Язык приложения","Russin (Русский)")
            }
        }

    }
}