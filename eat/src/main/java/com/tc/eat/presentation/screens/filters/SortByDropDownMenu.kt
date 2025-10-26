package com.tc.eat.presentation.screens.filters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tc.design.R
import theme.textTertiary

@Composable
fun SortByDropDownMenu() {
    var expanded by remember { mutableStateOf(false) }
    Box() {
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(88.dp)
                .padding(horizontal = 16.dp)
                .padding(top = 20.dp)
                .border(
                    width = 1.dp,
                    color = textTertiary,
                    shape = RoundedCornerShape(8.dp)
                ),
            shape = RoundedCornerShape(8.dp),
            onClick = { expanded = !expanded }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(com.tc.eat.R.string.recommended),
                    fontSize = 18.sp
                )
                Icon(
                    painter = painterResource(R.drawable.down_arrow_grey_icon),
                    contentDescription = ""
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(start = 40.dp, top = 10.dp)
                .height(24.dp)
                .width(80.dp)
                .background(MaterialTheme.colorScheme.surface),
            text = stringResource(com.tc.eat.R.string.sort_by),
            textAlign = TextAlign.Center,
            color = textTertiary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(com.tc.eat.R.string.recommended)) },
                onClick = {}
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(com.tc.eat.R.string.rating)) },
                onClick = {}
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(com.tc.eat.R.string.alphabetical)) },
                onClick = {}
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(com.tc.eat.R.string.proximity)) },
                onClick = {}
            )
        }
    }
}