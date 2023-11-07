package com.example.fetchapplication.network
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiService {
    @GET("/hiring.json")
    suspend fun getItems(): List<Data>
}

private const val BASE_URL =
    "https://fetch-hiring.s3.amazonaws.com/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

object RetrofitInstance {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}