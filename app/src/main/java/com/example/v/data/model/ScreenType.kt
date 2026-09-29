package com.example.v.data.model

import androidx.annotation.Keep


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

interface Hyi{
    fun chlen(): Int
    fun pisa(): String
}

class Penis: Hyi {
    override fun chlen(): Int {
        return 1
    }

    override fun pisa(): String {
        return "123"
    }
}