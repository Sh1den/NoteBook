package com.example.v.ui.components

import android.content.Context.MODE_PRIVATE
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.v.LocalSharedStateTheme
import com.example.v.MainActivity
import com.example.v.data.model.Theme
import com.example.v.data.model.TypeSetting
import com.example.v.data.model.getLang
import com.example.v.ui.viewmodels.MainViewModel

@Composable
fun SettingEligment(
    painter: Int,
    primaryText: String,
    type: TypeSetting
){
    val secondaryText = when(type){
        TypeSetting.Theme -> LocalSharedStateTheme.current.getTheme(AppCompatDelegate.getApplicationLocales()[0]?.language ?: "ru")
        TypeSetting.Language -> getLang(AppCompatDelegate.getApplicationLocales()[0]?.language ?: "ru")
    }
    var optionOpen by remember { mutableStateOf(false) }
        TextButton(
            modifier = Modifier.fillMaxWidth().height(70.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            onClick = {
                optionOpen = !optionOpen
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(painter = painterResource(painter), contentDescription = null)
                Spacer(Modifier.size(15.dp))
                Column() {
                    Text("${primaryText}")
                    Text(
                        text = "${secondaryText}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    AnimatedVisibility(
        visible = optionOpen,
        enter = fadeIn(tween(500,100))+expandVertically(tween(600,100, easing = FastOutSlowInEasing)),
        exit = fadeOut(tween(500,100))+shrinkVertically(tween(600,100, easing = FastOutSlowInEasing))
    ) {
        when(type){
            TypeSetting.Theme -> SettingTheme()
            TypeSetting.Language -> SettingLanguage()
        }
    }
}
@Composable
fun SettingTheme(){
    val activity = LocalContext.current
    val mainViewModel: MainViewModel = hiltViewModel(activity as MainActivity)
    Column() {
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                mainViewModel.setTheme("dark", Theme.ColorTheme.Dark)
            }
        ) {
            Text("Темная")
        }
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                mainViewModel.setTheme("light", Theme.ColorTheme.Light)
            }) {
            Text("Светлая")
        }
    }
}
@Composable
fun SettingLanguage(){
    Column() {
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val localeListCompat = LocaleListCompat.forLanguageTags("ru")
                AppCompatDelegate.setApplicationLocales(localeListCompat)
            }
        ) {
            Text("Русский")
        }
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val localeListCompat = LocaleListCompat.forLanguageTags("eu")
                AppCompatDelegate.setApplicationLocales(localeListCompat)
            }) {
            Text("Английский")
        }
    }
}
