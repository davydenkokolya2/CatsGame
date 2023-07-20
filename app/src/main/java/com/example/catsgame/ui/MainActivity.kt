package com.example.catsgame.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.catsgame.R
import com.example.catsgame.databinding.ActivityMainBinding
import com.example.catsgame.ui.choose.ChooseFragment
import com.example.catsgame.ui.game.GameFragment
import com.example.catsgame.ui.lose.LoseFragment
import com.example.catsgame.ui.rules.RulesFragment
import com.example.catsgame.ui.setting.SettingFragment
import com.example.catsgame.ui.setting.SettingViewModel
import com.example.catsgame.ui.shop.ShopFragment
import com.example.catsgame.ui.start.StartFragment
import com.example.catsgame.ui.win.WinFragment
import com.example.catsgame.util.ScreensNavigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mediaPlayer: MediaPlayer
    private val screensNavigationViewModel: ScreensNavigationViewModel by viewModels()
    private val settingViewModel: SettingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer.isLooping = true

        lifecycleScope.launch {
            screensNavigationViewModel.stateScreensNavigation.collect {
                when (it) {
                    ScreensNavigation.START -> replaceFragment(StartFragment())
                    ScreensNavigation.CHOOSE -> replaceFragment(ChooseFragment())
                    ScreensNavigation.GAME -> replaceFragment(GameFragment())
                    ScreensNavigation.SETTING -> replaceFragment(SettingFragment())
                    ScreensNavigation.LOSE -> replaceFragment(LoseFragment())
                    ScreensNavigation.SHOP -> replaceFragment(ShopFragment())
                    ScreensNavigation.RULES -> replaceFragment(RulesFragment())
                    ScreensNavigation.WIN -> replaceFragment(WinFragment())
                    else -> {}
                }
            }
        }
        lifecycleScope.launch {
            settingViewModel.stateMusic.collect {
                when (it) {
                    "on" -> {
                        if (settingViewModel.stateSound.value == "on")
                            mediaPlayer.start()
                    }
                    "off" -> mediaPlayer.pause()
                }
            }
        }
        lifecycleScope.launch {
            settingViewModel.stateSound.collect {
                when (it) {
                    "on" -> {
                        if (settingViewModel.stateMusic.value == "on")
                            mediaPlayer.start()
                    }
                    "off" -> mediaPlayer.pause()
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        mediaPlayer.start()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }
}