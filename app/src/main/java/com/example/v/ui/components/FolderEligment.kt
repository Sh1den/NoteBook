package com.example.v.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.v.R
import com.example.v.data.model.Folder
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.v.ui.theme.WarningColor

@Composable
fun CustomDialog(
    onValueChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    nameNewCategory: String,
    onSaveClick: () -> Unit
){
    var nameValid by remember { mutableStateOf(true) }
    val focusColor by animateColorAsState(
        if (nameValid) MaterialTheme.colorScheme.tertiary
        else WarningColor
    )
    val unfocusColor by animateColorAsState(
        if (nameValid) Color.Gray
        else Color.Red
    )
    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Box(
            contentAlignment = Alignment.TopStart
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                shape = RoundedCornerShape(28.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                ) {
                    Spacer(Modifier.size(10.dp))
                    Text(
                        text = stringResource(R.string.new_packege),
                        modifier = Modifier.padding(
                            horizontal = 15.dp,
                            vertical = 5.dp
                        ),
                        fontSize = 19.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    CastOutlineTextField(nameNewCategory,focusColor,unfocusColor){
                        onValueChange(it)
                        nameValid = true
                    }
                    Spacer(Modifier.size(22.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween
                    ) {
                        CastTextClickable(stringResource(R.string.cancel)){
                            onDismissRequest()
                        }
                        CastTextClickable(stringResource(R.string.save)) {
                            if(nameNewCategory.isNotBlank()){
                                nameValid = true
                                onSaveClick()
                            }
                            else{
                                nameValid = false
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun CastOutlineTextField(
    nameNewCategory: String,
    focusColor: Color,
    unfocusColor: Color,
    onValueChange :  (String) -> Unit
){
    OutlinedTextField(
        singleLine = true,
        label = {
            Text(
                text = stringResource(R.string.name),
                color = MaterialTheme.colorScheme.tertiary
            )
        },
        onValueChange = onValueChange,
        value = nameNewCategory,
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedBorderColor = focusColor,
            unfocusedBorderColor = unfocusColor
        )
    )
}
@Composable
fun CastTextClickable(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        modifier = Modifier.clickable(onClick = onClick),
        fontSize = 16.sp,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
fun FolderCard(
    folder: Folder,
    isSelected: () -> Boolean,
    isRename: () -> Boolean,
    toRename: (text: String) -> Unit,
    combinedClickable: () -> Unit,
    onClick: () -> Unit
) {
    val animateLongClick by animateColorAsState(
        targetValue = when (isSelected() && !isRename()) {
            false -> MaterialTheme.colorScheme.tertiary
            true -> Color(0xFF74C0FC)
        }
    )
    Card(
        shape = RoundedCornerShape(7.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = animateLongClick
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .combinedClickable(onLongClick = {
                    combinedClickable()
                }) {
                    if (!isRename()) onClick()
                }
        ){
            Row(
                modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 60.dp).padding(start = 12.dp, top = 7.dp, end = 3.dp, bottom = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.open_folder_),
                    modifier = Modifier.size(40.dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(12.dp))
                if (isRename() && isSelected()) {
                    val keyboardController = LocalSoftwareKeyboardController.current
                    val focusRequester = remember { FocusRequester() }
                    var newName by remember { mutableStateOf(folder.name) }
                    LaunchedEffect(Unit) {
                        focusRequester.requestFocus()
                        keyboardController?.show()
                    }
                    OutlinedTextField(
                        value = newName,
                        onValueChange = {
                            newName = it
                            toRename(newName)
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                } else {
                    Column() {
                        Text(
                            text = folder.name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = stringResource(R.string.notes_counter,folder.countNotes),
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }

    }
}