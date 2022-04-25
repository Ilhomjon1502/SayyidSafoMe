package uz.mnsh.sayyidsafo.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.ilhomjon.sayyidsafo.network.repository.AudiosRepository
import uz.ilhomjon.sayyidsafo.network.retrofit.ApiClient
import uz.ilhomjon.sayyidsafo.network.utils.NetworkHelper
import uz.ilhomjon.sayyidsafo.network.utils.Status
import uz.ilhomjon.sayyidsafo.viewmodel.UserViewModel
import uz.mnsh.sayyidsafo.R
import uz.mnsh.sayyidsafo.adapters.AudioAdapter
import uz.mnsh.sayyidsafo.database.AppDatabase
import uz.mnsh.sayyidsafo.databinding.FragmentListenBinding
import uz.mnsh.sayyidsafo.utils.Sections
import uz.mnsh.sayyidsafo.utils.UiData
import uz.mnsh.sayyidsafo.viewmodel.ViewModelFactory


class ListenFragment : Fragment() {

    lateinit var binding:FragmentListenBinding
    lateinit var userViewModel:UserViewModel
    var position = -1
    lateinit var sections: Sections

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListenBinding.inflate(layoutInflater)
        position = arguments?.getInt("keyPosition", -1)!!

        if (position!=-1)
        loadDada()

        sections = UiData.arrayListSections[position]

        binding.apply {
            imgBack.setOnClickListener { findNavController().popBackStack() }
            tvLessonTitle.text = sections.name
            tvLessonSubtitle.text = sections.description
        }

        return binding.root
    }

    private fun loadDada() {
        val audiosRepository = AudiosRepository(ApiClient.apiService(), AppDatabase.getInstance(binding.root.context))
        val networkHelper = NetworkHelper(binding.root.context)
        userViewModel = ViewModelProvider(requireActivity(), ViewModelFactory(audiosRepository, networkHelper))[UserViewModel::class.java]

        userViewModel.getUsers(position+2).observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.rvProgress.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                   Toast.makeText(context, "Error...\nInternetga qayta bo'glaning", Toast.LENGTH_SHORT).show()
                    binding.rvProgress.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.rvProgress.visibility = View.GONE

                    val audioAdapter = AudioAdapter(it.data?.data!!)
                    binding.recyclerLessons.adapter = audioAdapter

                    Log.d("TAG", "loadDada: ${it.data?.data}")
                }
            }
        }
    }

}