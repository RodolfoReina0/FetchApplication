package com.example.fetchapplication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.fetchapplication.R
import com.example.fetchapplication.network.Data
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.format.TextStyle


@Composable
fun HomeScreen(uiState: UiState) {
    when (uiState) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Success -> ResultScreen(uiState.items)
        is UiState.Error -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen(){
    Image(
        painter = painterResource(R.drawable.ic_loading),
        contentDescription = stringResource(R.string.loading)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(itemsList: List<Data>) {
    
//    Display all the items grouped by "listId"
    
    val groupedItems = itemsList.groupBy { it.listId }
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Fetch") }) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            groupedItems.forEach {(listId, items ) ->
                item {
                    SectionHeader("List Id: $listId")
                }
                items(items) {item ->
                    ListItem(item)
                }
            }
        }
    }
}

@Composable
fun SectionHeader(headerText: String) {
    Text(
        text = headerText,
        style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun ListItem(item: Data) {
    Column {
        Text(text= "ListID: ${item.listId}")
        Text(text = "Id: ${item.id}")
        Text(text = "Name: ${item.name}")
        Divider(thickness = 5.dp)
    }
}

@Composable
fun ErrorScreen(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_broken_image),
            stringResource(R.string.loading_failed )
        )
        Text(text = "Failed to load. Check your connection")
    }
}



