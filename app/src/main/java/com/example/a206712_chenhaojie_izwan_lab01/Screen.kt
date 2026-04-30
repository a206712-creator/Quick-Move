package com.example.a206712_chenhaojie_izwan_lab01

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Function1 : Screen("function1")
    object Wallet1 : Screen("wallet1")
    object Recommended1 : Screen("recommended1")
    object Booking : Screen("booking")
    object BookingSummary : Screen("bookingSummary")
    object Message : Screen("message")

}