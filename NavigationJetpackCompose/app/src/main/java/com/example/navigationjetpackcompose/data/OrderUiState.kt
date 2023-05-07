package com.example.navigationjetpackcompose.data

// Data class that represent the current UI state in terms of [quantity], [flavor], [dateOptions], selected pickup [date] and [price]
data class OrderUiState (
    // Selected cupcake quantity (1, 6, 12)
    val quantity: Int = 0,
    // Flavor of the cupcakes in the order (such as "Chocolate", Vanilla)
    val flavor: String = "",
    // Selected date for pickup (such as "Jan 1")
    val date: String = "",
    // total price for the order
    val price: String = "",
    // Available pickup dates for the order
    val pickupOptions: List<String> = listOf()
)