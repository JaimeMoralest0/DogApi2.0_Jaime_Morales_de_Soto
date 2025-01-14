package com.example.dogapi.retro
import com.example.dogapi.data.DogImageResponse
import retrofit2.http.GET
import com.example.dogapi.data.BreedListResponse
interface DogApiService {
    @GET("breed/hound/images")
    suspend fun getHoundImages(): DogImageResponse

    @GET("breeds/list/all")
    suspend fun getBreedList(): BreedListResponse
}