package com.example.taskmanager.ui.main.network

import com.example.taskmanager.ui.main.data.Task
import com.example.taskmanager.ui.main.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApiService {

    @GET("/todos")
    suspend fun getUserTasks(@Query("userId") userId: String): List<Task>

    companion object {

        fun createService(): RestApiService {
            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient())
                .build()
                .create(RestApiService::class.java)
        }
    }
}