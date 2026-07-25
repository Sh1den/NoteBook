package com.example.v.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.v.data.model.Category
import com.example.v.data.model.Folder
import com.example.v.data.model.NavigationItems
import com.example.v.data.model.Note
import com.example.v.ui.navigation.Route
import com.example.v.ui.theme.paletteColors
import com.example.v.ui.viewmodels.FoldersViewModel
import com.example.v.ui.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationTopAppBar(
    titleWidget:  @Composable (() -> Unit) = {},
    titleBar: String? = null,
    navIcons: NavigationItems? = null,
    actionIcons: MutableList<NavigationItems>? = null,
    actionText: MutableList<String>? = null,
    onActionsClicks: List<(() -> Unit)>? = null,
    colorCont: Color? = null,
    onNavClick: () -> Unit = {}
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorCont ?: Color.Unspecified,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        title = {

            titleBar?.let {
                Text(it)
            }
            titleWidget()
        },
        navigationIcon = {
            Row() {
                navIcons?.let {
                    CastIconButton(navIcons.imageVector,navIcons.painter,onNavClick)
                    Spacer(modifier = Modifier.size(10.dp)) }
            }
        },
        actions = {
            actionIcons?.let {
                it.forEachIndexed() {ind,items ->   CastIconButton(items.imageVector, items.painter,onActionsClicks?.getOrNull(ind) ?: {})}
            }
            actionText?.let {
                Row() {
                    it.forEachIndexed { index, string ->
                        Text(
                            text = string,
                            modifier = Modifier.clickable{
                                onActionsClicks?.let {
                                    it[index]()
                                }
                            }
                        )
                        Spacer(Modifier.size(30.dp))
                    }
                }
            }
        }
    )
}
@Composable
fun CastIconButton(
    imVect: ImageVector? = null,
    painter: Int? = null,
    onClick: () -> Unit
){
    IconButton(
        onClick = onClick
    ) {
        imVect?.let { Icon(
            imageVector = imVect, contentDescription = null
        ) }
        painter?.let { Icon(
            painter = painterResource(painter), contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        ) }
    }
}
@Composable
fun CastFloatingActionButton(
    modif: Modifier,
    iconModif: Modifier,
    shape: Shape,
    onClick: () -> Unit = {}
)
{
    FloatingActionButton(
        modifier = modif,
        onClick = onClick,
        shape = shape,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.background
    ) {
       Icon(
           imageVector = Icons.Default.Add,
           contentDescription = null,
           modifier = iconModif
       )
    }
}

@Composable
fun NoteCard(
    note: Note?,
    navController: NavController,
    isSelected: () -> Boolean,
    combinedClickable: () -> Unit,
    onClick: () -> Unit
){
    note?.let {
        val animateLongClick by animateColorAsState(
            targetValue = when(isSelected()){
                false -> {
                    if (note.color == null) MaterialTheme.colorScheme.surface
                    else note.color
                }
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
                    onClick()
                },
            shape = RoundedCornerShape(7.dp),
            elevation = CardDefaults.cardElevation(7.dp),
            colors = CardDefaults.cardColors(
                containerColor = animateLongClick
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = if(it.title.length == 0) it.text.take(10) else it.title,
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.size(5.dp))
                Text(
                    text = it.time,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    }
}

@Composable
fun GetNotes(
    mainViewModel: MainViewModel,
    navController: NavController,
    paddingValues: PaddingValues,
    selectedNote: SnapshotStateList<Note>
){
    val notesPagging = mainViewModel.tableRepository.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 7.dp)
    ) {
        items(
            count = notesPagging.itemCount,
            key = notesPagging.itemKey { Note ->  Note.id}
        ){
            val note = notesPagging[it]
            note?.let {
                NoteCard(note,navController,{ selectedNote.contains(it) },
                    {
                        if (!selectedNote.contains(it)) selectedNote.add(it)
                    }
                ) {
                    if (selectedNote.size != 0 && !selectedNote.contains(it)) selectedNote.add(it)
                    else if (selectedNote.contains(it))  selectedNote.remove(it)
                    else {
                        navController.navigate(Route.NoteScreen(it.id,it.category.categoryId,it.category.stringCategory))
                    }
                }
            }
        }
    }
}

@Composable
fun GetFolders(
    foldersViewModel: FoldersViewModel,
    navController: NavController,
    paddingValues: PaddingValues,
    selectedFolder: SnapshotStateList<Folder>,
    isRename: MutableState<Boolean>,
    newFolder: MutableState<Folder>
){
    val pagingFolders = foldersViewModel.folders.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 7.dp)
    ) {
        items(
            count = pagingFolders.itemCount,
            key = pagingFolders.itemKey {it.id }
        ){
            val folder = pagingFolders[it]
            folder?.let {
                thFolder ->
                FolderCard(
                    thFolder,
                    { selectedFolder.contains(thFolder) },
                    {isRename.value},
                    { newFolder.value = Folder(id = thFolder.id, category = Category().apply{this.toCategory(it,thFolder.id)})},
                    { if(!selectedFolder.contains(thFolder)) selectedFolder.add(thFolder) }) {
                    if (selectedFolder.size != 0 && !selectedFolder.contains(thFolder)) selectedFolder.add(thFolder)
                    else if(selectedFolder.contains(thFolder))selectedFolder.remove(thFolder)
                    else {
                        navController.navigate(Route.FolderNotes(thFolder.category.stringCategory,thFolder.category.categoryId))
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomColors(
    bottomIsOpen: MutableState<Boolean>,
    mainViewModel: MainViewModel,
    selectedNote: SnapshotStateList<Note>,
    isBottomOpen: MutableState<Boolean>
){
    ModalBottomSheet(
        onDismissRequest = {
            bottomIsOpen.value = false
        },
        dragHandle = null,
        modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(17.dp)
    ) {
        Spacer(Modifier.size(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Цвет заметки",
                fontSize = 20.sp,
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(11.dp),
                contentPadding = PaddingValues(19.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(paletteColors){
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = it
                        ),
                        modifier = Modifier
                            .size(37.dp)
                            .clickable {
                                mainViewModel.colorChange(selectedNote[0].copy(color = it))
                                selectedNote.clear()
                                bottomIsOpen.value = false
                            },
                        shape = RoundedCornerShape(8.dp)
                    ) {
                    }
                }
            }
        }
    }
}