package uz.mnsh.sayyidsafo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ilhomjon.sayyidsafo.network.models.AudioResponse
import uz.ilhomjon.sayyidsafo.network.models.Data
import uz.mnsh.sayyidsafo.databinding.ItemMusicBinding

class AudioAdapter(val list: List<Data>) : RecyclerView.Adapter<AudioAdapter.Vh>() {

    inner class Vh(var itemRv: ItemMusicBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(audioResponse: Data) {
            itemRv.tvMusicName.text = audioResponse.name
            itemRv.tvTimer.text = audioResponse.duration
            itemRv.tvCloud.text = audioResponse.size
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemMusicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}