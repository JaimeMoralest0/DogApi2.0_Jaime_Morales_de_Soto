package com.example.dogapi.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogapi.retro.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class DogViewModel : ViewModel() {
    val dogImages = mutableListOf<String>()

    fun fetchDogImages() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.instance.getHoundImages()
                }
                if (response.status == "success") {
                    dogImages.clear()
                    dogImages.addAll(response.message)
                }
            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }
}
