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

class UserViewModel(val audiosRepository: AudiosRepository, val networkHelper: NetworkHelper) : ViewModel(){

    private val liveData = MutableLiveData<Resource<AudioResponse>>()

    fun getUsers(id:Int): LiveData<Resource<AudioResponse>> {
            viewModelScope.launch {
            liveData.postValue(Resource.loading(null))
                if (networkHelper.isNetworkConnected()){
                    val audioResponse = audiosRepository.getAudios(id)
                    if (audioResponse.isSuccessful){
                        liveData.postValue(Resource.success(audioResponse.body()))
                        audiosRepository.addAudiosList(audioResponse.body()!!)
                    }else{
                        liveData.postValue(Resource.error(audioResponse.message(), null))
                    }
                }else{
                    liveData.postValue(Resource.success(AudioResponse(audiosRepository.getLocalAudios(id))))
                }

        }
        return liveData
    }
}