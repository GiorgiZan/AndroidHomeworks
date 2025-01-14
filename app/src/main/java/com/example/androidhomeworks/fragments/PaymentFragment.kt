package com.example.androidhomeworks.fragments

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.androidhomeworks.cards.CardAdapter
import com.example.androidhomeworks.cards.CardViewModel
import com.example.androidhomeworks.databinding.FragmentPaymentBinding
import java.util.UUID


class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {

    private val cardAdapter by lazy {
        CardAdapter {
            openBottomSheet(it)
        }
    }
    private val cardViewModel: CardViewModel by activityViewModels()


    private fun openBottomSheet(id: UUID) {
        findNavController().navigate(
            PaymentFragmentDirections.actionPaymentFragmentToBottomConfirmationSheet(
                id
            )
        )
    }

    override fun setUp() {
        binding.viewPagerCards.adapter = cardAdapter
        binding.viewPagerCards.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        cardViewModel.cards.observe(viewLifecycleOwner) { cards ->
            cardAdapter.submitList(cards)
        }


    }

    override fun listeners() {
        binding.tvAddNew.setOnClickListener {
            findNavController().navigate(PaymentFragmentDirections.actionPaymentFragmentToAddNewCardFragment())
        }

        binding.ivBackIcon.setOnClickListener {
            requireActivity().finish()
        }
    }


}