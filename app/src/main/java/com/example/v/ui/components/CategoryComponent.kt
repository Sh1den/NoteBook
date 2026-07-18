package com.example.v.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.v.R
import com.example.v.data.model.Category
import com.example.v.data.model.NavigationItems
import com.example.v.data.model.TypeCategory

@Composable
private fun GetTitle(category: Category): String{
    return when(category.typeCategory){
        TypeCategory.MAIN -> stringResource(R.string.app_name)
        TypeCategory.BASKET -> stringResource(R.string.basket_app)
        else -> category.stringCategory
    }
}
@Composable
private fun GetSelectAppBarCategory(category: Category,onNavClick: () -> Unit,onActionsClicks: () -> Unit){
    when (category.typeCategory) {
        TypeCategory.BASKET -> {
            NavigationTopAppBar(
                navIcons = NavigationItems.Back,
                actionText = mutableListOf(stringResource(R.string.delete)),
                onActionsClicks = mutableListOf(onActionsClicks),
                onNavClick = onNavClick
            )
        }
        else -> {
            NavigationTopAppBar(
                navIcons = NavigationItems.Back,
                actionIcons = mutableListOf(NavigationItems.Basket),
                onActionsClicks = mutableListOf(onActionsClicks),
                onNavClick = onNavClick
            )
        }
    }
}

@Composable
private fun GetUnselectAppBarCategory(
    category: Category,
    onNavClick: () -> Unit,
    onActionsClicks: () -> Unit
) {
    val navIcons: NavigationItems = when (category.typeCategory) {
        TypeCategory.OTHER -> NavigationItems.Back
        TypeCategory.BASKET -> NavigationItems.Back
        else -> NavigationItems.Menu
    }
    NavigationTopAppBar(
        titleBar = GetTitle(category),
        navIcons = navIcons,
        actionIcons = mutableListOf(
            NavigationItems.Search
        ),
        onActionsClicks = mutableListOf(onActionsClicks),
        onNavClick = onNavClick
    )

}


@Composable
private fun GetCategoryAppBar(category: Category,isSelected: Boolean,onNavClick: () -> Unit,onActionsClicks: () -> Unit){
    if (isSelected) {
        GetSelectAppBarCategory(category,onNavClick,onActionsClicks)
    }
    else{
        GetUnselectAppBarCategory(category,onNavClick,onActionsClicks)
    }
}
