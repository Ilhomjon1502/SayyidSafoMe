package uz.mnsh.sayyidsafo.ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import uz.mnsh.sayyidsafo.R
import uz.mnsh.sayyidsafo.adapters.SectionAdapter
import uz.mnsh.sayyidsafo.databinding.FragmentHomeBinding
import uz.mnsh.sayyidsafo.utils.Sections
import uz.mnsh.sayyidsafo.utils.UiData

class HomeFragment : Fragment(), SectionAdapter.SectionClick {
    lateinit var binding: FragmentHomeBinding
    lateinit var sectionAdapter:SectionAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.imgMenu.setOnClickListener {
            UiData.activityMainBinding?.drawerLayout?.openDrawer(Gravity.START);
        }

        UiData.addSections()
        sectionAdapter = SectionAdapter(UiData.arrayListSections, this)
        binding.recyclerView.adapter = sectionAdapter
        return binding.root
    }

    override fun sectionClick(sections: Sections, position: Int) {
        findNavController().navigate(R.id.listenFragment2, bundleOf("keyPosition" to position))
    }
}