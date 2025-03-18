package com.example.androidhomeworks.presentation.extension.lifecyclescope

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest

fun <T> Fragment.lifecycleCollectLatest(flow: Flow<T>, action: suspend (value: T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest { value ->
                action(value)
            }
        }
    }
}
