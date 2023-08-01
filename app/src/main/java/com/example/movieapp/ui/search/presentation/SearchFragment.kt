package com.example.movieapp.ui.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.SearchAdapter
import com.example.movieapp.core.MainActivity
import com.example.movieapp.databinding.FragmentSearchBinding
import com.example.movieapp.extension.getURLEncoded
import com.example.movieapp.ui.search.domain.SearchViewModel
import com.example.movieapp.util.MusicPlayer
import com.example.movieapp.view.searchbar.SearchBarListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var musicPlayer: MusicPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val coroutineScope = CoroutineScope(Dispatchers.Main)

        binding.fragmentSearchRvSearch.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        searchViewModel.data.observe(viewLifecycleOwner) {
            coroutineScope.launch {
                (activity as MainActivity).showProgress()
                delay(1000)
                if (it.results.isEmpty()) {
                    binding.fragmentSearchTvNotFound.isVisible = true
                    binding.fragmentSearchRvSearch.isVisible = false
                } else {
                    binding.fragmentSearchTvNotFound.isVisible = false
                    val searchFragmentAdapter = SearchAdapter(it.results) { clickedItem ->
                        musicPlayer.playMusic(clickedItem.previewUrl)
                    }
                    binding.fragmentSearchRvSearch.isVisible = true
                    binding.fragmentSearchRvSearch.adapter = searchFragmentAdapter
                }
                (activity as MainActivity).hideProgress()
            }
        }
        //TODO empty state component haline getir show fnk nunu 2 overload seklinde yaz birinde string id alacak digeri string alacak

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentSearchSbSearch.searchBarListener = object : SearchBarListener {
            override fun onSearchClick(searchInput: String) {
                sendSearchRequest(searchInput)
            }
        }
    }

    private fun sendSearchRequest(searchInput: String) {
        val param = searchInput.getURLEncoded()
        searchViewModel.loadContents(param)
    }
}