package com.example.v.ui.components

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.example.v.data.model.ColorTheme
import com.example.v.data.model.GridColumn
import com.example.v.ui.theme.typetheme.MaterialCurrentTheme
import com.example.v.ui.viewmodels.SettingViewModel

@Composable
fun SettingCard(
    titleId: Int,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
){
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = stringResource(titleId),
            style = MaterialCurrentTheme.topography.labelLargeText,
            color = MaterialCurrentTheme.colorSchema.labelComponent,
            modifier = Modifier.padding(start = 10.dp)
        )

        Card(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialCurrentTheme.colorSchema.component
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
            containerColor = MaterialCurrentTheme.colorSchema.component,
            contentColor = MaterialCurrentTheme.colorSchema.onComponent
        ),
        onClick = {
            optionOpen = !optionOpen
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(painter),
                contentDescription = null,
                tint = MaterialCurrentTheme.colorSchema.componentIcon
            )
            Column {
                Text(
                    text = primaryText,
                    color = MaterialCurrentTheme.colorSchema.onComponent
                )
                Text(
                    text = secondText,
                    color = MaterialCurrentTheme.colorSchema.labelComponent
                )
            }
        }
    }
    AnimatedVisibility(
        visible = optionOpen,
        enter = fadeIn(tween(500,100)) + expandVertically(tween(600,100, easing = FastOutSlowInEasing)),
        exit = fadeOut(tween(500,100)) + shrinkVertically(tween(600,100, easing = FastOutSlowInEasing)),
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
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialCurrentTheme.colorSchema.primary
        ),
        onClick = {
            settingViewModel.setGridLayout(currentGrid)
        }
    ) {
        Text(
            text = stringResource(currentGrid.type),
        )
    }
}

@Composable
fun LanguageButton(
    languageName: String,
    languageTabs: String
) {
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialCurrentTheme.colorSchema.primary
        ),
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
    val themeController = MaterialCurrentTheme.themeUpdater
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialCurrentTheme.colorSchema.primary
        ),
        onClick = {
            themeController(currentTheme)
        }
    ) {
        Text(stringResource(currentTheme.idTheme))
    }
}
