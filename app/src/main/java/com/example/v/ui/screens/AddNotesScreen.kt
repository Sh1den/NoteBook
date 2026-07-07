package com.example.v.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.v.R
import com.example.v.data.model.NavigationItems
import com.example.v.ui.components.CastTextField
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.ui.navigation.Route
import com.example.v.ui.viewmodels.MainViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun AddNoteScreen(
    navController: NavController
){
    val mainViewModel: MainViewModel = hiltViewModel()
    val title = rememberTextFieldState("")
    val description = rememberTextFieldState("")
    var lenText = remember{ mutableStateOf(0) }
    val thTime = remember { mutableStateOf(LocalDateTime.now()) }
    val formater = DateTimeFormatter.ofPattern("d MMMM, H:m",Locale.getDefault())
    Scaffold(
        topBar = {
            NavigationTopAppBar(
                navIcons = NavigationItems.Back,
                actionIcons = mutableListOf(NavigationItems.Ok),
                onNavClick = {navController.navigate(Route.HomeScreen)},
                colorCont = MaterialTheme.colorScheme.background
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
                .fillMaxSize().padding(20.dp)
        ) {

            CastTextField(
                title,
                stringResource(R.string.title_note),
                TextFieldLineLimits.SingleLine
            )
            Spacer(Modifier.size(15.dp))
            Row() {
                Text(
                    text = formater.format(thTime.value),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp
                )
                Spacer(Modifier.size(25.dp))
                Text(
                    text = lenText.value.toString() + " " + stringResource(R.string.symbols_note),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp
                )
            }
            Spacer(Modifier.size(15.dp))
            CastTextField(
                description,
                stringResource(R.string.description_note),
                TextFieldLineLimits.MultiLine(),
                lenText
            )
        }
    }
}