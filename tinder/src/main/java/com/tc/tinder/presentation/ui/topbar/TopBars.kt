package com.tc.tinder.presentation.ui.topbar

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.tc.design.R
import theme.primaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackOnlyTopAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes backIconRes: Int = R.drawable.arrow_left_orange_icon,
    contentDescription: String = "Back Arrow"
) {
    TopAppBar(
        modifier = modifier,
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = backIconRes),
                    contentDescription = contentDescription,
                    tint = Color.Unspecified
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchTopAppBar(
    onHomeClick: () -> Unit,
    onMessageClick: () -> Unit,
    onUserProfileClick: () -> Unit
) {
    Surface(
        tonalElevation = 3.dp,
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(TopAppBarDefaults.TopAppBarExpandedHeight)
                .padding(horizontal = 8.dp)
        ) {

            IconButton(
                onClick = onHomeClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.home_icon),
                    contentDescription = "Home",
                    tint = Color.Unspecified
                )
            }


            IconButton(
                onClick = onMessageClick,
                modifier = Modifier.align(Alignment.Center)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.message_grey_bubble_icon),
                    contentDescription = "Messages",
                    tint = Color.Unspecified
                )
            }


            IconButton(
                onClick = onUserProfileClick,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.user_icon),
                    contentDescription = "Profile",
                    tint = Color.Unspecified
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraTopAppBar(
    onBackClick: () -> Unit,
    onGalleryClick: () -> Unit,
    onHelpClick: () -> Unit
) {
    Surface(
        tonalElevation = 3.dp,
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(TopAppBarDefaults.TopAppBarExpandedHeight)
                .padding(horizontal = 8.dp)
        ) {

            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_left_orange_icon),
                    contentDescription = "Back Arrow",
                    tint = Color.Unspecified
                )
            }


            Text(
                text = "Take the photo",
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.labelLarge
            )


            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                IconButton(
                    onClick = onGalleryClick,
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.image_icon),
                        contentDescription = "Gallery Icon",
                        tint = primaryColor
                    )
                }

                IconButton(
                    onClick = onHelpClick,
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.question_mark_orange_icon),
                        contentDescription = "Gallery Icon",
                        tint = primaryColor
                    )
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSignUpTopAppBar(
    onBackClick: () -> Unit,
) {
    Surface(
        tonalElevation = 3.dp,
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(TopAppBarDefaults.TopAppBarExpandedHeight)
                .padding(horizontal = 8.dp)
        ) {

            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_left_orange_icon),
                    contentDescription = "Back Arrow",
                    tint = Color.Unspecified
                )
            }


            Text(
                text = "Complete your profile",
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.labelLarge
            )


        }

    }
}


