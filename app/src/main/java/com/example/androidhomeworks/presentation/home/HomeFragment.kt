package com.example.androidhomeworks.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.androidhomeworks.adapter.StatisticsAdapter
import com.example.androidhomeworks.common.Resource
import com.example.androidhomeworks.presentation.base.BaseFragment
import com.example.androidhomeworks.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val homeViewModel: HomeViewModel by viewModels()
    private val statisticsAdapter by lazy {
        StatisticsAdapter()
    }

    override fun setUp() {
        homeViewModel.fetchStatistics()

        binding.viewPager.adapter = statisticsAdapter

        lifecycleScope.launch {
            homeViewModel.statisticsState.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                    }

                    is Resource.Success -> {
                        val statisticsList = resource.data
                        statisticsAdapter.submitList(statisticsList)
                    }

                    is Resource.Error -> {
                        val errorMessage = resource.errorMessage
                    }
                }
            }
        }

        setupViewPager()
    }

    private fun setupViewPager() {
        binding.viewPager.adapter = statisticsAdapter

        val transformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))

            addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.14f
            }
        }

        binding.viewPager.apply {
            adapter = statisticsAdapter

            setPageTransformer(transformer)

            offscreenPageLimit = 3

            clipToPadding = false
            clipChildren = false
            setPadding(80, 0, 80, 0)
        }
    }
}
