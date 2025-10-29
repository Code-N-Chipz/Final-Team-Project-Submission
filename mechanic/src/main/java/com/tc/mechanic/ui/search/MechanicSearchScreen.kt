package com.tc.mechanic.ui.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tc.mechanic.R


@Composable
fun MechanicSearchScreen(
    viewModel: MechanicSearchViewModel = viewModel (),
    bannerResId: Int = R.drawable.mechanic_search,
    profileResId: Int = R.drawable.jessy_mechanic,
    onSearch: () -> Unit = {},
    onFavoritesClick: () -> Unit = {},
    onOrdersClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onSuccess: () -> Unit = {},
    onCalenderClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // 🔧 Top banner
        Box {
            Image(
                painter = painterResource(id = bannerResId),
                contentDescription = "Engine Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
            IconButton (
                onClick = onHomeClick,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color(0xFFFF7A00),
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // 🧾 Search section in card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .offset(y = (-40).dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(state.location, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFFFF7A00))
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Column(
                        modifier = Modifier.weight(1f)
                            .clickable( onClick = onCalenderClick )
                        ) {
                        Text("CHOOSE DATE", fontSize = 10.sp, color = Color.Gray)
                        Text(state.date, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("TYPE", fontSize = 10.sp, color = Color.Gray)
                        Text(state.type, fontWeight = FontWeight.Bold)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("MODEL", fontSize = 10.sp, color = Color.Gray)
                        Text(state.model, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = viewModel::updateSearch,
                    placeholder = { Text("Search location / name") },
                    trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onSearch,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00))
                ) {
                    Text("Search", color = Color.White)
                }
            }
        }

        // 🟧 Navigation bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFF7A00))
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable(onClick = onFavoritesClick)) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.White)
                Text("Favorites", color = Color.White, fontSize = 12.sp)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable(onClick = onOrdersClick)) {
                Icon(Icons.Default.List, contentDescription = null, tint = Color.White)
                Text("Orders", color = Color.White, fontSize = 12.sp)
            }
        }

        // 👨‍🔧 Mechanic listing
        Spacer(modifier = Modifier.height(16.dp))
        Text("Teachers 120", modifier = Modifier.padding(horizontal = 16.dp), fontWeight = FontWeight.Bold)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = profileResId),
                    contentDescription = "Jessy Jones",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text("Jessy Jones", fontWeight = FontWeight.Bold)
                    Text("Johannesburg", color = Color.Gray)
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFF7A00), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("4.8", fontSize = 12.sp)
                        }
                        Text("500m", color = Color.Gray, fontSize = 12.sp)
                        Text("$15/h", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.DirectionsCar, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                        Icon(Icons.Default.TwoWheeler, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                    }
                }
                Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.Gray)
            }
        }
    }
}


//@Composable
//fun MechanicSearchScreen(
//    viewModel: MechanicSearchViewModel = viewModel(),
//    bannerResId: Int = R.drawable.mechanic_search,
//    profileResId: Int = R.drawable.jessy_mechanic
//) {
//    val state by viewModel.uiState.collectAsState()
//
//    Column(modifier = Modifier.fillMaxSize()) {
//        // 🔧 Top banner
//        Box {
//            Image(
//                painter = painterResource(id = bannerResId),
//                contentDescription = "Engine Banner",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(220.dp)
//            )
//            Icon(
//                imageVector = Icons.Default.Home,
//                contentDescription = "Home",
//                tint = Color(0xFFFF7A00),
//                modifier = Modifier
//                    .padding(16.dp)
//                    .size(24.dp)
//            )
//        }
//
//        // 🧾 Search section in card
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//                .offset(y = (-40).dp),
//            shape = RoundedCornerShape(12.dp),
//            elevation = CardDefaults.cardElevation(4.dp)
//        ) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Text(state.location, fontWeight = FontWeight.Bold, fontSize = 16.sp)
//                    Spacer(modifier = Modifier.weight(1f))
//                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color(0xFFFF7A00))
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
//                    Column(modifier = Modifier.weight(1f)) {
//                        Text("CHOOSE DATE", fontSize = 10.sp, color = Color.Gray)
//                        Text(state.date, fontWeight = FontWeight.Bold)
//                    }
//                    Column(modifier = Modifier.weight(1f)) {
//                        Text("TYPE", fontSize = 10.sp, color = Color.Gray)
//                        Text(state.type, fontWeight = FontWeight.Bold)
//                    }
//                    Column(modifier = Modifier.weight(1f)) {
//                        Text("MODEL", fontSize = 10.sp, color = Color.Gray)
//                        Text(state.model, fontWeight = FontWeight.Bold)
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//                OutlinedTextField(
//                    value = state.searchQuery,
//                    onValueChange = viewModel::updateSearch,
//                    placeholder = { Text("Search location / name") },
//                    trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//                Button(
//                    onClick = { /* trigger search */ },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7A00))
//                ) {
//                    Text("Search", color = Color.White)
//                }
//            }
//        }
//
//        // 🟧 Navigation bar
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color(0xFFFF7A00))
//                .padding(vertical = 12.dp),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.White)
//                Text("Favorites", color = Color.White, fontSize = 12.sp)
//            }
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                Icon(Icons.Default.List, contentDescription = null, tint = Color.White)
//                Text("Orders", color = Color.White, fontSize = 12.sp)
//            }
//        }
//
//        // 👨‍🔧 Mechanic listing
//        Spacer(modifier = Modifier.height(16.dp))
//        Text("Teachers 120", modifier = Modifier.padding(horizontal = 16.dp), fontWeight = FontWeight.Bold)
//
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            shape = RoundedCornerShape(12.dp)
//        ) {
//            Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    painter = painterResource(id = profileResId),
//                    contentDescription = "Jessy Jones",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(72.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                )
//                Spacer(modifier = Modifier.width(12.dp))
//                Column(modifier = Modifier.weight(1f)) {
//                    Text("Jessy Jones", fontWeight = FontWeight.Bold)
//                    Text("Johannesburg", color = Color.Gray)
//                    Spacer(modifier = Modifier.height(4.dp))
//                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
//                        Row(verticalAlignment = Alignment.CenterVertically) {
//                            Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFF7A00), modifier = Modifier.size(16.dp))
//                            Spacer(modifier = Modifier.width(4.dp))
//                            Text("4.8", fontSize = 12.sp)
//                        }
//                        Text("500m", color = Color.Gray, fontSize = 12.sp)
//                        Text("$15/h", fontWeight = FontWeight.Bold, fontSize = 12.sp)
//                    }
//                    Spacer(modifier = Modifier.height(4.dp))
//                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//                        Icon(Icons.Default.DirectionsCar, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
//                        Icon(Icons.Default.TwoWheeler, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
//                    }
//                }
//                Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.Gray)
//            }
//        }
//    }
//}



@Preview
@Composable
fun MechanicSearchScreenPreview(){

    Surface {
        MechanicSearchScreen()
    }


}