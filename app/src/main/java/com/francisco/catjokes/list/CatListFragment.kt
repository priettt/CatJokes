package com.francisco.catjokes.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.francisco.catjokes.R
import com.francisco.catjokes.databinding.FragmentCatListBinding
import com.francisco.catjokes.list.domain.CatJoke

class CatListFragment : Fragment(R.layout.fragment_cat_list) {

    private val viewModel: CatListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBinding(view)
    }

    private fun setupBinding(view: View) {
        val binding = FragmentCatListBinding.bind(view)
        val catsAdapter = CatsAdapter()
        binding.recyclerView.adapter = catsAdapter
        catsAdapter.submitList(listOf(
            CatJoke(1, "https://cdn2.thecatapi.com/images/5c1.jpg", "The opposite of courage in our society is not cowardice... it is conformity"),
            CatJoke(2, "https://cdn2.thecatapi.com/images/aa2.jpg", "We are all works in progress"),
            CatJoke(3, "https://cdn2.thecatapi.com/images/aa3.jpg", "We are all works in progress"),
            CatJoke(4, "https://cdn2.thecatapi.com/images/aa4.jpg", "We believe in you"),
            CatJoke(5, "https://cdn2.thecatapi.com/images/aa5.jpg", "We are all "),
            CatJoke(6, "https://cdn2.thecatapi.com/images/aa6.jpg", "Dale a tu cuerpo alegria macarena"),
            CatJoke(7, "https://cdn2.thecatapi.com/images/aa7.jpg", "EEE MACARENA"),
            CatJoke(8, "https://cdn2.thecatapi.com/images/a3a.jpg", "I believe in you"),
        ))
    }
}