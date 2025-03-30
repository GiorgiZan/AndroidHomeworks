package com.example.androidhomeworks.presentation.screens.transfer

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.FragmentTransferBinding
import com.example.androidhomeworks.domain.common.CardType
import com.example.androidhomeworks.presentation.base_framgent.BaseFragment
import com.example.androidhomeworks.presentation.extension.lifecyclescope.lifecycleCollectLatest
import com.example.androidhomeworks.presentation.extension.lifecyclescope.showErrorSnackBar
import com.example.androidhomeworks.presentation.model.UiAccount
import com.example.androidhomeworks.presentation.screens.select_bottom.SelectAccountBottomSheetFragment
import com.example.androidhomeworks.presentation.screens.tranfer_to_bottom.TransferToBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TransferFragment : BaseFragment<FragmentTransferBinding>(FragmentTransferBinding::inflate) {
    private val transferViewModel: TransferViewModel by viewModels()

    override fun setUp() {
        transferViewModel.onEvent(TransferEvent.FetchAccountEvent)
        binding.tvFromContainer
        observeTransferState()
        observeUiEffects()

        binding.tvToAmount.addTextChangedListener {
            course()
        }
        binding.tvFromAmount.addTextChangedListener {
            course()
        }

    }

    private fun observeTransferState() {
        lifecycleCollectLatest(transferViewModel.transferUiState) { state ->
            loading(state.isLoading)
            state.accountsList?.let { account ->
                selectFromAccount(account)
            }
            state.validationResult.run {
                selectToAccount()
            }
            state.course?.let { courseRate ->
                calculateExchange(courseRate)
            }
        }
    }

    private fun observeUiEffects() {
        lifecycleCollectLatest(transferViewModel.uiEffect) { effect ->
            when (effect) {
                is TransferUiEffect.Failure -> {
                    binding.root.showErrorSnackBar(effect.error)
                }
            }
        }
    }

    private fun selectFromAccount(account: List<UiAccount>) {
        binding.tvFromContainer.setOnClickListener {
            SelectAccountBottomSheetFragment(account) { selectedAccount ->
                with(binding) {
                    tvFromIban.text = "****".plus(selectedAccount.accountNumber.takeLast(4))
                    ivFromCardIcon.setImageResource(
                        when (selectedAccount.cardType) {
                            CardType.VISA -> R.drawable.visa
                            CardType.MASTER_CARD -> R.drawable.mastercard
                        }
                    )
                    tvFromAmount.text = selectedAccount.balance.toString().plus(" ")
                        .plus(selectedAccount.valuteType)
                }

            }.show(parentFragmentManager, getString(R.string.selectaccountbottomsheetTag))
        }


    }

    private fun calculateExchange(courseRate:Double){
        binding.tvAmount.addTextChangedListener {
            val amountText = binding.tvAmount.text.toString()
            val amount = amountText.toIntOrNull() ?: 0
            binding.tvConvertedAmount.text = (amount * courseRate).toString()
        }
        binding.tvAmount.text?.clear()
    }

    private fun selectToAccount() {
        binding.tvToContainer.setOnClickListener {
            val transferBottomSheetFragment =
                TransferToBottomSheetFragment { transferType, enteredValue ->
                    transferViewModel.onEvent(TransferEvent.ValidateAccountNumber(enteredValue))
                    binding.tvToIBAN.text = "****".plus(enteredValue.takeLast(4))
                    binding.tvToAmount.text = getString(R.string._400, transferType)
                }
            transferBottomSheetFragment.show(
                parentFragmentManager,
                getString(R.string.transfertobottomsheetTag)
            )
        }
    }

    private fun course() {
        val currencyFrom = binding.tvFromAmount.text.toString().split(" ").last()
        val currencyTo = binding.tvToAmount.text.toString().split(" ").last()
        if (currencyFrom == currencyTo) {
            hideConvertedBox(false)

        } else {
            if (currencyTo.isNotEmpty() && currencyFrom.isNotEmpty()) {
                hideConvertedBox(true)
                transferViewModel.onEvent(TransferEvent.GetCourse(currencyFrom, currencyTo))
            }
        }

    }

    private fun loading(isLoading: Boolean) {
        binding.loading.isVisible = isLoading
        binding.loading.isClickable = isLoading

    }

    private fun hideConvertedBox(boolean: Boolean) {
        binding.tvConvertedSell.isVisible = boolean
        binding.tvConvertedSellContainer.isVisible = boolean
        binding.tvConvertedAmount.isVisible = boolean
    }

}
