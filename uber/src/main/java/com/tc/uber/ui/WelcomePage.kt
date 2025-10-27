package com.tc.uber.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.tc.uber.R
import com.tc.uber.ui.components.DefButton
import theme.primaryColor
import theme.textPrimary
import theme.typography


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomePage(imgDrawableId : Int, title : String, description : String, btnText : String,
                onBack :() -> Unit = {},
                onBtnClick : () -> Unit){

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {

            },
               navigationIcon = {
                   IconButton(onClick = onBack, modifier = Modifier.padding(start = 4.dp)) {
                       Icon(
                           painter = painterResource(R.drawable.back),
                           contentDescription = "back",
                           tint = primaryColor,
                       )
                   }
               } )
        }) { innerPadding ->
        ConstraintLayout(Modifier.padding(innerPadding).fillMaxSize()) {

            val (imgRef, transPortRef, descRef, btnRef) = createRefs()

            Image(painter = painterResource(imgDrawableId),
                contentDescription = "",
                modifier = Modifier.height(240.dp).constrainAs(imgRef){
                    top.linkTo(parent.top, margin = 32.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                })

            Text(title, style = typography.displayLarge,
                modifier = Modifier.constrainAs(transPortRef){
                    top.linkTo(imgRef.bottom, margin = 24.dp)
                    centerHorizontallyTo(parent)
                })

            Text(description, style = typography.labelLarge,
                textAlign = TextAlign.Center,
                color = Color(0xFFA6AAB4),
                modifier = Modifier.constrainAs(descRef){
                    top.linkTo(transPortRef.bottom, margin = 24.dp)
                    start.linkTo(parent.start, margin = 20.dp)
                    end.linkTo(parent.end, margin = 20.dp)
                    width = Dimension.fillToConstraints
                })

            DefButton(modifier = Modifier.constrainAs(btnRef){
                bottom.linkTo( parent.bottom , margin = 32.dp)
                start.linkTo(parent.start, margin = 24.dp)
                end.linkTo(parent.end, margin = 24.dp)
                width = Dimension.fillToConstraints
            }, btnText = btnText, onBtnClick = onBtnClick)

        }
    }
}
