package com.example.v.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.v.data.model.NavigationItems
import com.example.v.ui.navigation.Route

@Composable
fun AppDrawerContent(
    navController: NavController,
    thScreen: NavDestination?,
    onDetailsScreensClick: () -> Unit
){
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .width(200.dp)
            .safeDrawingPadding()
    ) {
        Text(
            text = "Menu",
            fontSize = 32.sp,
            modifier = Modifier
                .padding(
                    start = 15.dp,
                    top = 7.dp,
                    bottom = 5.dp
                ),
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        DrawItem(
            NavigationItems.Home.title,
            isSelected = thScreen?.hasRoute<Route.HomeScreen>(),
            painter = NavigationItems.Home.painter
        ) {
            navController.navigate(Route.HomeScreen)
        }
        DrawItem(
            NavigationItems.Folder.title,
            isSelected = thScreen?.hasRoute<Route.FolderScreen>(),
            painter = NavigationItems.Folder.painter,
        ) {
            navController.navigate(Route.FolderScreen)
        }
        HorizontalDivider(
            Modifier.padding(
                start = 15.dp,
                end = 15.dp,
                bottom = 10.dp,
                top = 5.dp
            )
        )
        DrawItem(NavigationItems.Setting.title, painter = NavigationItems.Setting.painter) {
            onDetailsScreensClick()
            navController.navigate(Route.SettingsScreen)
        }
        DrawItem(NavigationItems.Basket.title, painter = NavigationItems.Basket.painter) {}
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
            selectedIconColor = MaterialTheme.colorScheme.onBackground,
            selectedTextColor = MaterialTheme.colorScheme.onBackground,
            unselectedIconColor = MaterialTheme.colorScheme.onBackground,
            unselectedTextColor = MaterialTheme.colorScheme.onBackground
        ),
        label = {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = title?.let { stringResource(it) } ?: "")
        },
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        icon = {
            Spacer(modifier = Modifier.height(10.dp))
            imVect?.let { Icon(
                modifier = Modifier.padding(end = 5.dp),
                imageVector = imVect, contentDescription = null,
                tint = Color.White
            ) }
            painter?.let { Icon(
                modifier = Modifier.padding(end = 5.dp),
                painter = painterResource(painter), contentDescription = null,
                tint = Color.White
            ) }
        },
    )
}