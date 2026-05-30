package com.example.v.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.v.data.model.NavigationItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationTopAppBar(
    titleBar: String?,
    navIcons: NavigationItems,
    actionIcons: MutableList<NavigationItems>?,
    onActionsClicks: List<(() -> Unit)>? = null,
    onNavClick: () -> Unit
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        title = {Text(titleBar?:"")},
        navigationIcon = {
            Row() {
                CastIconButton(navIcons.imageVector,navIcons.painter,onNavClick)
                Spacer(modifier = Modifier.size(10.dp))
            }
        },
        actions = {
            actionIcons?.let {
                it.forEachIndexed() {ind,items ->   CastIconButton(items.imageVector,items.painter,onActionsClicks?.getOrNull(ind) ?: {})}
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
            tint = MaterialTheme.colorScheme.primary
        ) }
    }
}
@Composable
fun CastFloatingActionButton(
    modif: Modifier,
    onClick: () -> Unit = {}
)
{
    FloatingActionButton(
        modifier = modif,
        onClick = onClick,
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.background
    ) {
       Icon(
           imageVector = Icons.Default.Add,
           contentDescription = null,
           modifier = Modifier.size(50.dp)
       )
    }
}
