package uz.mnsh.sayyidsafo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.mnsh.sayyidsafo.R
import uz.mnsh.sayyidsafo.databinding.FragmentMediaBinding

class MediaFragment : Fragment() {

    private lateinit var binding:FragmentMediaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMediaBinding.inflate(layoutInflater)


        return binding.root
    }

}