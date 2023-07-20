package com.example.catsgame.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val _stateCatScore = MutableStateFlow<Int>(0)
    val stateCatScore: StateFlow<Int> = _stateCatScore

    fun loadStateCat(catScore: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateCatScore.emit(catScore)
        }
    }

    private val _stateDogScore = MutableStateFlow<Int>(0)
    val stateDogScore: StateFlow<Int> = _stateDogScore

    fun loadStateDog(dogScore: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateDogScore.emit(dogScore)
        }
    }

    private val _stateGame = MutableStateFlow<Int>(0)
    val stateGame: StateFlow<Int> = _stateGame

    fun loadState(game: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _stateGame.emit(game)
        }
    }
}