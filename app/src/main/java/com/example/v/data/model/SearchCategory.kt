package com.example.v.data.model

import androidx.annotation.Keep

@Keep
data class SearchCategory(
    val category: Category,
    private var search: String = ""
){
    fun toSearch(string: String){
        search = string
    }
    fun getSearchString() = search
}