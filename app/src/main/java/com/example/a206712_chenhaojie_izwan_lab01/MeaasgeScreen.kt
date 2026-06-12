package com.example.a206712_chenhaojie_izwan_lab01

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MessageScreen(navController: NavController,
                  viewModel: MoveViewModel,
                  modifier: Modifier){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .height(110.dp)
                .statusBarsPadding()
        ) {
            Text(
                text = "Message Page",
                fontSize = 20.sp,
                fontWeight = Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            Modifier.padding(17.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
                    .height(70.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text("Driver Information", fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp))
            }

            Card(
                modifier = Modifier.fillMaxWidth()
                    .height(70.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Text("Latest information", fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp))
            }

            Card(
                modifier = Modifier.fillMaxWidth()
                    .height(70.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Text("dining information", fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp))
            }

            Card(
                modifier = Modifier.fillMaxWidth()
                    .height(70.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Text("SDG Goal: Sustainable Cities", fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp))
            }
        }

    }
}