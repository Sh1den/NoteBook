package com.example.v.ui.components

import android.R
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.v.data.model.NavigationItems
import com.example.v.data.model.Note
import com.example.v.data.model.Table
import com.example.v.ui.navigation.Route
import org.w3c.dom.Text

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationTopAppBar(
    titleBar: String? = null,
    navIcons: NavigationItems? = null,
    actionIcons: MutableList<NavigationItems>? = null,
    actiomText: String? = null,
    onActionsClicks: List<(() -> Unit)>? = null,
    colorCont: Color? = null,
    onNavClick: () -> Unit = {}
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorCont ?: Color.Unspecified,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        ),
        title = {Text(titleBar?:"")},
        navigationIcon = {
            Row() {
                navIcons?.let {
                    CastIconButton(navIcons.imageVector,null,navIcons.painter,onNavClick)
                    Spacer(modifier = Modifier.size(10.dp)) }
            }
        },
        actions = {
            actionIcons?.let {
                it.forEachIndexed() {ind,items ->   CastIconButton(items.imageVector, actiomText,items.painter,onActionsClicks?.getOrNull(ind) ?: {})}
            }

        }
    )
}
@Composable
fun CastIconButton(
    imVect: ImageVector? = null,
    text: String? = null,
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
        text?.let { Text(it) }
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
                false -> MaterialTheme.colorScheme.surface
                true -> Color(0xFF74C0FC)
            }
        )
        Card(
            modifier = Modifier.fillMaxWidth().height(90.dp).padding(vertical = 5.dp).combinedClickable(onLongClick = {
                combinedClickable()
            }){
                onClick()
            },
            shape = RoundedCornerShape(7.dp),
            elevation = CardDefaults.cardElevation(7.dp),
            colors = CardDefaults.cardColors(
                containerColor = animateLongClick
            )
        ) {
            Column(
                modifier = Modifier.fillMaxHeight().padding(horizontal = 15.dp)
            ) {
                Text(
                    text = it.title,
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