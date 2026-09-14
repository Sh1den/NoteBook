package com.example.v.data.model

import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.v.R


enum class Category {
    Main,
    Others,
    Basket;

}
@Keep
data class ScreenType(
    val name: Category = Category.Main,
    val idFolder: Int? = null,
    private var search: String = ""
){
    fun toSearch(string: String){
        search = string
    }
    fun getSearchString() = search
}