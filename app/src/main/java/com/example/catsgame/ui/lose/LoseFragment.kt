package com.example.catsgame.ui.lose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.catsgame.databinding.FragmentLoseBinding
import com.example.catsgame.ui.ScreensNavigationViewModel
import com.example.catsgame.ui.choose.ChooseViewModel
import com.example.catsgame.ui.game.GameViewModel
import com.example.catsgame.util.ScreensNavigation

class LoseFragment : Fragment() {

    private val screensNavigationViewModel: ScreensNavigationViewModel by activityViewModels()
    private val gameViewModel: GameViewModel by activityViewModels()
    private lateinit var binding: FragmentLoseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoseBinding.inflate(inflater, container, false)
        gameViewModel.loadState(0)
        binding.btnTryAgainDefeat.setOnClickListener {
            screensNavigationViewModel.loadState(ScreensNavigation.START)
        }
        return binding.root
    }


}