package com.example.v.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import com.example.v.data.model.Category
import com.example.v.data.model.Folder
import com.example.v.ui.viewmodels.FoldersViewModel
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.example.v.ui.theme.WarningColor

@Composable
fun CustomDialog(
    showDialog: MutableState<Boolean>,
    nameNewCategory: MutableState<String>,
    paddingValues: PaddingValues,
    foldersViewModel: FoldersViewModel
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
        onDismissRequest = { showDialog.value = false }
    ) {
        Box(
            modifier = Modifier.padding(paddingValues),
            contentAlignment = Alignment.TopStart
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .width(100.dp),
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
                    CastOutlineTextField(nameNewCategory,focusColor,unfocusColor)
                    Spacer(Modifier.size(22.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween
                    ) {
                        CastTextClickable(stringResource(R.string.cancel)){
                            showDialog.value = false
                        }
                        CastTextClickable(stringResource(R.string.save)) {
                            if(nameNewCategory.value.length == 0) nameValid = false
                            else {
                                nameValid = true
                                foldersViewModel.insertFolder(Folder(category = Category().apply {
                                    this.toCategory(
                                        nameNewCategory.value
                                    )
                                }))
                                showDialog.value = false
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
    nameNewCategory: MutableState<String>,
    focusColor: Color,
    unfocusColor: Color
){
    OutlinedTextField(
        singleLine = true,
        label = {
            Text(
                text = stringResource(R.string.name),
                color = MaterialTheme.colorScheme.tertiary
            )
        },
        onValueChange = {
            nameNewCategory.value = it
        },
        value = nameNewCategory.value,
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedBorderColor = focusColor,
            unfocusedBorderColor = unfocusColor,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White
        )
    )
}
@Composable
fun CastTextClickable(
    text: String,
    onClick: () -> Unit
){
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
){
    folder?.let {
        val animateLongClick by animateColorAsState(
            targetValue = when(isSelected() && !isRename()){
                false -> MaterialTheme.colorScheme.surface
                true -> Color(0xFF74C0FC)
            }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(vertical = 5.dp)
                .combinedClickable(onLongClick = {
                    combinedClickable()
                }) {
                    if (!isRename()) onClick()
                },
            shape = RoundedCornerShape(7.dp),
            elevation = CardDefaults.cardElevation(7.dp),
            colors = CardDefaults.cardColors(
                containerColor = animateLongClick
            )
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.size(7.dp))
                Image(
                    painter = painterResource(R.drawable.outline_folder_24),
                    modifier = Modifier.size(width = 45.dp, height = 55.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)))
                Spacer(Modifier.size(17.dp))
                if (isRename() && isSelected()) {
                    val keyboardController = LocalSoftwareKeyboardController.current
                    val focusRequester = remember { FocusRequester() }
                    var newName by remember { mutableStateOf("") }
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
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        modifier = Modifier.focusRequester(focusRequester)
                    )
                }
                else{
                    Text(
                        text = it.category.stringCategory,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}