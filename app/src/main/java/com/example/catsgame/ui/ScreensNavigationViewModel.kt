package com.example.catsgame.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsgame.util.ScreensNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreensNavigationViewModel @Inject constructor() : ViewModel() {
    private val _stateScreensNavigation = MutableStateFlow<ScreensNavigation?>(ScreensNavigation.START)
    val stateScreensNavigation: StateFlow<ScreensNavigation?> = _stateScreensNavigation

    fun loadState(navigation: ScreensNavigation) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateScreensNavigation.emit(navigation)
        }
    }
}