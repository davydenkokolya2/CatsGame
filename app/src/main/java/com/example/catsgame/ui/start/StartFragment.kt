package com.example.catsgame.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.catsgame.databinding.FragmentStartBinding
import com.example.catsgame.ui.ScreensNavigationViewModel
import com.example.catsgame.util.ScreensNavigation

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val screensNavigationViewModel: ScreensNavigationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater, container, false)

        binding.btnPlay.setOnClickListener {
            screensNavigationViewModel.loadState(ScreensNavigation.RULES)
        }

        binding.btnSetting.setOnClickListener {
            screensNavigationViewModel.loadState(ScreensNavigation.SETTING)
        }

        binding.btnShop.setOnClickListener {
            screensNavigationViewModel.loadState(ScreensNavigation.SHOP)
        }
        return binding.root
    }

}