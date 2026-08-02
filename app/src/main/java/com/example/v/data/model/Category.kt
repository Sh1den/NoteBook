package com.example.v.data.model

import androidx.annotation.Keep

@Keep
enum class TypeCategory{
    MAIN,
    BASKET,
    OTHER
}
@Keep
data class Category(
    val typeCategory: TypeCategory = TypeCategory.MAIN,
    val stringCategory: String = "Main",
    val categoryId: Int = 0
){
    companion object {
        fun getCategory(newCategory: String, id: Int = 0): Category {
            return when (id) {
                1 -> getMainCategory()
                2 -> getBasketCategory()
                else -> Category(
                    typeCategory = TypeCategory.OTHER,
                    stringCategory = newCategory,
                    categoryId = id
                )
            }
        }

        fun getBasketCategory(): Category {
            return Category(
                typeCategory = TypeCategory.BASKET,
                stringCategory = "Basket",
                categoryId = 2
            )
        }

        fun getMainCategory(): Category {
            return Category(
                typeCategory = TypeCategory.MAIN,
                stringCategory = "Main",
                categoryId = 1
            )
        }
    }
}