package com.example.a206712_chenhaojie_izwan_lab01

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


data class WalletData(
    val title: String,
    val amount: String
)

data class BookingData(
    val pickupLocation: String,
    val destination: String,
    val vehicle: String,
    val price: String
)



class WalletViewModel : ViewModel() {
    var selectedWallet = mutableStateOf(
        WalletData
            ("", "")
    )

    fun setWalletData(title: String, amount: String) {
        selectedWallet.value = WalletData(title, amount)

        var booking = mutableStateOf(
            BookingData("", "", "","")
        )

        fun setBookingData(pickupLocation: String, destination: String,
                           vehicle: String,price: String) {
            booking.value = BookingData(pickupLocation, destination, vehicle,price)

        }
    }
}

