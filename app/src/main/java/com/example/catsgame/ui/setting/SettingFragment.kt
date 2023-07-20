package com.example.catsgame.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.catsgame.R
import com.example.catsgame.databinding.FragmentSettingBinding
import com.example.catsgame.ui.ScreensNavigationViewModel
import com.example.catsgame.util.ScreensNavigation
import kotlinx.coroutines.launch

class SettingFragment : Fragment() {

    private val settingViewModel: SettingViewModel by activityViewModels()
    private lateinit var binding: FragmentSettingBinding
    private val screensNavigationViewModel: ScreensNavigationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        binding.btnBackSetting.setOnClickListener {
            screensNavigationViewModel.loadState(ScreensNavigation.START)
        }
        binding.btnOnMusic.setOnClickListener {
            settingViewModel.loadStateMusic("on")
        }
        binding.btnOffMusic.setOnClickListener {
            settingViewModel.loadStateMusic("off")
        }
        binding.btnOnSound.setOnClickListener {
            settingViewModel.loadStateSound("on")
        }
        binding.btnOffSound.setOnClickListener {
            settingViewModel.loadStateSound("off")
        }
        lifecycleScope.launch {
            settingViewModel.stateMusic.collect {
                when (it) {
                    "on" -> {
                        binding.btnOnMusic.setTextColor(
                            resources.getColor(
                                R.color.green,
                                resources.newTheme()
                            )
                        )
                        binding.btnOffMusic.setTextColor(
                            resources.getColor(
                                R.color.white,
                                resources.newTheme()
                            )
                        )
                    }
                    "off" -> {
                        binding.btnOffMusic.setTextColor(
                            resources.getColor(
                                R.color.green,
                                resources.newTheme()
                            )
                        )
                        binding.btnOnMusic.setTextColor(
                            resources.getColor(
                                R.color.white,
                                resources.newTheme()
                            )
                        )
                    }
                }
            }
        }
        lifecycleScope.launch {
            settingViewModel.stateSound.collect {
                when (it) {
                    "on" -> {
                        binding.btnOnSound.setTextColor(
                            resources.getColor(
                                R.color.green,
                                resources.newTheme()
                            )
                        )
                        binding.btnOffSound.setTextColor(
                            resources.getColor(
                                R.color.white,
                                resources.newTheme()
                            )
                        )
                    }
                    "off" -> {
                        binding.btnOffSound.setTextColor(
                            resources.getColor(
                                R.color.green,
                                resources.newTheme()
                            )
                        )
                        binding.btnOnSound.setTextColor(
                            resources.getColor(
                                R.color.white,
                                resources.newTheme()
                            )
                        )
                    }
                }
            }
        }
        return binding.root
    }
}