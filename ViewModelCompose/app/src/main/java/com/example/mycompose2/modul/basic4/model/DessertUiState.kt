package com.example.mycompose2.modul.basic4.model

import androidx.annotation.DrawableRes
import com.example.mycompose2.modul.basic4.data.DataSource.dessertList

data class DessertUiState(
    val currentDessertIndex: Int = 0,
    val dessertsSold: Int = 0,
    val revenue: Int = 0,
    val currentDessertPrice: Int = dessertList[currentDessertIndex].price,
    @DrawableRes val currentDessertImageId: Int = dessertList[currentDessertIndex].imageId
)