package com.example.androidhomeworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidhomeworks.databinding.FragmentDeliveryAddressBinding

class DeliveryAddressFragment : Fragment() {
    private var binding: FragmentDeliveryAddressBinding? = null
    private val addressAdapter by lazy {
        AddressAdapter(parentFragmentManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeliveryAddressBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNewAddress()
        binding?.categoriesRecyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.categoriesRecyclerView?.adapter = addressAdapter
        addressAdapter.submitList(ListOfAddresses.addresses)


    }

    private fun addNewAddress() {
        binding?.btnAddNewAddress?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main, AddAddressFragment(), "AddAddressFragment")
                .addToBackStack("AddAddressFragment")
                .commit()
        }
    }


}