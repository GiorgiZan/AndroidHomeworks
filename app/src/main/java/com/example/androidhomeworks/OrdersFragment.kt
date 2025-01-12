package com.example.androidhomeworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomeworks.databinding.FragmentOrdersBinding
import java.util.UUID


class OrdersFragment : Fragment() {
    private var _binding: FragmentOrdersBinding? = null
    private val binding get() = _binding!!
    private val orderAdapter by lazy {
        OrderAdapter(reviewClick = {
            navigateToReview(it)
        })
    }

    private fun navigateToReview(order: UUID) {
        findNavController().navigate(
            OrdersFragmentDirections.actionOrdersFragmentToMyBottomFragment(
                orderId = order
            )
        )

    }

    companion object {
        val orders: MutableList<Order> = mutableListOf(
            Order(
                UUID.randomUUID(),
                orderImg = R.drawable.chair,
                orderTitle = "Super brown chair",
                orderColor = R.drawable.brown_circle_background,
                orderColorInText = "Brown",
                orderQuantity = 2,
                orderStatus = StatusEnum.ACTIVE,
                orderPrice = 280.00,
                orderAction = "Track Order",
            ),
            Order(
                UUID.randomUUID(),
                orderImg = R.drawable.brown_kings_throne,
                orderTitle = "Brown kings throne",
                orderColor = R.drawable.brown_circle_background,
                orderColorInText = "Brown",
                orderQuantity = 1,
                orderStatus = StatusEnum.COMPLETED,
                orderPrice = 6110.01,
                orderAction = "Leave a review"
            ),
            Order(
                UUID.randomUUID(),
                orderImg = R.drawable.black_chair,
                orderTitle = "Black chair",
                orderColor = R.drawable.black_circle_background,
                orderColorInText = "Black",
                orderQuantity = 5,
                orderStatus = StatusEnum.ACTIVE,
                orderPrice = 180.00,
                orderAction = "Track Order"
            ),
            Order(
                UUID.randomUUID(),
                orderImg = R.drawable.black_table,
                orderTitle = "Black Table",
                orderColor = R.drawable.black_circle_background,
                orderColorInText = "Blac",
                orderQuantity = 2,
                orderStatus = StatusEnum.COMPLETED,
                orderPrice = 150.00,
                orderAction = "Leave a review"
            ),
            Order(
                UUID.randomUUID(),
                orderImg = R.drawable.chair,
                orderTitle = "Super brown chair",
                orderColor = R.drawable.brown_circle_background,
                orderColorInText = "Brown",
                orderQuantity = 2,
                orderStatus = StatusEnum.ACTIVE,
                orderPrice = 280.00,
                orderAction = "Track Order"
            ),
            Order(
                UUID.randomUUID(),
                orderImg = R.drawable.black_table,
                orderTitle = "Black Table",
                orderColor = R.drawable.black_circle_background,
                orderColorInText = "Blac",
                orderQuantity = 2,
                orderStatus = StatusEnum.COMPLETED,
                orderPrice = 150.00,
                orderAction = "Leave a review"
            ),
            Order(
                UUID.randomUUID(),
                orderImg = R.drawable.chair,
                orderTitle = "Super brown chair",
                orderColor = R.drawable.brown_circle_background,
                orderColorInText = "Brown",
                orderQuantity = 2,
                orderStatus = StatusEnum.ACTIVE,
                orderPrice = 280.00,
                orderAction = "Track Order"
            ),
            Order(
                UUID.randomUUID(),
                orderImg = R.drawable.black_table,
                orderTitle = "Black Table",
                orderColor = R.drawable.black_circle_background,
                orderColorInText = "Blac",
                orderQuantity = 2,
                orderStatus = StatusEnum.COMPLETED,
                orderPrice = 150.00,
                orderAction = "Leave a review"
            ),
            Order(
                UUID.randomUUID(),
                orderImg = R.drawable.chair,
                orderTitle = "Super brown chair",
                orderColor = R.drawable.brown_circle_background,
                orderColorInText = "Brown",
                orderQuantity = 2,
                orderStatus = StatusEnum.ACTIVE,
                orderPrice = 280.00,
                orderAction = "Track Order"
            ),
            Order(
                UUID.randomUUID(),
                orderImg = R.drawable.black_table,
                orderTitle = "Black Table",
                orderColor = R.drawable.black_circle_background,
                orderColorInText = "Blac",
                orderQuantity = 2,
                orderStatus = StatusEnum.COMPLETED,
                orderPrice = 150.00,
                orderAction = "Leave a review"
            ),
            Order(
                UUID.randomUUID(),
                orderImg = R.drawable.chair,
                orderTitle = "Super brown chair",
                orderColor = R.drawable.brown_circle_background,
                orderColorInText = "Brown",
                orderQuantity = 2,
                orderStatus = StatusEnum.ACTIVE,
                orderPrice = 280.00,
                orderAction = "Track Order"
            )
        )
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
        setUp()


    }


    private fun setUp() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = orderAdapter
        val activeOrders = orders.filter { it.orderStatus == StatusEnum.ACTIVE }
        orderAdapter.submitList(activeOrders)

        swipeStatus()


    }


    private fun swipeStatus() {
        val touchListener = object : View.OnTouchListener {
            var initialX = 0f

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        initialX = event.x
                    }

                    MotionEvent.ACTION_UP -> {
                        val deltaX = event.x - initialX
                        if (deltaX < 0) {
                            binding.tvCompleted.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.white
                                )
                            )
                            binding.tvActive.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.status_color
                                )
                            )
                            binding.underlineActive.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.status_color
                                )
                            )
                            binding.underlineCompleted.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.white
                                )
                            )

                            val completedOrders =
                                orders.filter { it.orderStatus == StatusEnum.COMPLETED }
                            orderAdapter.submitList(completedOrders)
                        }

                        if (deltaX > 0) {
                            binding.tvActive.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.white
                                )
                            )
                            binding.tvCompleted.setTextColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.status_color
                                )
                            )
                            binding.underlineActive.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.white
                                )
                            )
                            binding.underlineCompleted.setBackgroundColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.status_color
                                )
                            )

                            val activeOrders = orders.filter { it.orderStatus == StatusEnum.ACTIVE }
                            orderAdapter.submitList(activeOrders)
                        }

                        v?.performClick()
                    }
                }
                return true
            }
        }

        binding.tvActive.setOnTouchListener(touchListener)
        binding.tvCompleted.setOnTouchListener(touchListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}