package com.example.a206712_chenhaojie_izwan_lab01

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun BookingScreen(navController: NavController,
                  viewModel: WalletViewModel,
                  modifier: Modifier){


    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .height(118.dp)
                .statusBarsPadding()

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.navigateUp() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }

                Text(
                    text = "Booking Page",
                    fontSize = 18.sp
                )
            }
        }



        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Select your pickup and destination",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = viewModel.pickupLocation.value,
            onValueChange = {
                viewModel.pickupLocation.value = it },
            label = { Text("Pickup Location") },
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults . colors (
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

   Spacer(modifier = Modifier.height(30.dp))

        TextField(
            value = viewModel.destination.value,
            onValueChange = { viewModel.destination.value = it },
            label = { Text("Destination") },
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults . colors (
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text("Choose Vehicle", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))


        VehicleCard("Standard(4 people)", "RM10", viewModel.selectedVehicleType.value ) {
            viewModel.selectedVehicleType.value  = "Standard(4 people)"
            viewModel.selectedPrice.value = "RM10"
        }

        VehicleCard("Standard(6 people)", "RM15", viewModel.selectedVehicleType.value ) {
            viewModel.selectedVehicleType.value  = "Standard(6 people)"
            viewModel.selectedPrice.value = "RM15"
        }

        VehicleCard("Business", "RM25", viewModel.selectedVehicleType.value ) {
            viewModel.selectedVehicleType.value  = "Business"
            viewModel.selectedPrice.value = "RM25"
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.setBookingData(viewModel.pickupLocation.value,
                    viewModel.destination.value,
                    viewModel.selectedVehicleType.value,
                    viewModel.selectedPrice.value)
                navController.navigate("bookingSummary")
            },
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Text("Book ride")
        }


    }
}

@Composable
fun VehicleCard(
    name: String,
    price: String,
    selected: String,
    onClick: () -> Unit
) {
    val isSelected = selected == name

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding( start = 16.dp, end = 16.dp,top = 6.dp, bottom = 6.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(name, fontWeight = FontWeight.Bold)
            Text(price)
        }
    }
}

