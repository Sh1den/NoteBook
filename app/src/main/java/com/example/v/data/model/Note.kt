package com.example.v.data.model

import androidx.compose.ui.graphics.Color
enum class TypeCategory{
    MAIN,
    BASKET,
    OTHER
}
data class Note(
    val id: Int = 0,
    val title: String = "",
    val text: String = "",
    val time: String = "",
    val previousForeignCategory: Int? = null,
    val category: Category = Category(),
    val color: Color? = null
)

data class Category(
    var typeCategory: TypeCategory = TypeCategory.MAIN,
    var stringCategory: String = "Main",
    var categoryId: Int = 0
){
    fun toCategory(newCategory: String,Id: Int = 0){
        when(Id){
            1 -> toMain()
            2 -> toBasket()
            else -> {
                stringCategory = newCategory
                typeCategory = TypeCategory.OTHER
                categoryId = Id
            }
        }
    }
    fun toBasket(){
        stringCategory = "Basket"
        typeCategory = TypeCategory.BASKET
        categoryId = 2
    }
    fun toMain(){
        stringCategory = "Main"
        typeCategory = TypeCategory.MAIN
        categoryId = 1
    }
}
data class SearchCategory(
    val category: Category,
    private var search: String = ""
){
    fun toSearch(string: String){
        search = string
    }
    fun getSearchString() = search
}