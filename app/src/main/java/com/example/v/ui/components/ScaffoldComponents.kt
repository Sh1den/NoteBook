package com.example.v.ui.components

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.selection.selectable
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.v.data.model.Category
import com.example.v.data.model.Folder
import com.example.v.ui.navigation.NavigationItems
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
    onActionsClicksText: List<(() -> Unit)>? = null,
    onActionsClicksIcons: List<(() -> Unit)>? = null,
    colorCont: Color? = null,
    onNavClick: () -> Unit = {}
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
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
                it.forEachIndexed() {ind,items ->   CastIconButton(items.imageVector, items.painter,onActionsClicksIcons?.getOrNull(ind) ?: {})}
            }
            actionText?.let { it ->
                Row() {
                    it.forEachIndexed { index, string ->
                        Text(
                            text = string,
                            modifier = Modifier.clickable{
                                onActionsClicksText?.let {
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
    modifier: Modifier,
    iconModify: Modifier,
    shape: Shape,
    onClick: () -> Unit = {}
)
{
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        shape = shape,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.background
    ) {
       Icon(
           imageVector = Icons.Default.Add,
           contentDescription = null,
           modifier = iconModify
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
) {
    note?.let {
        val name = it.title.ifEmpty { it.text.take(10) }
        val animateLongClick by animateColorAsState(
            targetValue = when (isSelected()) {
                false -> {
                    note.color ?: Color.White
                }

                true -> Color(0xFF74C0FC)
            }
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = animateLongClick
            ),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(3.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .combinedClickable(
                        onLongClick = {
                            combinedClickable()
                        }) {
                        onClick()
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 80.dp)
                        .padding(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    Text(
                        text = name,
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.size(7.dp))
                    Text(
                        text = it.time,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
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
    val notesPaging = mainViewModel.tableRepository.collectAsLazyPagingItems()
    val gridType by mainViewModel.gridType.collectAsState()
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(gridType.countColumn),
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp,
        contentPadding = PaddingValues( horizontal = 10.dp)
    ) {
        items(
            count = notesPaging.itemCount,
            key = notesPaging.itemKey { it.id}
        ){ it ->
            val note = notesPaging[it]
            note?.let {
                NoteCard(note,navController,{ selectedNote.contains(it) },
                    {
                        if (!selectedNote.contains(it)) selectedNote.add(it)
                    }
                ) {
                    if(selectedNote.isNotEmpty()){
                        if(selectedNote.contains(it)) selectedNote.remove(it)
                        else selectedNote.add(it)
                    }
                    else {
                        if (mainViewModel.isEditNotes()) navController.navigate(Route.NoteScreen(it.id,it.categoryId))
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
    val gridType by foldersViewModel.gridType.collectAsState()
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(gridType.countColumn),
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalItemSpacing = 7.dp,
        contentPadding = PaddingValues( horizontal = 10.dp)
    ) {
        items(
            count = pagingFolders.itemCount,
            key = pagingFolders.itemKey {it.id }
        ){ it ->
            val folder = pagingFolders[it]
            folder?.let {
                thFolder ->
                FolderCard(
                    thFolder,
                    { selectedFolder.contains(thFolder) },
                    {isRename.value},
                    { newFolder.value = Folder(id = thFolder.id, name =  it, countNotes = thFolder.countNotes)},
                    { if(!selectedFolder.contains(thFolder)) selectedFolder.add(thFolder) }) {
                    if(selectedFolder.isNotEmpty()){
                        if(selectedFolder.contains(thFolder)) selectedFolder.remove(thFolder)
                        else selectedFolder.add(thFolder)
                    }
                    else {
                        navController.navigate(Route.FolderNotes(thFolder.name,thFolder.id))
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
    selectedNote: SnapshotStateList<Note>
) {
    ModalBottomSheet(
        onDismissRequest = {
            bottomIsOpen.value = false
        },
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
                items(paletteColors) { color ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = color
                        ),
                        modifier = Modifier
                            .size(37.dp)
                            .clickable {
                                selectedNote.firstOrNull()?.let { note ->
                                    mainViewModel.colorChange(note.copy(color = color))
                                }
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