package com.example.v.ui.components

import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.example.v.data.model.ColorTheme
import com.example.v.data.model.GridColumn
import com.example.v.data.model.TextStyleFontFamilyTheme
import com.example.v.data.model.TextStyleSpTheme
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(painter),
                    contentDescription = null,
                    tint = MaterialCurrentTheme.colorSchema.componentIcon,
                    modifier = Modifier.fillMaxHeight()
                )
                Column{
                    Text(
                        text = primaryText,
                        style = MaterialCurrentTheme.topography.labelMediumText,
                        color = MaterialCurrentTheme.colorSchema.onBackground
                    )
                    Text(
                        text = secondText,
                        style = MaterialCurrentTheme.topography.labelMediumText,
                        color = MaterialCurrentTheme.colorSchema.labelComponent
                    )
                }
            }
            Icon(
                imageVector = if(optionOpen) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = MaterialCurrentTheme.colorSchema.labelComponent
            )

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
    SettingBottom(stringResource(currentGrid.type)) {
        settingViewModel.setGridLayout(currentGrid)
    }
}

@Composable
fun LanguageButton(
    languageName: String,
    languageTabs: String
) {
    SettingBottom(languageName) {
        val localeListCompat = LocaleListCompat.forLanguageTags(languageTabs)
        AppCompatDelegate.setApplicationLocales(localeListCompat)
    }
}

@Composable
fun ThemeBottom(
    currentTheme: ColorTheme
){
    val themeController = MaterialCurrentTheme.themeUpdater
    SettingBottom(stringResource(currentTheme.idTheme)) {
        themeController(currentTheme)
    }
}

@Composable
fun TopographySpBottom(
    textStyleSpTheme: TextStyleSpTheme
){
    val textSpController = MaterialCurrentTheme.topographySpUpdater
    SettingBottom(stringResource(textStyleSpTheme.value)) {
        textSpController(textStyleSpTheme)
    }
}

@Composable
fun TopographyFontBottom(
    textStyleFontFamilyTheme: TextStyleFontFamilyTheme
){
    val textFontController = MaterialCurrentTheme.topographyFontUdapter
    SettingBottom(stringResource(textStyleFontFamilyTheme.value)) {
        textFontController(textStyleFontFamilyTheme)
    }
}
@Composable
fun SettingBottom(
    textBottom: String,
    onClick: () -> Unit
){
    TextButton(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialCurrentTheme.colorSchema.primary
        ),
        onClick = onClick
    ) {
        Text(
            text = textBottom,
            style = MaterialCurrentTheme.topography.labelMediumText
        )
    }
}
