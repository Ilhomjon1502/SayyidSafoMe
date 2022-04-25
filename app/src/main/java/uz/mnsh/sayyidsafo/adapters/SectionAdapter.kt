package uz.mnsh.sayyidsafo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.mnsh.sayyidsafo.databinding.ItemLessonBinding
import uz.mnsh.sayyidsafo.utils.Sections

class SectionAdapter(val list: List<Sections>, val sectionClick: SectionClick) : RecyclerView.Adapter<SectionAdapter.Vh>() {

    inner class Vh(var itemRv: ItemLessonBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(sections: Sections, position: Int) {
            Glide.with(itemRv.root).load(sections.image).into(itemRv.imgLesson)
            itemRv.tvLessonName.text = sections.name
            itemRv.tvLessonSum.text = sections.count
            itemRv.root.setOnClickListener { sectionClick.sectionClick(sections, position) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    interface SectionClick{
        fun sectionClick(sections: Sections, position: Int)
    }
}