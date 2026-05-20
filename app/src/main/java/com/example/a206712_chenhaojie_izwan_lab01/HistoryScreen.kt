package com.example.a206712_chenhaojie_izwan_lab01

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HistoryScreen(
    navController: NavController,
    viewModel: WalletViewModel,
    modifier: Modifier = Modifier
) {

    val bookingList by
    viewModel.bookingList.collectAsState()

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        // TOP BAR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.primary
                )
                .height(118.dp)
                .statusBarsPadding()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                IconButton(
                    onClick = {
                        navController.navigateUp()
                    }
                ) {

                    Icon(
                        imageVector =
                            Icons.Filled.ArrowBack,

                        contentDescription = "Back"
                    )
                }

                Text(
                    text = "Booking History",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // CONTENT
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Recent Activity",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text =
                    "View and manage your ride history.",
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            if (bookingList.isEmpty()) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 100.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "No Booking Yet",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text =
                            "Your booking history will appear here.",
                        color = Color.Gray
                    )
                }
            }

            LazyColumn(
                verticalArrangement =
                    Arrangement.spacedBy(14.dp)
            ) {

                items(bookingList) { booking ->

                    Card(

                        modifier = Modifier
                            .fillMaxWidth(),

                        shape = RoundedCornerShape(24.dp),

                        elevation =
                            CardDefaults.cardElevation(8.dp)
                    ) {

                        Column(
                            modifier =
                                Modifier.padding(20.dp)
                        ) {

                            Row(
                                modifier =
                                    Modifier.fillMaxWidth(),

                                horizontalArrangement =
                                    Arrangement.SpaceBetween
                            ) {

                                Column {

                                    Text(
                                        text =
                                            booking.vehicleType,

                                        fontSize = 20.sp,

                                        fontWeight =
                                            FontWeight.Bold
                                    )

                                    Spacer(
                                        modifier =
                                            Modifier.height(4.dp)
                                    )

                                    Text(
                                        text = "Completed Ride",
                                        color = Color.Gray
                                    )
                                }

                                Text(
                                    text = booking.price,

                                    fontSize = 24.sp,

                                    fontWeight =
                                        FontWeight.Bold,

                                    color =
                                        MaterialTheme
                                            .colorScheme
                                            .primary
                                )
                            }

                            Spacer(
                                modifier =
                                    Modifier.height(18.dp)
                            )

                            Card(
                                colors =
                                    CardDefaults.cardColors(
                                        containerColor =
                                            MaterialTheme
                                                .colorScheme
                                                .primaryContainer
                                    )
                            ) {

                                Column(
                                    modifier =
                                        Modifier.padding(14.dp)
                                ) {

                                    Text(
                                        text =
                                            "📍 ${booking.pickupLocation}"
                                    )

                                    Spacer(
                                        modifier =
                                            Modifier.height(10.dp)
                                    )

                                    Text(
                                        text =
                                            "🏁 ${booking.destination}"
                                    )
                                }
                            }

                            Spacer(
                                modifier =
                                    Modifier.height(16.dp)
                            )

                            OutlinedButton(

                                onClick = {

                                    viewModel.deleteBooking(
                                        booking
                                    )
                                },

                                modifier =
                                    Modifier.fillMaxWidth(),

                                shape =
                                    RoundedCornerShape(16.dp)
                            ) {

                                Text(
                                    text = "Delete History"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}