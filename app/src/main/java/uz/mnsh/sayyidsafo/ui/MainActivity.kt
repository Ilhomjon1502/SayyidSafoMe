package uz.mnsh.sayyidsafo.ui

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import uz.ilhomjon.sayyidsafo.network.utils.NetworkHelper
import uz.mnsh.sayyidsafo.R
import uz.mnsh.sayyidsafo.databinding.ActivityMainBinding
import uz.mnsh.sayyidsafo.databinding.DialogInfoBinding
import uz.mnsh.sayyidsafo.model.MyObjects.audioLiveData
import uz.mnsh.sayyidsafo.utils.UiData

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var networkHelper: NetworkHelper
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        UiData.activityMainBinding = binding
        hasNetwork()

        setDrawerLayout()

        setMediaPlayer()

        binding.apply {
            bottomNavigation.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.homeFragment -> {
                        findNavController(R.id.my_navigation_host).navigate(R.id.homeFragment2)
                    }
                    R.id.chosenFragment -> {
                        findNavController(R.id.my_navigation_host).navigate(R.id.saveFragment)
                    }
                    R.id.listenFragment -> {
                        findNavController(R.id.my_navigation_host).navigate(R.id.mediaFragment)
                    }
                }
                true
            }
        }
    }

    private fun setMediaPlayer() {

        audioLiveData.observe(this){

        }
    }

    private fun setDrawerLayout() {
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.about -> {
                    val alertDialog = AlertDialog.Builder(this).create()
                    val itemDialog = DialogInfoBinding.inflate(layoutInflater)
                    alertDialog.setView(itemDialog.root)
                    alertDialog.show()
                }
            }
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.my_navigation_host).navigateUp()
    }

    private fun hasNetwork(){
        networkHelper = NetworkHelper(this)
        if (!networkHelper.isNetworkConnected())
            Toast.makeText(this, "Internetga ulaning!!!", Toast.LENGTH_LONG).show()
    }
}