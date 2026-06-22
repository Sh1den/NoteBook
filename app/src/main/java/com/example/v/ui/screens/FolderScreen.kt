package com.example.v.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.v.data.model.NavigationItems
import com.example.v.ui.components.NavigationTopAppBar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun FolderScreen(
    onClick: () -> Unit
){
    var showDialog by remember { mutableStateOf(false) }
    Log.d("showDialog",showDialog.toString())
    Scaffold(
        topBar = {
            NavigationTopAppBar("Архивы заметок", NavigationItems.Menu,mutableListOf(NavigationItems.NewFolder,
                NavigationItems.Search),listOf({showDialog = true},{}),onClick)
        }
    ) {
        if(showDialog){
            AlertDialog(
                onDismissRequest = {showDialog = false},
                modifier = Modifier.padding(it)
            ){
                Card(
                    shape = RoundedCornerShape(10.dp)
                ) {

                }
            }
        }
    }
}