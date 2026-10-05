package com.example.v.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.v.R
import com.example.v.ui.navigation.NavigationItems
import com.example.v.ui.navigation.Route
import com.example.v.ui.theme.typetheme.MaterialCurrentTheme

@Composable
fun AppDrawerContent(
    navController: NavController,
    thScreen: NavDestination?,
    onDetailsScreensClick: () -> Unit
){
    ModalDrawerSheet(
        drawerContainerColor = MaterialCurrentTheme.colorSchema.background,
        modifier = Modifier
            .width(260.dp)
            .safeDrawingPadding()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 90.dp).background(Color(0xFFA989EF)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.image_note),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 54.dp, height = 90.dp),
                contentDescription = null
            )
            Spacer(Modifier.size(7.dp))
            Text(
                text = stringResource(R.string.menu),
                style = MaterialCurrentTheme.topography.displayText
            )
        }
        Spacer(Modifier.size(10.dp))
        DrawItem(
            NavigationItems.Home.title,
            isSelected = thScreen?.hasRoute<Route.HomeScreen>(),
            painter = NavigationItems.Home.painter
        ) {
            navController.navigate(Route.HomeScreen) {
                launchSingleTop = true
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                restoreState = true
            }
        }
        Spacer(Modifier.size(5.dp))
        DrawItem(
            NavigationItems.Folder.title,
            isSelected = thScreen?.hasRoute<Route.FolderScreen>(),
            painter = NavigationItems.Folder.painter,
        ) {
            navController.navigate(Route.FolderScreen){
                launchSingleTop = true
                popUpTo(navController.graph.startDestinationId){
                    saveState = true
                }
                restoreState = true
            }
        }
        HorizontalDivider(
            Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                bottom = 10.dp,
                top = 5.dp
            ),
            color = MaterialCurrentTheme.colorSchema.labelComponent
        )
        DrawItem(NavigationItems.Setting.title, painter = NavigationItems.Setting.painter) {
            onDetailsScreensClick()
            navController.navigate(Route.SettingsScreen){
                launchSingleTop = true
                popUpTo(navController.graph.startDestinationId){
                    saveState = true
                }
                restoreState = true
            }
        }
        Spacer(Modifier.size(5.dp))
        DrawItem(NavigationItems.Basket.title, painter = NavigationItems.Basket.painter) {
            onDetailsScreensClick()
            navController.navigate(Route.BasketNotes){
                launchSingleTop = true
                popUpTo(navController.graph.startDestinationId){
                    saveState = true
                }
                restoreState = true
            }
        }
    }
}
@Composable
fun DrawItem(
    title: Int?,
    imVect: ImageVector? = null,
    painter: Int? = null,
    isSelected: Boolean? = null,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        selected = isSelected ?: false,
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = MaterialCurrentTheme.colorSchema.selectNavigationComponent,
            selectedIconColor = MaterialCurrentTheme.colorSchema.onBackground,
            selectedTextColor = MaterialCurrentTheme.colorSchema.onBackground,
            unselectedIconColor = MaterialCurrentTheme.colorSchema.onBackground,
            unselectedTextColor = MaterialCurrentTheme.colorSchema.onBackground
        ),
        label = {
            Text(
                text = title?.let { stringResource(it) } ?: "",
                style = MaterialCurrentTheme.topography.bodyText
            )
        },
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        icon = {
            imVect?.let { Icon(
                modifier = Modifier.padding(end = 5.dp),
                imageVector = imVect, contentDescription = null,
                tint = MaterialCurrentTheme.colorSchema.onBackground
            ) }
            painter?.let { Icon(
                modifier = Modifier.padding(end = 5.dp),
                painter = painterResource(painter), contentDescription = null
            ) }
        },
    )
}