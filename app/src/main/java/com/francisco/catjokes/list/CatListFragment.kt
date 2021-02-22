package com.francisco.catjokes.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.francisco.catjokes.R
import com.francisco.catjokes.databinding.FragmentCatListBinding
import com.francisco.catjokes.list.domain.CatJoke
import com.francisco.catjokes.utilities.observeIn
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CatListFragment : Fragment(R.layout.fragment_cat_list) {

    private val viewModel: CatListViewModel by viewModels()
    private val adapter = CatsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupBinding(view)
    }

    private fun observeViewModel() {
        viewModel.jokes.asLiveData().observe(viewLifecycleOwner, { catJokes ->
            adapter.submitList(catJokes)
        })
        viewModel.eventsFlow
            .onEach {
                when (it) {
                    Event.HideSpinner -> hideSpinner()
                    Event.ShowError -> showError()
                }
            }
            .observeIn(this)
    }

    private fun showError() {
        TODO("Not yet implemented")
    }

    private fun hideSpinner() {
        TODO("Not yet implemented")
    }

    private fun setupBinding(view: View) {
        val binding = FragmentCatListBinding.bind(view)
        binding.recyclerView.adapter = adapter
    }
}