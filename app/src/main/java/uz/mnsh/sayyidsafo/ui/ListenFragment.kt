package uz.mnsh.sayyidsafo.ui

import android.app.AlertDialog
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import uz.ilhomjon.sayyidsafo.network.models.Data
import uz.ilhomjon.sayyidsafo.network.repository.AudiosRepository
import uz.ilhomjon.sayyidsafo.network.retrofit.ApiClient
import uz.ilhomjon.sayyidsafo.network.retrofit.ApiClient.BASE_URL
import uz.ilhomjon.sayyidsafo.network.utils.NetworkHelper
import uz.ilhomjon.sayyidsafo.network.utils.Status
import uz.ilhomjon.sayyidsafo.viewmodel.UserViewModel
import uz.mnsh.sayyidsafo.R
import uz.mnsh.sayyidsafo.adapters.AudioAdapter
import uz.mnsh.sayyidsafo.database.AppDatabase
import uz.mnsh.sayyidsafo.databinding.FragmentListenBinding
import uz.mnsh.sayyidsafo.databinding.ItemMusicBinding
import uz.mnsh.sayyidsafo.model.MyObjects.mediaPlayer
import uz.mnsh.sayyidsafo.utils.Sections
import uz.mnsh.sayyidsafo.utils.UiData
import uz.mnsh.sayyidsafo.utils.getProgressDisplayLine
import uz.mnsh.sayyidsafo.utils.getRootDirPath
import uz.mnsh.sayyidsafo.viewmodel.ViewModelFactory
import java.io.File


class ListenFragment : Fragment(), AudioAdapter.RvClick {

    lateinit var binding: FragmentListenBinding
    lateinit var userViewModel: UserViewModel
    var position = -1
    lateinit var sections: Sections
    private val TAG = "ListenFragment"
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListenBinding.inflate(layoutInflater)
        position = arguments?.getInt("keyPosition", -1)!!
        appDatabase = AppDatabase.getInstance(binding.root.context)

        if (position != -1)
            loadDada()

        sections = UiData.arrayListSections[position]

        binding.apply {
            imgBack.setOnClickListener { findNavController().popBackStack() }
            tvLessonTitle.text = sections.name
            tvLessonSubtitle.text = sections.description
        }

        PRDownloader.initialize(context)

        return binding.root
    }

    private fun loadDada() {
        val audiosRepository =
            AudiosRepository(ApiClient.apiService(), appDatabase)
        val networkHelper = NetworkHelper(binding.root.context)
        userViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelFactory(audiosRepository, networkHelper, appDatabase)
        )[UserViewModel::class.java]

        userViewModel.getUsers(position + 2).observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.rvProgress.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    val dialog = AlertDialog.Builder(context)
                    dialog.setTitle("Xabarnoma")
                    dialog.setMessage("Internetga ulanishni tekshiring, ma'lumotlar yetib kelmadi")
                    dialog.show()
                    binding.rvProgress.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.rvProgress.visibility = View.GONE

                    appDatabase = AppDatabase.getInstance(binding.root.context)
                    val audioAdapter = AudioAdapter(it.data?.data!!, appDatabase,this)
                    binding.recyclerLessons.adapter = audioAdapter

                    Log.d("TAG", "loadDada: ${it.data?.data}")
                }
            }
        }
    }

    override fun onClick(audioResponse: Data, position: Int, itemMusicBinding: ItemMusicBinding) {

        if (audioResponse.filePath!=null){
            if (mediaPlayer!=null)
                mediaPlayer?.stop()
            mediaPlayer = MediaPlayer.create(context, Uri.parse(audioResponse.filePath))
            mediaPlayer?.start()

        }else{
            downloatAudio(
                audioResponse,
                "${BASE_URL}storage/${audioResponse.location}",
                audioResponse.name,
                itemMusicBinding
            )
        }
        Log.d(TAG, "onClick: ")
    }

    fun downloatAudio(
        audioResponse: Data,
        uri: String,
        name: String,
        itemMusicBinding: ItemMusicBinding
    ) {

        val downloadId = PRDownloader.download(uri, getRootDirPath(binding.root.context), audioResponse.name)
            .build()
            .setOnStartOrResumeListener {
                Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
                itemMusicBinding.progressBar.visibility = View.VISIBLE
            }
            .setOnPauseListener { }
            .setOnProgressListener {
                itemMusicBinding.tvCloud.text =
                    getProgressDisplayLine(it.currentBytes, it.totalBytes)
            }
            .start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    itemMusicBinding.progressBar.visibility = View.GONE
                    itemMusicBinding.imageDownloadPlay.setImageResource(R.drawable.ic_play)
                    audioResponse.filePath = getRootDirPath(binding.root.context) + File.separator + audioResponse.name
                    appDatabase.myDao().addUsers(audioResponse)
                }

                override fun onError(error: Error?) {
                    itemMusicBinding.tvCloud.text = "Xatolik"
                }
            })
        Log.d(TAG, "downloatAudio: ")
    }
}