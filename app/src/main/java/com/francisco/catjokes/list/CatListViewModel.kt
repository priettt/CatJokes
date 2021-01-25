package com.francisco.catjokes.list

import androidx.hilt.lifecycle.ViewModelInject
import com.francisco.catjokes.list.domain.action.GetCatJokes

class CatListViewModel @ViewModelInject constructor(
     private val getCatJokes: GetCatJokes
) {
}