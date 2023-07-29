package com.example.movieapp.ui.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.SearchAdapter
import com.example.movieapp.databinding.FragmentSearchBinding
import com.example.movieapp.extension.getURLEncoded
import com.example.movieapp.ui.search.domain.SearchViewModel
import com.example.movieapp.util.MusicPlayer
import com.example.movieapp.view.searchbar.SearchBarListener
import dagger.hilt.android.AndroidEntryPoint
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

        binding.fragmentSearchRvSearch.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        searchViewModel.data.observe(viewLifecycleOwner) {
            val searchFragmentAdapter = SearchAdapter(it.results) { clickedItem ->
                musicPlayer.playMusic(clickedItem.previewUrl)
            }
            binding.fragmentSearchRvSearch.adapter = searchFragmentAdapter
        }
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