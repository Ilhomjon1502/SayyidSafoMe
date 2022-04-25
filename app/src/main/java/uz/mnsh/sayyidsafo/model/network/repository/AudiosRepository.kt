package uz.ilhomjon.sayyidsafo.network.repository

import uz.ilhomjon.sayyidsafo.network.models.AudioResponse
import uz.ilhomjon.sayyidsafo.network.models.Data
import uz.ilhomjon.sayyidsafo.network.retrofit.ApiService
import uz.mnsh.sayyidsafo.database.AppDatabase

class AudiosRepository(val apiService: ApiService, val appDatabase: AppDatabase) {

    suspend fun getAudios(id:Int) = apiService.getAudiosByCategory(id)
    suspend fun getLocalAudios(id: Int) = appDatabase.myDao().getAllUsers(id)
    suspend fun addAudiosList(audioResponse: AudioResponse) = appDatabase.myDao().addUsers(audioResponse.data)
}