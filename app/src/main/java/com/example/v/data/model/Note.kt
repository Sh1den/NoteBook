package com.example.v.data.model

import com.example.v.ui.navigation.Route
import kotlinx.serialization.Serializable


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
    val category: Category
)

data class Category(
    var typeCategory: TypeCategory = TypeCategory.MAIN,
    var stringCategory: String = "Main"
){
    fun toCategory(newCategory: String){
        stringCategory = newCategory
        typeCategory = TypeCategory.OTHER
    }
    fun toBasket(){
        stringCategory = "Basket"
        typeCategory = TypeCategory.BASKET
    }
    fun toMain(){
        stringCategory = "Main"
        typeCategory = TypeCategory.MAIN
    }
}