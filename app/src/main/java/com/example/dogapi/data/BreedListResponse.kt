package com.example.dogapi.data
data class BreedListResponse(
    val message: Map<String, List<String>>, // Razas y subrazas
    val status: String
)
