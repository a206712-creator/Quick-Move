package com.example.a206712_chenhaojie_izwan_lab01

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.a206712_chenhaojie_izwan_lab01.data.BookingDatabase
import com.example.a206712_chenhaojie_izwan_lab01.data.BookingEntity
import com.example.a206712_chenhaojie_izwan_lab01.data.BookingRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


data class WalletData(
    val title: String,
    val amount: String
)

data class BookingData(
    val pickupLocation: String,
    val destination: String,
    val vehicleType: String,
    val price: String
)



class WalletViewModel(
    application: Application
) : AndroidViewModel(application) {
    var selectedWallet = mutableStateOf(
        WalletData
            ("", "")
    )
    var booking = mutableStateOf(
        BookingData("", "", "", "")
    )

    private val repository: BookingRepository
    init {

        val dao =
            BookingDatabase
                .getDatabase(application)
                .bookingDao()

        repository = BookingRepository(dao)
    }

    val bookingList =
        repository.allBookings.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun setWalletData(title: String, amount: String) {
        selectedWallet.value = WalletData(title, amount)
    }


        fun setBookingData(
            pickupLocation: String, destination: String,
            vehicleType: String, price: String
        ) {
            booking.value = BookingData(pickupLocation, destination, vehicleType, price)

        }

    fun saveBooking() {

        viewModelScope.launch {

            repository.insertBooking(
                BookingEntity(
                    pickupLocation =
                        booking.value.pickupLocation,

                    destination =
                        booking.value.destination,

                    vehicleType =
                        booking.value.vehicleType,

                    price =
                        booking.value.price
                )
            )
        }
    }

    fun deleteBooking(
        booking: BookingEntity
    ) {

        viewModelScope.launch {

            repository.deleteBooking(
                booking
            )
        }
    }

    }

