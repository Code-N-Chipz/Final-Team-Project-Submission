package com.tc.eat.domain.util

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.tc.eat.R

enum class Categories(@StringRes val categoryString : Int){
    AFRICAN(categoryString = R.string.African),
    AMERICAN(categoryString = R.string.American),
    MEXICAN(categoryString = R.string.Mexican),
    ITALIAN(categoryString = R.string.Italian),
    FRENCH(categoryString = R.string.French),
    INDIAN(categoryString = R.string.Indian),
    TRADITIONAL(categoryString = R.string.Traditional),
    BREAKFAST(categoryString = R.string.Breakfast),
    SPICY(categoryString = R.string.Spicy),
    BURGER(categoryString = R.string.Burger),
    FRIES(categoryString = R.string.Fries)
}