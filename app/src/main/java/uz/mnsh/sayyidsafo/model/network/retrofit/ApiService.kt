package uz.ilhomjon.sayyidsafo.network.retrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.ilhomjon.sayyidsafo.network.models.AudioResponse

interface ApiService {

    @GET("api/audios")
    suspend fun getAudiosByCategory(@Query("topic_id") topicId:Int):Response<AudioResponse>
}