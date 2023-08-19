package com.kevser.isearch.ui.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.kevser.isearch.R
import com.kevser.isearch.adapter.ContentCategoryAdapter
import com.kevser.isearch.adapter.ContentSuggestionsAdapter
import com.kevser.isearch.adapter.SearchAdapter
import com.kevser.isearch.core.MainActivity
import com.kevser.isearch.data.ContentResultModel
import com.kevser.isearch.databinding.FragmentSearchBinding
import com.kevser.isearch.extension.extractUniqueGenreNames
import com.kevser.isearch.extension.getURLEncoded
import com.kevser.isearch.ui.search.domain.SearchViewModel
import com.kevser.isearch.util.MusicPlayer
import com.kevser.isearch.view.searchbar.SearchBarListener
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
        initAdapters()
        initObservers()
        return binding.root
    }

    private fun initAdapters() {
        binding.apply {
            fragmentSearchRvSearch.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            fragmentSearchRvCategory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            fragmentSearchRvSuggestions.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val suggestionsAdapter = ContentSuggestionsAdapter(
                searchViewModel.suggestionList
            ) { suggestion ->
                fragmentSearchSbSearch.setText(suggestion)
                sendSearchRequest(suggestion)
            }
            fragmentSearchRvSuggestions.adapter = suggestionsAdapter
        }
    }

    private fun initObservers() {
        searchViewModel.data.observe(viewLifecycleOwner) {
            (activity as MainActivity).hideProgress()

            if (it.results.isEmpty()) {
                binding.fragmentSearchTvNotFound.isVisible = true
                binding.fragmentSearchRvSearch.isVisible = false
            } else {
                binding.fragmentSearchTvNotFound.isVisible = false

                val list = mutableListOf<String>()
                list.add(0, getString(R.string.all))
                val uniqueGenreNames = it.results.extractUniqueGenreNames()
                list.addAll(uniqueGenreNames)

                val categoryAdapter =
                    ContentCategoryAdapter(list) { category ->
                        if (list.getOrNull(0) == category) {
                            searchAdapter.filter(null)
                        } else {
                            searchAdapter.filter(category)
                        }
                    }
                binding.fragmentSearchRvCategory.adapter = categoryAdapter

                searchAdapter = SearchAdapter(it.results, onReplayClick = {
                    musicPlayer.replayMusic()
                }, object : SearchAdapter.SearchAdapterListener {
                    override fun onPlay(contentResultModel: ContentResultModel) {
                        musicPlayer.playMusic(contentResultModel.previewUrl)
                    }

                    override fun onPause() {
                        musicPlayer.pauseMusic()
                    }

                })

                searchAdapter.setHasStableIds(true)
                binding.fragmentSearchRvSearch.isVisible = true
                binding.fragmentSearchRvSearch.adapter = searchAdapter
                (binding.fragmentSearchRvSearch.itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
                    false
            }
            (activity as MainActivity).hideProgress()
        }
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