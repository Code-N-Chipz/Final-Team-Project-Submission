package com.tc.eat.domain.util

import androidx.annotation.StringRes
import com.tc.eat.R

enum class MenuItemCategories(@StringRes val categoryString : Int){
    ENTREE(categoryString = R.string.entrees),
    DISH(categoryString = R.string.dishes),
    DRINK(categoryString = R.string.drinks),
    DESSERT(categoryString = R.string.desserts),
    POPULAR(categoryString = R.string.popular)
}