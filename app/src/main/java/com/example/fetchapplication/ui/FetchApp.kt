package com.example.fetchapplication.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FetchApp() {
    val itemsViewModel: ItemsViewModel = viewModel()
    HomeScreen(itemsViewModel.uiState)
}