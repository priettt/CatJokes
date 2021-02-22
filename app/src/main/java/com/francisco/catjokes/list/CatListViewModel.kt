package com.francisco.catjokes.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francisco.catjokes.list.domain.CatJoke
import com.francisco.catjokes.list.domain.action.GetCatJokes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatListViewModel @Inject constructor(
    private val getCatJokes: GetCatJokes
) : ViewModel() {

    val jokes: MutableStateFlow<List<CatJoke>?> = MutableStateFlow(null)

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            getCatJokes().onSuccess {
                jokes.value = it
            }.onFailure {
                eventChannel.send(Event.ShowError)
            }
        }
    }
}


sealed class Event {
    object ShowError : Event()
    object HideSpinner : Event()
}