package com.tc.eat.domain.entities

import com.tc.eat.domain.util.MenuItemCategories

class MenuItem(
    val name : String,
    val ingredients : List<String>,
    val menuCategory : MenuItemCategories,
    val price : Float,
    val isPopular : Boolean,
    val isAvailable : Boolean,
    val foodImage : Int
    ){
    fun ingredientList() : String{
        return ingredients.joinToString()
    }
}
