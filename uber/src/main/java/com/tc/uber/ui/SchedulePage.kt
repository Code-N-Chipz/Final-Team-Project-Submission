package com.tc.uber.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.tc.uber.R
import com.tc.uber.ui.components.DefButton
import com.tc.uber.ui.components.TcDatePicker
import theme.backgroundColor
import theme.primaryColor
import theme.typography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun SchedulePage(onBack : () -> Unit = {},onConfirm: () -> Unit = {}) {

    val description = "Choose the day of departure"
    val description2 = "Choose the time of departure"
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Set delayed trip", style = typography.headlineMedium)
                },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.padding(start = 4.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.back),
                            contentDescription = "back",
                            tint = primaryColor,
                        )
                    }
                })
        }) { innerPadding ->
        ConstraintLayout(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(backgroundColor)
        ) {

            val datePickerState = rememberDatePickerState()

            val btnText = "Confirm"
            val (tvRef, calRef, tv2Ref, timeRef, btnRef) = createRefs()

            Text(
                description, style = typography.labelLarge,
                textAlign = TextAlign.Center,
                color = Color(0xFFA6AAB4),
                modifier = Modifier.constrainAs(tvRef) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                })

            TcDatePicker(modifier = Modifier.constrainAs(calRef) {
                top.linkTo(tvRef.bottom, margin = 12.dp)
                start.linkTo(parent.start, margin = 0.dp)
                end.linkTo(parent.end, margin = 0.dp)
                width = Dimension.fillToConstraints
            })

            Text(
                description2, style = typography.labelLarge,
                textAlign = TextAlign.Center,
                color = Color(0xFFA6AAB4),
                modifier = Modifier.constrainAs(tv2Ref) {
                    top.linkTo(calRef.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                })

            OutlinedTextField(
                value = "14:30", onValueChange = {},
                label = {
                    val (lbRef) = createRefs()
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Time", style = typography.labelLarge)
                    }
                },
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = primaryColor,
                    focusedBorderColor = primaryColor
                ),
                modifier = Modifier
                    .width(160.dp)
                    .constrainAs(timeRef) {
                        centerHorizontallyTo(parent)
                        top.linkTo(tv2Ref.bottom, margin = 12.dp)
                    })

            LocalFocusManager.current.clearFocus()


            DefButton(modifier = Modifier.constrainAs(btnRef) {
                bottom.linkTo(parent.bottom, margin = 32.dp)
                start.linkTo(parent.start, margin = 24.dp)
                end.linkTo(parent.end, margin = 24.dp)
                width = Dimension.fillToConstraints
            }, btnText = btnText) {
                onConfirm()
            }

        }
    }

}