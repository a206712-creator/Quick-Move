package com.example.a206712_chenhaojie_izwan_lab01.data

class BookingRepository(
    private val dao: BookingDao
) {

    val allBookings =
        dao.getAllBookings()

    suspend fun insertBooking(
        booking: BookingEntity
    ) {

        dao.insertBooking(booking)
    }

    suspend fun deleteBooking(
        booking: BookingEntity
    ) {

        dao.deleteBooking(booking)
    }
}