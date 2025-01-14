package com.example.androidhomeworks.fragments

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.androidhomeworks.R
import com.example.androidhomeworks.cards.Card
import com.example.androidhomeworks.cards.CardType
import com.example.androidhomeworks.cards.CardViewModel
import com.example.androidhomeworks.databinding.FragmentAddNewCardBinding
import java.util.UUID


class AddNewCardFragment : BaseFragment<FragmentAddNewCardBinding>(FragmentAddNewCardBinding::inflate) {
    private val cardViewModel: CardViewModel by activityViewModels()


    override fun setUp() {
        realTimeChange()
    }

    override fun listeners() {
        radioButtons()
        binding.btnAddCard.setOnClickListener{
            if (!validation()){
                return@setOnClickListener
            }
            addNewCard()
            findNavController().navigate(AddNewCardFragmentDirections.actionAddNewCardFragmentToPaymentFragment())
        }

        binding.ivBackIcon.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun validation():Boolean{
        if (binding.etCardName.text.toString().isEmpty()){
            binding.etCardName.error = getString(R.string.card_name_should_not_be_empty)
            return false
        }
        if (binding.etCardNumber.text.toString().isEmpty()){
            binding.etCardNumber.error = getString(R.string.card_number_should_not_be_empty)
            return false
        }
        if (binding.etCardNumber.text.toString().length != 16){
            binding.etCardNumber.error = getString(R.string.card_number_should_contain_16_numbers)
            return false
        }
        if (binding.etCardExpires.text.toString().isEmpty()){
            binding.etCardExpires.error = getString(R.string.expiration_date_should_not_be_empty)
            return false
        }
        if (binding.etCardCvv.text.toString().isEmpty()){
            binding.etCardCvv.error = getString(R.string.card_cvv_should_not_be_empty)
            return false
        }

        return true
    }

    private fun radioButtons(){
        binding.radioMasterCard.setOnClickListener{
            binding.card.setBackgroundResource(R.drawable.mastercard)
        }
        binding.radioVisa.setOnClickListener{
            binding.card.setBackgroundResource(R.drawable.visa)
        }
    }

    private fun addNewCard(){
        var newCardType: CardType = CardType.MASTERCARD
        if (binding.radioMasterCard.isChecked){
            newCardType = CardType.MASTERCARD
        }
        else if (binding.radioVisa.isChecked){
            newCardType = CardType.VISA
        }
        val card = Card(
            id = UUID.randomUUID(),
            cardHolderName = binding.etCardName.text.toString(),
            cardNo = binding.etCardNumber.text.toString().toLong(),
            cardValidThru = binding.etCardExpires.text.toString(),
            cardCvv = binding.etCardCvv.text.toString().toInt(),
            cardType = newCardType
        )
        cardViewModel.addCard(card)

    }

    private fun realTimeChange(){
        binding.etCardNumber.addTextChangedListener{
            val formattedCardNo = it.toString().chunked(4).joinToString("   ")
            binding.tvCardNumbers.text = formattedCardNo
        }
        binding.etCardName.addTextChangedListener {
            binding.tvCardHolderNameOnCard.text = it.toString()
        }
        binding.etCardExpires.addTextChangedListener {
            binding.tvCardExpirationDateOnCard.text = it.toString()
        }
    }


}