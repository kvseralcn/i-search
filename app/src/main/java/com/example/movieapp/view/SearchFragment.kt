package com.example.movieapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.SearchAdapter
import com.example.movieapp.databinding.FragmentSearchBinding
import com.example.movieapp.util.MusicPlayer
import com.example.movieapp.viewmodel.SearchFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val searchFragmentViewModel: SearchFragmentViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var musicPlayer: MusicPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        searchFragmentViewModel.data.observe(viewLifecycleOwner) {
            val searchFragmentAdapter = SearchAdapter(it.results) { clickedItem ->
                musicPlayer.playMusic(clickedItem.previewUrl)
            }
            binding.recyclerView.adapter = searchFragmentAdapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendSearchRequest()
    }

    private fun sendSearchRequest() {
        val input = "michael jackson" // TODO: edit text
        val param =
            input.replace(" ", "+") // TODO: extension olara yazılabilir. String.getURLEncoded v.s
        searchFragmentViewModel.loadContents(param)
    }


}