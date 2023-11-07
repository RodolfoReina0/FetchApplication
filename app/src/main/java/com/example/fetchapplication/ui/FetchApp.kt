package com.example.fetchapplication.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FetchApp() {
    val fetchItemsViewModel: FetchItemsViewModel = viewModel()
    HomeScreen(fetchItemsViewModel.uiState)
}