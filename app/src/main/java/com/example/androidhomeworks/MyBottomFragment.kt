package com.example.androidhomeworks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidhomeworks.databinding.MyBottomFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Locale

class MyBottomFragment() : BottomSheetDialogFragment() {
    private var _binding: MyBottomFragmentBinding? = null
    private val binding get() = _binding!!
    private val args: MyBottomFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MyBottomFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        listeners()

    }


    private fun setUp() {

        val order = OrdersFragment.orders.find { it.id == args.orderId }!!
        with(binding) {
            ivOrderImg.setImageResource(order.orderImg)
            tvOrderTitle.text = order.orderTitle
            tvOrderColor.text = order.orderColorInText
            colorCircle.setBackgroundResource(order.orderColor)
            tvOrderQuantity.text = getString(R.string.qty, order.orderQuantity)
            tvOrderStatus.text = order.orderStatus.value
            tvOrderPrice.text =
                getString(
                    R.string.order_price,
                    String.format(Locale.CHINA, "%.2f", order.orderPrice)
                )
            tvActionButton.text = order.orderAction
        }
    }

    private fun listeners() {
        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSubmit.setOnClickListener {
            if (binding.etReview.text.toString().isEmpty()) {
                binding.etReview.error = getString(R.string.review_should_not_be_empty)
                return@setOnClickListener
            }
            if (binding.ratingBar.rating == 0f) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.provide_a_rating), Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            val order = OrdersFragment.orders.find { it.id == args.orderId }
            order?.orderReview = binding.etReview.text.toString()
            order?.orderAction = getString(R.string.buy_again)

            Toast.makeText(
                requireContext(),
                getString(R.string.thank_you_for_rating_order), Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(MyBottomFragmentDirections.actionMyBottomFragmentToOrdersFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}