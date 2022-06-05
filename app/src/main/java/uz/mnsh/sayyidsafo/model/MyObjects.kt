package uz.mnsh.sayyidsafo.model

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import uz.ilhomjon.sayyidsafo.network.models.Data

object MyObjects {
    var mediaPlayer:MediaPlayer? = null
    var audioLiveData = MutableLiveData<Data>()
}