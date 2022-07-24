package com.example.repositories

import com.example.models.Category

class CategoryRepository {
    companion object{

        fun getCategoryList() : List<Category> {
            return categoryStorage.values.toList()
        }

        fun getCategory(id: Int) : Category? {
            return categoryStorage[id]
        }
    }
}

val categoryStorage = mutableMapOf(1 to Category(1, "Kategoria 1"),
2 to Category(2, "Kategoria 2"),
3 to Category(3, "Kategoria 3"))