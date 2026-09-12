package com.mitsubishidestinatorgls.ppb_mod2_kel07

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeDetailScreen(
    animeId: Int,
    navController: NavController,
    viewModel: AnimeViewModel = viewModel()
) {
    val anime by viewModel.selectedAnime.collectAsState()

    LaunchedEffect(animeId) {
        viewModel.getAnimeDetail(animeId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(anime?.title ?: "Detail Anime") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (anime == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(anime!!.images.jpg.image_url),
                    contentDescription = anime!!.title,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 16.dp)
                )
                Text(anime!!.title, style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Type: ${anime!!.type ?: "-"}")
                        Text("Episodes: ${anime!!.episodes ?: 0}")
                        Text("Rating: ${anime!!.score?.toString() ?: "N/A"}")
                        Text("Status: ${anime!!.status ?: "-"}")
                        Text("Tanggal tayang: ${anime!!.aired?.string ?: "-"}")
                        Text("Jumlah anggota: ${anime!!.members ?: 0}")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Synopsis", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(anime!!.synopsis ?: "No synopsis available.")
            }
        }
    }
}
