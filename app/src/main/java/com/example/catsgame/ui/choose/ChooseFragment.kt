package com.example.catsgame.ui.choose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.catsgame.databinding.FragmentChooseBinding
import com.example.catsgame.ui.ScreensNavigationViewModel
import com.example.catsgame.util.Choose
import com.example.catsgame.util.ScreensNavigation
import kotlinx.coroutines.launch

class ChooseFragment : Fragment() {


    private lateinit var binding: FragmentChooseBinding
    private val chooseViewModel: ChooseViewModel by activityViewModels()
    private val screensNavigationViewModel: ScreensNavigationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChooseBinding.inflate(inflater, container, false)
        binding.btnOk.setOnClickListener {
            screensNavigationViewModel.loadState(ScreensNavigation.GAME)
        }
        binding.ivCatChoose.setOnClickListener {
            chooseViewModel.loadState(Choose.CAT)
        }
        binding.ivDogChoose.setOnClickListener {
            chooseViewModel.loadState(Choose.DOG)
        }
        lifecycleScope.launch {
            chooseViewModel.stateChoose.collect {
                when(it) {
                    Choose.CAT -> {
                        binding.ivCheckLeft.visibility = View.VISIBLE
                        binding.ivCheckRight.visibility = View.INVISIBLE
                    }
                    Choose.DOG -> {
                        binding.ivCheckRight.visibility = View.VISIBLE
                        binding.ivCheckLeft.visibility = View.INVISIBLE
                    }
                }
            }
        }
        return binding.root
    }
}