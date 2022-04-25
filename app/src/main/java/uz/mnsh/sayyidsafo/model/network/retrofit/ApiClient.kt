package uz.ilhomjon.sayyidsafo.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val BASE_URL = "http://5.182.26.44:8080/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun apiService() : ApiService{
        return getRetrofit().create(ApiService::class.java)
    }
}