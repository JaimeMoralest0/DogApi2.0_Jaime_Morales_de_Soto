package com.example.dogapi.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogapi.R
import com.example.dogapi.retro.RetrofitClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            try {
                // Obtener la lista de razas
                val breedListResponse = RetrofitClient.instance.getBreedList()
                val breeds = breedListResponse.message.keys.toList()

                val breedImages = mutableListOf<Pair<String, String>>()

                // Para cada raza, obtener una imagen
                for (breed in breeds) {
                    val imagesResponse = RetrofitClient.instance.getHoundImages() // Cambia si tienes un metodo
                    if (imagesResponse.status == "success" && imagesResponse.message.isNotEmpty()) {
                        breedImages.add(Pair(breed, imagesResponse.message.random())) // Una imagen aleatoria
                    }

                    // Limitar a las primeras 10 razas para no sobrecargar el RecyclerView
                    if (breedImages.size >= 20) break
                }

                recyclerView.adapter = BreedAdapter(breedImages)

            } catch (e: Exception) {
                e.printStackTrace() // Manejar errores de red o datos
            }
        }
    }
}
