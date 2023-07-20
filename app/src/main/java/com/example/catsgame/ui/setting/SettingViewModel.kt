package com.example.catsgame.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsgame.util.Choose
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingViewModel : ViewModel() {
    private val _stateSound = MutableStateFlow<String>("on")
    val stateSound: StateFlow<String?> = _stateSound

    fun loadStateSound(sound: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateSound.emit(sound)
        }
    }

    private val _stateMusic = MutableStateFlow<String>("on")
    val stateMusic: StateFlow<String?> = _stateMusic

    fun loadStateMusic(music: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateMusic.emit(music)
        }
    }
}