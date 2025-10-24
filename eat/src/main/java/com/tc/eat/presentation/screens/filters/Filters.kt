package com.tc.eat.presentation.screens.filters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FiltersScreen(){
    Scaffold(topBar = {FilterTopBar()}){ innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)){

        }
    }
}
@Composable
fun FilterTopBar(){

}