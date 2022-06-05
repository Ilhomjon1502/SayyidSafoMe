package uz.mnsh.sayyidsafo.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ilhomjon.sayyidsafo.network.models.AudioResponse
import uz.ilhomjon.sayyidsafo.network.models.Data
import uz.mnsh.sayyidsafo.R
import uz.mnsh.sayyidsafo.database.AppDatabase
import uz.mnsh.sayyidsafo.databinding.ItemMusicBinding

class AudioAdapter(val list: List<Data>,val appDatabase: AppDatabase, val rvClick: RvClick) : RecyclerView.Adapter<AudioAdapter.Vh>() {

    private val TAG = "AudioAdapter"
    inner class Vh(var itemRv: ItemMusicBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(audioResponse: Data, position: Int) {
            itemRv.tvMusicName.text = audioResponse.name
            itemRv.tvTimer.text = millisecondTo(audioResponse.duration.toInt())

            if (appDatabase.myDao().getIdList().contains(audioResponse.id)){
                audioResponse.filePath = appDatabase.myDao().getDataById(audioResponse.id).filePath
                Log.d(TAG, "onBind: $audioResponse")
                itemRv.imageDownloadPlay.setImageResource(R.drawable.ic_play)
                itemRv.tvCloud.visibility = View.GONE
                itemRv.imageCloud.visibility = View.GONE
            }else{
                itemRv.tvCloud.text = kbToMb(audioResponse.size.toDouble()).toString()+" MB"
            }

            itemRv.root.setOnClickListener {
                rvClick.onClick(audioResponse, position, itemRv)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    private fun millisecondTo(milliseconds:Int):String{
        val minutes = milliseconds  / 60
        val seconds = milliseconds  % 60

        return "$minutes:$seconds"
    }

    private fun kbToMb(kb:Double):Double{
        val MB = (kb*10/1024).toInt()

        return MB.toDouble()/10
    }

    interface RvClick{
        fun onClick(audioResponse: Data, position: Int, itemRv: ItemMusicBinding)
    }
}