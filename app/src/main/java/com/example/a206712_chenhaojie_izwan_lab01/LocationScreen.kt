package com.example.a206712_chenhaojie_izwan_lab01

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices
import androidx.navigation.NavController
import com.google.android.gms.location.Priority

@SuppressLint("MissingPermission")
@Composable
fun LocationScreen(
    navController: NavController,
    viewModel: MoveViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
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
                    text = "Location Page",
                    fontSize = 18.sp
                )
            }
        }



        Spacer(modifier = Modifier.height(8.dp))

        val context = LocalContext.current

        val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(
                context
            )

        val permissionLauncher =
            rememberLauncherForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { granted ->

                if (granted) {

                    fusedLocationClient
                        .getCurrentLocation(
                            Priority.PRIORITY_HIGH_ACCURACY,
                            null
                        )
                        .addOnSuccessListener { location ->

                           location?.let {

                                viewModel.latitude.value =
                                    it.latitude.toString()

                                viewModel.longitude.value =
                                    it.longitude.toString()
                            }

                        }
                }
            }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            Icon(
                imageVector =
                    Icons.Default.LocationOn,

                contentDescription = null,

                modifier = Modifier.size(80.dp)
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                "Current GPS Location",
                fontSize = 24.sp
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        "Latitude:",
                        style =
                            MaterialTheme.typography.titleLarge
                    )

                    Text(
                        viewModel.latitude.value,
                        style =
                            MaterialTheme.typography.bodyLarge
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text(
                        "Longitude:",
                        style =
                            MaterialTheme.typography.titleLarge
                    )

                    Text(
                        viewModel.longitude.value,
                        style =
                            MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            if(viewModel.loading.value){

                CircularProgressIndicator()

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    "Getting GPS Location..."
                )
            }

            Button(
                modifier= Modifier.height(60.dp)
                    .width(230.dp),
                onClick = {
                    viewModel.loading.value = true
                    if (
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                        ==
                        PackageManager.PERMISSION_GRANTED
                    ) {

                        fusedLocationClient
                            .getCurrentLocation(
                                Priority.PRIORITY_HIGH_ACCURACY,
                                null
                            )
                            .addOnSuccessListener {

                                it?.let {

                                    viewModel.latitude.value =
                                        it.latitude.toString()

                                    viewModel.longitude.value =
                                        it.longitude.toString()
                                }
                                viewModel.loading.value = false
                            }

                    } else {

                        permissionLauncher.launch(
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                    }
                }
            ) {

                Text(text = "Get Current Location",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary


                )
            }
        }
    }
}