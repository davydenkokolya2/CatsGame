package com.example.catsgame.ui.choose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsgame.util.Choose
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChooseViewModel : ViewModel() {
    private val _stateChoose = MutableStateFlow<Choose>(Choose.CAT)
    val stateChoose: StateFlow<Choose> = _stateChoose

    fun loadState(choose: Choose) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateChoose.emit(choose)
        }
    }
}