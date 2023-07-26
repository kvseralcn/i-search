package com.example.movieapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.btnTest.setOnClickListener {
        //    sendSearchRequest()
        //}

        //  mainViewModel.data.observe(this) {
        //      //it.results
        //  }
    }

    // private fun sendSearchRequest() {
    //     val input = "michael jackson" // TODO: edit text
    //     val param = input.replace(" ", "+") // TODO: extension olara yazılabilir. String.getURLEncoded v.s
    //     mainViewModel.loadContents(param)
    // }
}