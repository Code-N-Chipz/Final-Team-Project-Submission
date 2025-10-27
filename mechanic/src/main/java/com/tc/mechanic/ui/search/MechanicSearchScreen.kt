package com.tc.mechanic.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun MechanicSearchScreen(viewModel: MechanicSearchViewModel = viewModel ()) {
    val state by viewModel.uiState.collectAsState()

    Column (modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Top image placeholder
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text("Engine Image", color = Color.DarkGray)
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = state.location, fontWeight = FontWeight.Bold)

        Row (horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextField(value = state.date, onValueChange = viewModel::updateDate, label = { Text("Date") }, modifier = Modifier.weight(1f))
            TextField(value = state.type, onValueChange = viewModel::updateType, label = { Text("Type") }, modifier = Modifier.weight(1f))
            TextField(value = state.model, onValueChange = viewModel::updateModel, label = { Text("Model") }, modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = state.searchQuery, onValueChange = viewModel::updateSearch, label = { Text("Search location / name") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button (onClick = {}) { Text("Favorites") }
            Button(onClick = {}) { Text("Orders") }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Mechanics", fontWeight = FontWeight.SemiBold)

        // Mechanic card
        Card (modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Jessy Jones", fontWeight = FontWeight.Bold)
                Text("Johannesburg", color = Color.Gray)
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text("⭐ 4.8")
                    Text("500m")
                    Text("Car / Motorcycle")
                    Text("$15/h")
                }
            }
        }
    }
}

@Preview
@Composable
fun MechanicSearchScreenPreview(){

    MechanicSearchScreen()

}