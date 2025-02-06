package com.example.androidhomeworks.presentation.passcode

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PasscodeViewModel : ViewModel() {
    private val _enteredPasscode = MutableStateFlow("")
    val enteredPasscode: StateFlow<String> get() = _enteredPasscode

    companion object {
        private const val CORRECT_PASSCODE = "0934"
    }

    fun addNum(num: String) {
        if (_enteredPasscode.value.length < 4) {
            _enteredPasscode.value += num
        }
    }

    fun removeLastNum() {
        _enteredPasscode.value = _enteredPasscode.value.dropLast(1)
    }

    fun checkPasscode(): Boolean {
        return _enteredPasscode.value == CORRECT_PASSCODE
    }

    fun resetPasscode() {
        _enteredPasscode.value = ""
    }
}