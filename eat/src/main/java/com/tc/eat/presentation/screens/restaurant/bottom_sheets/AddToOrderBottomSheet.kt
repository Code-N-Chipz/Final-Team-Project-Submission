package com.tc.eat.presentation.screens.restaurant.bottom_sheets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.design.R
import com.tc.eat.domain.entities.MenuItem
import com.tc.eat.domain.util.MenuItemCategories
import com.tc.eat.presentation.screens.composables.PriceTagText
import com.tc.eat.presentation.screens.restaurant.MenuItemHeader
import com.tc.ui.CommonButton
import theme.primaryColor
import theme.primaryIconColor
import theme.textQuaternary
import theme.textTertiary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddToOrderBottomSheet() {
    val menuItem = MenuItem(
        name = "Spinach and ricotta raviolis",
        ingredients = listOf("Spinach", "Ricotta", "Pasta"),
        menuCategory = MenuItemCategories.ENTREE,
        price = 13.50f,
        isPopular = true,
        isAvailable = true,
        foodImage = com.tc.eat.R.drawable.spaghetti
    )
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    var quantity by remember { mutableStateOf("1") }
    var totalCost by remember{ mutableFloatStateOf(menuItem.price) }
    if(isSheetOpen) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = {}
        ) {
            MenuItemHeader(
                menuItem = menuItem,
                iconAvailability = R.drawable.dispo_green_icon,
                textColor = textQuaternary
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                text = menuItem.ingredientList()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier.padding(end = 24.dp),
                    onClick = {}
                )
                {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(R.drawable.minus_icon),
                        contentDescription = "",
                        tint = primaryColor
                    )
                }
                TextField(
                    shape = RoundedCornerShape(8.dp),
                    textStyle = TextStyle(fontSize = 32.sp, textAlign = TextAlign.Center),
                    value = quantity,
                    onValueChange = { newText -> quantity = newText },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = textTertiary,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .size(72.dp)
                )
                IconButton(
                    modifier = Modifier.padding(start = 24.dp),
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.plus_white_icon),
                        contentDescription = "",
                        tint = primaryColor
                    )
                }
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .clickable(onClick = {}),
                text = stringResource(com.tc.eat.R.string.remove),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = primaryColor
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                CommonButton(
                    text = stringResource(com.tc.eat.R.string.add)
                )
                PriceTagText(
                    modifier = Modifier.padding(start = 200.dp),
                    price = totalCost,
                    style = TextStyle(color = primaryIconColor, fontSize = 14.sp)
                )
            }
        }
    }
}