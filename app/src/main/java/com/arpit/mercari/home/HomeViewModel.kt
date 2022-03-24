package com.arpit.mercari.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val homeScreenEventsChannel = Channel<HomeScreenEvents>()
    val homeScreenEvents = homeScreenEventsChannel.receiveAsFlow()

    var showHome by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            if (Firebase.auth.currentUser == null) {
                homeScreenEventsChannel.send(NavigateToLoginScreen)
            } else {
                showHome = true
            }
        }
    }

}

sealed interface HomeScreenEvents
object NavigateToLoginScreen : HomeScreenEvents
