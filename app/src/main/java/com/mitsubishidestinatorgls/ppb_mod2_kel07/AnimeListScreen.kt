package com.mitsubishidestinatorgls.ppb_mod2_kel07

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun AnimeListScreen(
    viewModel: AnimeViewModel = viewModel(),
    onItemClick: (Int) -> Unit = {}
) {
    val animeList by viewModel.animeList.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchTopAnime()
    }

    val filteredList = if (searchQuery.isBlank()) {
        animeList
    } else {
        animeList.filter { it.title.contains(searchQuery, ignoreCase = true) }
    }

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Anime") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true
        )

        LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            items(filteredList) { anime ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { onItemClick(anime.mal_id) }
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(anime.images.jpg.image_url),
                            contentDescription = anime.title,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(anime.title)
                            Text("Type: ${anime.type ?: "-"}")
                            Text("Episodes: ${anime.episodes ?: 0}")
                            Text("Rating: ${anime.score?.toString() ?: "N/A"}")
                            Text("Status: ${anime.status ?: "-"}")
                            Text("Tanggal tayang: ${anime.aired?.string ?: "-"}")
                            Text("Jumlah anggota: ${anime.members ?: 0}")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeListScreenPreview(){
    AnimeListScreen()
}
