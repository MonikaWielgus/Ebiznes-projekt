package com.example.repositories

import com.example.models.Category

class CategoryRepository {
    companion object{

        fun getCategoryList() : List<Category> {
            return categoryStorage.values.toList();
        }
    }
}

val categoryStorage = mutableMapOf(1 to Category(1, "Mike"));