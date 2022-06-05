package uz.ilhomjon.sayyidsafo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import uz.ilhomjon.sayyidsafo.network.models.AudioResponse
import uz.ilhomjon.sayyidsafo.network.repository.AudiosRepository
import uz.ilhomjon.sayyidsafo.network.retrofit.ApiClient
import uz.ilhomjon.sayyidsafo.network.utils.NetworkHelper
import uz.ilhomjon.sayyidsafo.network.utils.Resource
import uz.mnsh.sayyidsafo.database.AppDatabase
import java.io.File

class UserViewModel(val audiosRepository: AudiosRepository, val networkHelper: NetworkHelper, val appDatabase: AppDatabase) : ViewModel(){

    private val liveData = MutableLiveData<Resource<AudioResponse>>()

    fun getUsers(id:Int): LiveData<Resource<AudioResponse>> {
            viewModelScope.launch {
            liveData.postValue(Resource.loading(null))

//                val dataList = AudioResponse(audiosRepository.getLocalAudios(id)).data as ArrayList
//                dataList.forEach {
//                    if (!File(it.filePath!!).isFile){
//                        dataList.remove(it)
//                        appDatabase.myDao().deleteAudio(it)
//                    }
//                }

                if (networkHelper.isNetworkConnected()){
                    try {

                        val audioResponse = audiosRepository.getAudios(id)
                        if (audioResponse.isSuccessful) {
                            liveData.postValue(Resource.success(audioResponse.body()))

                        } else {
                            liveData.postValue(Resource.error(audioResponse.message(), null))
                        }
                    }catch (e:Exception){
                        liveData.postValue(Resource.error("Internet", null))
                    }
                }else{

//                    if (dataList.isNotEmpty())
//                    liveData.postValue(Resource.success(AudioResponse(audiosRepository.getLocalAudios(id))))
//                    else
                        liveData.postValue(Resource.error("Database is empty", null))
                }

        }
        return liveData
    }
}