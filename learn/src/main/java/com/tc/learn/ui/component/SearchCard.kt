package com.tc.learn.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tc.learn.data.model.Teacher

@Composable
fun SearchCard(
    teacher: Teacher,
    onClick: (Teacher) -> Unit,
    modifier: Modifier,
) {
//       --- Date selection state ---
    var selectedDate by remember { mutableStateOf<String?>(null) }

//    // --- Search states ---
//    var name by remember { mutableStateOf("") }
//    var subject by remember { mutableStateOf("") }
//    var level by remember { mutableStateOf("") }
//    var date by remember { mutableStateOf("") }
//    var location by remember { mutableStateOf("") }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick(teacher) },
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            //Current Location Goes here
            Text(
                text = "Current Location",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(Modifier.height(4.dp))

            Row(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Text(
                    text = "CHOOSE DATE",
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxHeight()
            ) {
                Text(
                    text = "[TO CALANDER]",
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}

