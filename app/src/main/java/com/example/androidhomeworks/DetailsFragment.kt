package com.example.androidhomeworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidhomeworks.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBack()
        displayDetails()
        changeStatus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun goBack() {
        binding.ivBackIcon.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }

    private fun displayDetails() {
        val getOrder =
            OrderList.orderList.find { it.id == requireArguments().getSerializable("Id") }
        binding.tvDisplayOrderName.text = getOrder!!.orderName
        binding.tvDisplayOrderDate.text =
            HelperFunctions.convertMillisecondsToDate(getOrder.orderDate)
        binding.tvDisplayOrderTrackingNumber.text = getOrder.trackingNum
        binding.tvDisplayQuantity.text = getOrder.quantity.toString()
        binding.tvDisplayOrderSubtotal.text =
            getString(R.string.subtotal_display, getOrder.subTotal)
        binding.tvDisplayStatus.text = getOrder.status
        binding.tvDisplayOrderTotal.text =
            getString(R.string.calculate_total, getOrder.subTotal * getOrder.quantity)
    }

    private fun changeStatus() {
        val getOrder =
            OrderList.orderList.find { it.id == requireArguments().getSerializable("Id") }
        if (getOrder!!.status == "PENDING") {
            binding.btnDelivered.visibility = View.VISIBLE
            binding.btnCancelled.visibility = View.VISIBLE
        }
        binding.btnDelivered.setOnClickListener {
            getOrder.apply {
                status = "DELIVERED"
                parentFragmentManager.popBackStack()
            }
        }

        binding.btnCancelled.setOnClickListener {
            getOrder.apply {
                status = "CANCELLED"
                parentFragmentManager.popBackStack()
            }
        }
    }
}