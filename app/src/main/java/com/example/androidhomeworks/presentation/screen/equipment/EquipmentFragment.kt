package com.example.androidhomeworks.presentation.screen.equipment

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomeworks.databinding.FragmentEquipmentBinding
import com.example.androidhomeworks.presentation.adapter.EquipmentAdapter
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import com.example.androidhomeworks.presentation.extension.lifecyclescope.lifecycleCollectLatest
import com.example.androidhomeworks.presentation.extension.lifecyclescope.showErrorSnackBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class EquipmentFragment :
    BaseFragment<FragmentEquipmentBinding>(FragmentEquipmentBinding::inflate) {
    private val equipmentViewModel: EquipmentViewModel by viewModels()
    private var debounceJob: Job? = null

    private val equipmentAdapter by lazy {
        EquipmentAdapter()
    }


    override fun setUp() {

        equipmentViewModel.onEvent(EquipmentEvent.FetchEquipmentEvent())
        setUpEquipmentRecyclerView()
        observeEquipmentState()
        filterByName()
    }

    private fun filterByName() {
        binding.etSearch.addTextChangedListener { text ->
            debounceJob?.cancel()

            debounceJob = CoroutineScope(Dispatchers.Main).launch {
                delay(500)
                equipmentViewModel.onEvent(EquipmentEvent.FetchEquipmentEvent(text.toString()))
            }
        }
    }


    private fun observeEquipmentState() {
        lifecycleCollectLatest(equipmentViewModel.equipmentState) { state ->
            loading(state.isLoading)
            state.success.let {
                equipmentAdapter.submitList(it)
            }
            state.error?.let { binding.root.showErrorSnackBar(it) }

        }
    }


    private fun setUpEquipmentRecyclerView() {
        binding.recyclerViewEquip.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = equipmentAdapter
        }
    }

    private fun loading(isLoading: Boolean) {
        binding.loading.isVisible = isLoading
        binding.loading.isClickable = isLoading

    }
}