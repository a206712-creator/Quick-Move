package com.example.a206712_chenhaojie_izwan_lab01

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CommunityScreen(

    navController: NavController,

    viewModel: MoveViewModel,

    modifier: Modifier = Modifier
){

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
                    text = "Community Page",
                    fontSize = 18.sp
                )
            }
        }



        Spacer(modifier = Modifier.height(8.dp))

    var driver by remember {
        mutableStateOf("")
    }

    var from by remember {
        mutableStateOf("")
    }

    var to by remember {
        mutableStateOf("")
    }

    var seat by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {

        viewModel.loadCommunityRide()
    }


        Column(

            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {

            Text(
                "Community Ride Sharing",
                style =
                    MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            OutlinedTextField(
                value = driver,
                onValueChange = {
                    driver = it
                },
                label = {
                    Text("Driver")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )

            OutlinedTextField(
                value = from,
                onValueChange = {
                    from = it
                },
                label = {
                    Text("From")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )

            OutlinedTextField(
                value = to,
                onValueChange = {
                    to = it
                },
                label = {
                    Text("To")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )

            OutlinedTextField(
                value = seat,
                onValueChange = {
                    seat = it
                },
                label = {
                    Text("Seat")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )


            Spacer(
                modifier = Modifier.height(14.dp)
            )

            Button(
                modifier= Modifier.height(50.dp)
                    .width(200.dp),

                onClick = {

                    viewModel.addCommunityRide(

                        driver,

                        from,

                        to,

                        seat
                    )

                    viewModel.loadCommunityRide()

                    driver = ""
                    from = ""
                    to = ""
                    seat = ""
                }

            ) {

                Text("Share Ride")
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            LazyColumn {

                items(
                    viewModel.communityList.value
                ) { ride ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),

                        elevation =
                            CardDefaults.cardElevation(6.dp)
                    ) {

                        Column(
                            modifier =
                                Modifier.padding(20.dp)
                        ) {

                            Text(
                                "🚗 ${ride.driver}",
                                style =
                                    MaterialTheme.typography.titleLarge
                            )

                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )

                            Text(
                                "${ride.from} → ${ride.to}"
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                "Available Seats : ${ride.seat}"
                            )

                            Spacer(
                                modifier = Modifier.height(12.dp)
                            )
                   Row(modifier=Modifier.fillMaxWidth()
                       .padding(6.dp),
                       horizontalArrangement = Arrangement.SpaceBetween) {
                       Button(
                           onClick = { }
                       ) {

                           Text("Join Ride")
                       }
                       Spacer(
                           modifier = Modifier.width(8.dp)
                       )

                       OutlinedButton(
                           onClick = {

                               viewModel.deleteRide(
                                   ride.id
                               )
                           }
                       ) {

                           Text("Delete")
                       }
                   }
                        }
                    }
                }
            }
        }
    }
    }