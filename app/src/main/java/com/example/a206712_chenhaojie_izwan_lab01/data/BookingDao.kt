package com.example.a206712_chenhaojie_izwan_lab01.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {

    @Insert
    suspend fun insertBooking(
        booking: BookingEntity
    )

    @Delete
    suspend fun deleteBooking(
        booking: BookingEntity
    )

    @Query("SELECT * FROM booking_table")
    fun getAllBookings(): Flow<List<BookingEntity>>
}