package com.mitsubishidestinatorgls.ppb_mod2_kel07

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AboutScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("About Page", style = MaterialTheme.typography.headlineMedium, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Aplikasi Daftar Anime", style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Mata Kuliah: Pemrograman Perangkat Bergerak", textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Kelompok 07", style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Anggota Kelompok:", style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
                Text("1. Galileo Athari Muhammad - 21120124130099", textAlign = TextAlign.Center)
                Text("2. Rayhan Cahya Qurnia - 21120124130046", textAlign = TextAlign.Center)
                Text("3. Alif Rizki Kurniawan Hariadi - 21120124140148", textAlign = TextAlign.Center)
                Text("4. Christian Duta Dungdungon Sihotang - 21120124140136", textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Dibuat menggunakan Kotlin dan Jetpack Compose", textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview(){
    AboutScreen()
}
