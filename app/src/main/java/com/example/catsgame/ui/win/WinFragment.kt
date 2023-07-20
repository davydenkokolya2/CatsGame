package com.example.catsgame.ui.win

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.catsgame.databinding.FragmentWinBinding
import com.example.catsgame.ui.ScreensNavigationViewModel
import com.example.catsgame.ui.choose.ChooseViewModel
import com.example.catsgame.ui.game.GameViewModel
import com.example.catsgame.util.Choose
import com.example.catsgame.util.ScreensNavigation
import kotlinx.coroutines.launch

class WinFragment : Fragment() {

    private lateinit var binding: FragmentWinBinding
    private val screensNavigationViewModel: ScreensNavigationViewModel by activityViewModels()
    private val chooseViewModel: ChooseViewModel by activityViewModels()
    private val gameViewModel: GameViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWinBinding.inflate(inflater, container, false)
        binding.btnTryAgainWin.setOnClickListener {
            screensNavigationViewModel.loadState(ScreensNavigation.START)
        }
        gameViewModel.loadState(0)
        lifecycleScope.launch {
            chooseViewModel.stateChoose.collect {
                when (it) {
                    Choose.CAT -> {
                        binding.ivCatWin.visibility = View.VISIBLE
                        binding.ivDogWin.visibility = View.INVISIBLE
                        binding.tvScoreCat.visibility = View.VISIBLE
                        binding.tvScoreDog.visibility = View.INVISIBLE
                    }
                    Choose.DOG -> {
                        binding.ivCatWin.visibility = View.INVISIBLE
                        binding.ivDogWin.visibility = View.VISIBLE
                        binding.tvScoreCat.visibility = View.INVISIBLE
                        binding.tvScoreDog.visibility = View.VISIBLE
                    }
                }
            }
        }
        lifecycleScope.launch {
            gameViewModel.stateCatScore.collect {
                binding.tvScoreCat.text = it.toString()
            }
        }
        lifecycleScope.launch {
            gameViewModel.stateDogScore.collect {
                binding.tvScoreDog.text = it.toString()
            }
        }
        return binding.root
    }
}