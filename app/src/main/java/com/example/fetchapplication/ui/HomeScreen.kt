package com.example.fetchapplication.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        topBar = { TopAppBar(
            title = { Text(text = "Fetch App(lication)", textAlign = TextAlign.Center) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFED7D31),
                titleContentColor = Color(0xFFF6F1EE)
            )
        ) },
        containerColor = Color(0xFF6C5F5B)
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
        fontSize = 20.sp,
        style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold),
        color = Color(0xFFF6F1EE),
        modifier = Modifier.padding(16.dp),
    )
}

@Composable
fun ListItem(item: Data) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF4F4A45),
            contentColor = Color(0xFFF6F1EE)
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text= "ListID: ${item.listId}")
            Text(text = "Id: ${item.id}")
            Text(text = "Name: ${item.name}")
        }
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



