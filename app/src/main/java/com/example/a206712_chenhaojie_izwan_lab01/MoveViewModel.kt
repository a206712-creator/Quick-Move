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
import com.google.firebase.firestore.FirebaseFirestore
import com.example.a206712_chenhaojie_izwan_lab01.data.CommunityRide
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.a206712_chenhaojie_izwan_lab01.data.RetrofitInstance


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



class MoveViewModel(
    application: Application
) : AndroidViewModel(application) {
    var selectedWallet = mutableStateOf(
        WalletData
            ("", "")
    )
    var booking = mutableStateOf(
        BookingData("", "", "", "")
    )

    var pickupLocation = mutableStateOf("")
    var destination = mutableStateOf("")
    var selectedVehicleType = mutableStateOf("")
    var selectedPrice = mutableStateOf("")


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

    private val db =
        FirebaseFirestore.getInstance()

    var communityList = mutableStateOf(
        listOf<CommunityRide>()
    )

    var latitude = mutableStateOf("Unknown")

    var longitude = mutableStateOf("Unknown")

    var loading = mutableStateOf(false)

    var temperature = mutableStateOf("Loading")

    var humidity = mutableStateOf("Loading")

    var lastUpdated = mutableStateOf("")

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

    fun addCommunityRide(

        driver: String,

        from: String,

        to: String,

        seat: String
    ) {

        val ride = hashMapOf(

            "driver" to driver,

            "from" to from,

            "to" to to,

            "seat" to seat
        )

        db.collection("communityRide")
            .add(ride)
    }

    fun loadCommunityRide() {

        db.collection("communityRide")
            .get()
            .addOnSuccessListener { result ->

                val rideList =
                    mutableListOf<CommunityRide>()

                for(document in result) {

                    rideList.add(

                        CommunityRide(

                            id = document.id,

                            driver =
                                document.getString("driver")
                                    ?: "",

                            from =
                                document.getString("from")
                                    ?: "",

                            to =
                                document.getString("to")
                                    ?: "",

                            seat =
                                document.getString("seat")
                                    ?: ""
                        )
                    )
                }

                communityList.value =
                    rideList
            }
    }

    fun deleteRide(
        documentId: String
    ) {

        db.collection("communityRide")
            .document(documentId)
            .delete()
            .addOnSuccessListener {

                loadCommunityRide()
            }
    }

    fun getWeather() {

        viewModelScope.launch {

            try {

                val response =
                    RetrofitInstance.api.getWeather(

                        latitude = 3.1572757,

                        longitude = 101.7122335,

                        current =
                            "temperature_2m,relative_humidity_2m"
                    )

                temperature.value =
                    "${response.current.temperature_2m} "

                humidity.value =
                    "${response.current.relative_humidity_2m}"
                val formatter =
                    java.text.SimpleDateFormat(
                        "dd MMM yyyy\nhh:mm:ss a",
                        java.util.Locale.getDefault()
                    )

                lastUpdated.value =
                    formatter.format(
                        java.util.Date()
                    )
            } catch (e: Exception) {

                temperature.value = "Error"

                humidity.value = "Error"
            }
        }
    }

    }

