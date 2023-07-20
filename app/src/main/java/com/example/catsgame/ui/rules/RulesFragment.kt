package com.example.catsgame.ui.rules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.catsgame.databinding.FragmentRulesBinding
import com.example.catsgame.ui.ScreensNavigationViewModel
import com.example.catsgame.util.ScreensNavigation

class RulesFragment : Fragment() {

    private lateinit var binding: FragmentRulesBinding
    private val screensNavigationViewModel: ScreensNavigationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRulesBinding.inflate(inflater, container, false)

        binding.btnSkip.setOnClickListener {
            screensNavigationViewModel.loadState(ScreensNavigation.CHOOSE)
        }

        return binding.root
    }
}