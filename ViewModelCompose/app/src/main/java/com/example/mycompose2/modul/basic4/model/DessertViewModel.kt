package com.example.mycompose2.modul.basic4.model

import androidx.lifecycle.ViewModel
import com.example.mycompose2.modul.basic4.data.DataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel: ViewModel() {
    // Dessert UI State
    private val _uiState = MutableStateFlow(DessertUiState())
    val iuState:  StateFlow<DessertUiState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        _uiState.update { cupcakeUiState ->
            val dessertsSold = cupcakeUiState.dessertsSold + 1
            val nextDessertIndex = determineDessertIndex(dessertsSold)
            cupcakeUiState.copy(
                currentDessertIndex = nextDessertIndex,
                dessertsSold = dessertsSold,
                revenue = cupcakeUiState.revenue + cupcakeUiState.currentDessertPrice,
                currentDessertPrice = DataSource.dessertList[nextDessertIndex].price,
                currentDessertImageId = DataSource.dessertList[nextDessertIndex].imageId
            )
        }
    }

    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0
        for (index in DataSource.dessertList.indices) {
            if (dessertsSold >= DataSource.dessertList[index].startProductionAmount) {
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. as you sell more
                // desserts, you'll start producing more expensive desserts as determined by
                // startProductionAmount. we know to break as soon as we see a dessert who's
                // "startProductionAmount" is greater than the amount sold
                break
            }
        }
        return dessertIndex
    }
}