package com.example.movieapp.ui.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.movieapp.adapter.ContentCategoryAdapter
import com.example.movieapp.adapter.ContentSuggestionsAdapter
import com.example.movieapp.adapter.SearchAdapter
import com.example.movieapp.core.MainActivity
import com.example.movieapp.databinding.FragmentSearchBinding
import com.example.movieapp.extension.extractUniqueGenreNames
import com.example.movieapp.extension.getURLEncoded
import com.example.movieapp.ui.search.domain.SearchViewModel
import com.example.movieapp.util.MusicPlayer
import com.example.movieapp.view.searchbar.SearchBarListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var searchAdapter: SearchAdapter
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
        binding.fragmentSearchRvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentSearchRvSuggestions.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        searchViewModel.data.observe(viewLifecycleOwner) {
            (activity as MainActivity).hideProgress()

            if (it.results.isEmpty()) {
                binding.fragmentSearchTvNotFound.isVisible = true
                binding.fragmentSearchRvSearch.isVisible = false
            } else {
                binding.fragmentSearchTvNotFound.isVisible = false

                val categoryAdapter =
                    ContentCategoryAdapter(it.results.extractUniqueGenreNames()) { category ->
                        searchAdapter.filter(category)
                    }
                binding.fragmentSearchRvCategory.adapter = categoryAdapter

                searchAdapter = SearchAdapter(it.results, onReplayClick = {
                    musicPlayer.replayMusic()
                })
                { clickedItem, position ->
                    if (musicPlayer.isPlaying()) {
                        musicPlayer.pauseMusic()
                    } else {
                        musicPlayer.playMusic(clickedItem.previewUrl)
                    }
                    searchAdapter.notifyItemChanged(position)
                }
                searchAdapter.setHasStableIds(true)
                binding.fragmentSearchRvSearch.isVisible = true
                binding.fragmentSearchRvSearch.adapter = searchAdapter
                (binding.fragmentSearchRvSearch.itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
                    false
            }
            (activity as MainActivity).hideProgress()
        }

        val suggestionsAdapter = ContentSuggestionsAdapter(
            listOf(
                "michael jackson",
                "billie eilish",
                "kanye west",
                "jennifer lopez",
                "adele"
            )
        ) { suggestion ->
            binding.fragmentSearchSbSearch.setText(suggestion)
            sendSearchRequest(suggestion)
        }
        binding.fragmentSearchRvSuggestions.adapter = suggestionsAdapter

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
        if (binding.fragmentSearchLlSuggestions.isVisible) {
            binding.fragmentSearchLlSuggestions.isVisible = false
        }
        (activity as MainActivity).showProgress()
        val param = searchInput.getURLEncoded()
        searchViewModel.loadContents(param)
    }
}