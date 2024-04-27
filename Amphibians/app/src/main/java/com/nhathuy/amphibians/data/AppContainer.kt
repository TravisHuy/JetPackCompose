package com.nhathuy.amphibians.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nhathuy.amphibians.network.AmphibiansApiServer
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val amphibiansRepository:AmphibiansRepository
}

class DefaultAppContainer :AppContainer{
    private val baseUrl= "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit:Retrofit =Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()
    private val retrofitServer:AmphibiansApiServer by lazy {
        retrofit.create(AmphibiansApiServer::class.java)
    }
    override val amphibiansRepository: AmphibiansRepository by lazy {
        NetworkAmphibiansRepository(retrofitServer)
    }

}