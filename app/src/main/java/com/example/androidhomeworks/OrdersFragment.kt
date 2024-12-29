package com.example.androidhomeworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidhomeworks.databinding.FragmentOrdersBinding


class OrdersFragment : Fragment() {
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!
    private val orderAdapter by lazy {
        OrderAdapter() { args ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, DetailsFragment().apply { arguments = args }, "DetailsFragment")
                .addToBackStack("DetailsFragment").commit()
        }
    }
    private val orderStatusAdapter by lazy {
        OrderStatusAdapter() { selectedStatus ->
            orderAdapter.filterByStatus(selectedStatus)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrdersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.orderRecyclerView.adapter = orderAdapter
        orderAdapter.submitList(OrderList.orderList)
        binding.statusRecyclerView.adapter = orderStatusAdapter
        orderStatusAdapter.submitList(OrderStatusList.statusList)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}