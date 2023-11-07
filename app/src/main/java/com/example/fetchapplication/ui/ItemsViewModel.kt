package com.example.fetchapplication.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchapplication.network.Data
import com.example.fetchapplication.network.RetrofitInstance
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UiState {
    data class Success(val items: List<Data>) : UiState
    object Error : UiState
    object Loading : UiState
}

class ItemsViewModel : ViewModel() {
    var uiState: UiState by mutableStateOf(UiState.Loading)
        private set

    init {
        getItems()
    }

    private fun getItems(){
        viewModelScope.launch {
            uiState = try {
                val listResult = RetrofitInstance.retrofitService.getItems()
                    .filter { !it.name.isNullOrBlank() }
                    .sortedWith(compareBy({ it.listId}, { it.name?.substring(5)?.toInt()}))
                UiState.Success(listResult)
            } catch (e: IOException){
                UiState.Error
            }
        }
    }
}