package com.example.catsgame.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.catsgame.databinding.FragmentShopBinding
import com.example.catsgame.ui.ScreensNavigationViewModel
import com.example.catsgame.ui.choose.ChooseViewModel
import com.example.catsgame.ui.game.GameViewModel
import com.example.catsgame.util.Choose
import com.example.catsgame.util.ScreensNavigation
import kotlinx.coroutines.launch

class ShopFragment : Fragment() {

    private val screensNavigationViewModel: ScreensNavigationViewModel by activityViewModels()
    private val chooseViewModel: ChooseViewModel by activityViewModels()
    private val gameViewModel: GameViewModel by activityViewModels()
    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopBinding.inflate(inflater, container, false)
        binding.btnBackShop.setOnClickListener {
            screensNavigationViewModel.loadState(ScreensNavigation.START)
        }
        binding.btnDog.setOnClickListener {
            chooseViewModel.loadState(Choose.DOG)
        }
        binding.btnCat.setOnClickListener {
            chooseViewModel.loadState(Choose.CAT)
        }
        binding.btnBowl.setOnClickListener {
            if (chooseViewModel.stateChoose.value == Choose.CAT)
            {
                if (gameViewModel.stateCatScore.value >= 200)
                    gameViewModel.loadStateCat(gameViewModel.stateCatScore.value - 200)
            }
            else
                if (gameViewModel.stateDogScore.value >= 200)
                    gameViewModel.loadStateDog(gameViewModel.stateDogScore.value - 200)

        }
        binding.btnBrush.setOnClickListener {
            if (chooseViewModel.stateChoose.value == Choose.CAT)
            {
                if (gameViewModel.stateCatScore.value >= 1000)
                    gameViewModel.loadStateCat(gameViewModel.stateCatScore.value - 1000)
            }
            else
                if (gameViewModel.stateDogScore.value >= 1000)
                    gameViewModel.loadStateDog(gameViewModel.stateDogScore.value - 1000)

        }
        binding.btnCollar.setOnClickListener {
            if (chooseViewModel.stateChoose.value == Choose.CAT)
            {
                if (gameViewModel.stateCatScore.value >= 500)
                    gameViewModel.loadStateCat(gameViewModel.stateCatScore.value - 500)
            }
            else
                if (gameViewModel.stateDogScore.value >= 500)
                    gameViewModel.loadStateDog(gameViewModel.stateDogScore.value - 500)

        }
        lifecycleScope.launch {
            chooseViewModel.stateChoose.collect {
                when (it) {
                    Choose.CAT -> {
                        binding.ivCat.visibility = View.VISIBLE
                        binding.ivDog.visibility = View.INVISIBLE
                        binding.tvMoneyCat.visibility = View.VISIBLE
                        binding.tvMoneyDog.visibility = View.INVISIBLE
                        binding.btnDog.visibility = View.VISIBLE
                        binding.btnCat.visibility = View.INVISIBLE
                    }
                    Choose.DOG -> {
                        binding.ivCat.visibility = View.INVISIBLE
                        binding.ivDog.visibility = View.VISIBLE
                        binding.tvMoneyCat.visibility = View.INVISIBLE
                        binding.tvMoneyDog.visibility = View.VISIBLE
                        binding.btnDog.visibility = View.INVISIBLE
                        binding.btnCat.visibility = View.VISIBLE
                    }
                }
            }
        }
        lifecycleScope.launch {
            gameViewModel.stateCatScore.collect {
                binding.tvMoneyCat.text = it.toString()
            }
        }
        lifecycleScope.launch {
            gameViewModel.stateDogScore.collect {
                binding.tvMoneyDog.text = it.toString()
            }
        }
        return binding.root
    }
}