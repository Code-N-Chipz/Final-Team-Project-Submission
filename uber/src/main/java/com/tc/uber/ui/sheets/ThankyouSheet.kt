package com.tc.uber.ui.sheets


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.tc.uber.ui.components.DefButton
import com.tc.uber.ui.components.StarRating
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import theme.backgroundColor
import theme.primaryColor
import theme.textPrimary
import theme.typography
import com.tc.design.R as D

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThankYou(
    isVisible : Boolean,
    onCloseSheet: () -> Unit = {}) {
    val skipPartiallyExpanded by rememberSaveable { mutableStateOf(true) }
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)

    val focusRequester = remember { FocusRequester().also { it.freeFocus() } }

    var focused by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(2000)
        focused = true
    }

    val scope = rememberCoroutineScope()

    if(isVisible){
          ModalBottomSheet(
              dragHandle = {},
              sheetGesturesEnabled = false,
              properties = ModalBottomSheetProperties(
                  shouldDismissOnClickOutside = false,
                  shouldDismissOnBackPress = false
              ),
              containerColor = backgroundColor,
              onDismissRequest = onCloseSheet,
              sheetState = state,
          ) {

              ConstraintLayout(Modifier.fillMaxWidth()) {

                  val (dhRef, tvRef, rateRef, starsRef, tfRef, tipRef, tipSelectorRef, clearRef, btnRef) = createRefs()

                  HorizontalDivider(
                      thickness = 2.dp,
                      modifier = Modifier
                          .width(48.dp)
                          .padding(top = 16.dp)
                          .constrainAs(dhRef) {
                              bottom.linkTo(tvRef.top, margin = 16.dp)
                              centerHorizontallyTo(parent)
                          },
                      color = Color.Gray
                  )

                  IconButton(
                      onClick = {
                          scope.launch {
                              state.hide()
                          }.invokeOnCompletion {
                              onCloseSheet()
                          }
                      },
                      modifier = Modifier.constrainAs(clearRef) {
                          top.linkTo(tvRef.top, margin = 2.dp)
                          bottom.linkTo(tvRef.bottom, margin = 2.dp)
                          end.linkTo(parent.end, margin = 24.dp)
                          height = Dimension.fillToConstraints
                      }) {

                      Icon(
                          painter = painterResource(D.drawable.cancel_grey_icon),
                          tint = primaryColor,
                          contentDescription = "close",
                      )
                  }

                  Text(
                      "Thank You", style = typography.headlineMedium,
                      modifier = Modifier.constrainAs(tvRef) {
                          bottom.linkTo(rateRef.top, margin = 32.dp)
                          centerHorizontallyTo(parent)
                      })

                  Text(
                      "You rate Gabriel 4 stars", style = typography.labelLarge,
                      modifier = Modifier.constrainAs(rateRef) {
                          bottom.linkTo(starsRef.top, margin = 20.dp)
                          centerHorizontallyTo(parent)
                      })

                  StarRating(
                      modifier = Modifier.constrainAs(starsRef) {
                          bottom.linkTo(tfRef.top, margin = 24.dp)
                          centerHorizontallyTo(parent)
                      }, rating = 4
                  ) { newRating ->

                  }


                  OutlinedTextField(
                      enabled = focused,
                      colors = OutlinedTextFieldDefaults
                          .colors(
                              unfocusedContainerColor = Color(0xFFF0F1F4).copy(alpha = 0.9f),
                              focusedContainerColor = Color(0xFFF0F1F4).copy(alpha = 0.9f),
                              unfocusedBorderColor = Color(0xFFF0F1F4).copy(alpha = 0.9f),
                              focusedBorderColor = Color(0xFFF0F1F4).copy(alpha = 0.9f)
                          ),
                      value = "",
                      onValueChange = {},
                      placeholder = {
                          Text(
                              "Say something about Gabriel's service.", style = typography.labelMedium,
                              color = Color.Gray
                          )
                      },
                      modifier = Modifier
                          .heightIn(min = 80.dp)
                          .focusRequester(focusRequester)
                          .constrainAs(tfRef) {
                              bottom.linkTo(tipRef.top, margin = 24.dp)
                              start.linkTo(parent.start, margin = 12.dp)
                              end.linkTo(parent.end, margin = 12.dp)
                              width = Dimension.fillToConstraints
                          }
                  )

                  Text(
                      "Do you want to tip Gabriel?", style = typography.labelLarge,
                      modifier = Modifier.constrainAs(tipRef) {
                          bottom.linkTo(tipSelectorRef.top, margin = 24.dp)
                          centerHorizontallyTo(parent)
                      })



                  Row(
                      modifier = Modifier.constrainAs(tipSelectorRef) {
                          bottom.linkTo(btnRef.top, margin = 36.dp)
                          start.linkTo(btnRef.start, margin = 20.dp)
                          end.linkTo(btnRef.end, margin = 20.dp)
                          width = Dimension.fillToConstraints
                      },
                      horizontalArrangement = Arrangement.spacedBy(4.dp)
                  ) {

                      Box(
                          contentAlignment = Alignment.Center,
                          modifier = Modifier
                              .weight(0.5f)
                              .padding(horizontal = 4.dp)
                              .clip(RoundedCornerShape(12))
                              .background(Color(0xFF017DFF))
                      ) {
                          Text(
                              "$5",
                              color = textPrimary,
                              modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp)
                          )
                      }

                      Box(
                          contentAlignment = Alignment.Center,
                          modifier = Modifier
                              .weight(0.5f)
                              .padding(horizontal = 4.dp)
                              .clip(RoundedCornerShape(12))
                              .background(Color(0xFF10C971))

                      ) {
                          Text(
                              "$5",
                              color = textPrimary,
                              modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp)
                          )
                      }

                      Box(
                          contentAlignment = Alignment.Center,
                          modifier = Modifier
                              .weight(0.5f)
                              .padding(horizontal = 4.dp)
                              .clip(RoundedCornerShape(12))
                              .dropShadow(
                                  shape = RoundedCornerShape(20.dp),
                                  shadow = Shadow(
                                      radius = 10.dp,
                                      spread = 6.dp,
                                      color = Color(0xFFE5C5BC),
                                      offset = DpOffset(x = 4.dp, 4.dp)
                                  )
                              )
                              .background(primaryColor)
                      ) {
                          Text(
                              "$50",
                              color = textPrimary,
                              modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp)
                          )
                      }

                      Box(
                          contentAlignment = Alignment.Center,
                          modifier = Modifier
                              .weight(0.5f)
                              .padding(horizontal = 4.dp)
                              .clip(RoundedCornerShape(12))
                              .background(Color.White)
                      ) {
                          Text(
                              "Other",
                              modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp)
                          )
                      }
                  }

                  DefButton(modifier = Modifier.constrainAs(btnRef) {
                      bottom.linkTo(parent.bottom, margin = 24.dp)
                      start.linkTo(parent.start, margin = 48.dp)
                      end.linkTo(parent.end, margin = 48.dp)
                      width = Dimension.fillToConstraints
                  }, btnText = "Submit") {

                  }
              }

              LocalFocusManager.current.clearFocus(force = true)
          }
    }
}

