package uz.mnsh.sayyidsafo.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ilhomjon.sayyidsafo.network.models.AudioResponse
import uz.ilhomjon.sayyidsafo.network.models.Data
import uz.mnsh.sayyidsafo.databinding.ItemMusicBinding

class AudioAdapter(val list: List<Data>) : RecyclerView.Adapter<AudioAdapter.Vh>() {

    inner class Vh(var itemRv: ItemMusicBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(audioResponse: Data) {
            itemRv.tvMusicName.text = audioResponse.name
            itemRv.tvTimer.text = millisecondTo(audioResponse.duration.toInt())
            itemRv.tvCloud.text = kbToMb(audioResponse.size.toDouble()).toString()+" MB"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
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
}