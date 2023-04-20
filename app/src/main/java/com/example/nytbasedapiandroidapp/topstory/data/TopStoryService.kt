package com.example.nytbasedapiandroidapp.topstory.data

import com.example.nytbasedapiandroidapp.topstory.models.TopStories
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//NYTApiService.kt


interface NYTApiService {

    @GET("svc/topstories/v2/home.json")
    suspend fun getTopStories(@Query("api-key") apiKey: String): Response<TopStories>

    companion object {
        private const val BASE_URL = "https://api.nytimes.com/"

        fun create(): NYTApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(NYTApiService::class.java)
        }
    }
}
