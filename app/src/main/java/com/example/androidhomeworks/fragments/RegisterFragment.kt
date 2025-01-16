package com.example.androidhomeworks.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomeworks.box.BoxAdapter
import com.example.androidhomeworks.databinding.FragmentRegisterBinding
import com.example.androidhomeworks.field.FieldsViewModel

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val fieldsViewModel: FieldsViewModel by viewModels()
    override fun setUp() {
        binding.containerRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
        }
        fieldsViewModel.fieldContainers.observe(this) { containers ->
            binding.containerRecyclerView.adapter = BoxAdapter(containers)
        }
    }



}