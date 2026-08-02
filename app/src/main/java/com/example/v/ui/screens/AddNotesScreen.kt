package com.example.v.ui.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.v.R
import com.example.v.ui.navigation.NavigationItems
import com.example.v.ui.components.CastTextField
import com.example.v.ui.components.NavigationTopAppBar
import com.example.v.ui.viewmodels.EditNoteViewModel

@Composable
fun AddNoteScreen(
    navController: NavController,
    editNoteViewModel: EditNoteViewModel
) {
    val note by editNoteViewModel.thNote.collectAsStateWithLifecycle()
    var isInitialized by remember { mutableStateOf(true) }
    val title = rememberTextFieldState("")
    val description = rememberTextFieldState("")
    LaunchedEffect(note) {
            title.setTextAndPlaceCursorAtEnd(note.title)
            description.setTextAndPlaceCursorAtEnd(note.text)
    }
    Scaffold(
        topBar = {
            NavigationTopAppBar(
                navIcons = NavigationItems.Back,
                actionIcons = mutableListOf(NavigationItems.Ok),
                onNavClick = {
                    navController.popBackStack()
                },
                colorCont = MaterialTheme.colorScheme.background,
                onActionsClicksIcons = listOf {
                    if (description.text.toString().trim().isNotEmpty() or title.text.toString().trim().isNotEmpty()) {
                        editNoteViewModel.saveNote(
                            title.text.toString(),
                            description.text.toString()
                        )
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(20.dp)
        ) {

            CastTextField(
                title,
                stringResource(R.string.title_note),
                TextFieldLineLimits.SingleLine
            )
            Spacer(Modifier.size(15.dp))
            Row() {
                Text(
                    text = editNoteViewModel.thTime ?: "",
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp
                )
                Spacer(Modifier.size(25.dp))
                Text(
                    text = description.text.length.toString() + " " + stringResource(R.string.symbols_note),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 12.sp
                )
            }
            Spacer(Modifier.size(15.dp))
            CastTextField(
                description,
                stringResource(R.string.description_note),
                TextFieldLineLimits.MultiLine()
            )
        }
    }
}
