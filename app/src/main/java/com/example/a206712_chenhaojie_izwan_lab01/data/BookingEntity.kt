package com.example.a206712_chenhaojie_izwan_lab01.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booking_table")
data class BookingEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val pickupLocation: String,

    val destination: String,

    val vehicleType: String,

    val price: String
)