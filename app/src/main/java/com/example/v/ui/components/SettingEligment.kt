package com.example.v.ui.components


import android.util.Log
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.LocaleListCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.v.LocalSharedStateTheme
import com.example.v.MainActivity
import com.example.v.R
import com.example.v.data.model.ColorTheme
import com.example.v.data.model.GridColumn
import com.example.v.data.model.getSecondLanguage
import com.example.v.ui.viewmodels.SettingViewModel


@Composable
fun SettingCard(
    titleId: Int,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
){
    Column {
        Text(
            text = stringResource(titleId),
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 10.dp)
        )

        Card(
            modifier = modifier.width(370.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            content = content,
            elevation = CardDefaults.cardElevation(2.dp)
        )
    }
}
@Composable
fun SettingAlignment(
    painter: Int,
    primaryText: String,
    secondText: String,
    content: @Composable (AnimatedVisibilityScope.() -> Unit)
){
    var optionOpen by remember { mutableStateOf(false) }
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
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
                Column {
                    Text(primaryText)
                    Text(
                        text = secondText,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    AnimatedVisibility(
        visible = optionOpen,
        enter = fadeIn(tween(500,100))+expandVertically(tween(600,100, easing = FastOutSlowInEasing)),
        exit = fadeOut(tween(500,100))+shrinkVertically(tween(600,100, easing = FastOutSlowInEasing)),
        content = content
    )
}

@Composable
fun GridBottom(
    currentGrid: GridColumn,
    settingViewModel: SettingViewModel
){
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            settingViewModel.setGridLayout(currentGrid)
        }
    ) {
        Text(stringResource(currentGrid.type))
    }
}
@Composable
fun LanguageButton(
    languageName: String,
    languageTabs: String
) {
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            val localeListCompat = LocaleListCompat.forLanguageTags(languageTabs)
            AppCompatDelegate.setApplicationLocales(localeListCompat)
        }
    ) {
        Text(languageName)
    }
}

@Composable
fun ThemeBottom(
    currentTheme: ColorTheme,
    settingViewModel: SettingViewModel
){
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            settingViewModel.setTheme(currentTheme)
        }
    ) {
        Text(stringResource(currentTheme.idTheme))
    }
}
