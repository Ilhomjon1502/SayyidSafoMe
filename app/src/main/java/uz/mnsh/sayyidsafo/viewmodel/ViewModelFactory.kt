package uz.mnsh.sayyidsafo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.ilhomjon.sayyidsafo.network.repository.AudiosRepository
import uz.ilhomjon.sayyidsafo.network.utils.NetworkHelper
import uz.ilhomjon.sayyidsafo.viewmodel.UserViewModel

class ViewModelFactory(
    private val audiosRepository: AudiosRepository,
    val networkHelper: NetworkHelper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(audiosRepository, networkHelper) as T
        }
        throw IllegalArgumentException("Error")
    }

}