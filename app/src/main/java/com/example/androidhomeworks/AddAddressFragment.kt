package com.example.androidhomeworks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.androidhomeworks.databinding.FragmentAddAddressBinding
import com.google.android.material.snackbar.Snackbar

class AddAddressFragment : Fragment() {
    private var binding: FragmentAddAddressBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAddressBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDropDown()
        editMode()
        clickAdd()
        clickEdit()

    }

    private fun setDropDown(){
        val locations = resources.getStringArray(R.array.address_locations)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, locations)
        val autocompleteTV = binding?.autoCompleteTextView
        autocompleteTV?.setAdapter(arrayAdapter)
    }

    private fun clickAdd(){
        binding?.btnAdd?.setOnClickListener{
            if (!validateEmptyFields()){
                return@setOnClickListener
            }
            if (!addAddressValidation()){
                return@setOnClickListener
            }
            ListOfAddresses.addresses.add(
                0,
                AddressDetails(
                    id = binding?.etEnterId?.text.toString().toInt(),
                    name = binding?.etEnterName?.text.toString(),
                    address = binding?.etEnterAddress?.text.toString(),
                    icon = locationToValue()
                )
            )
            parentFragmentManager.popBackStack()

        }
    }

    private fun clickEdit(){
        binding?.btnEdit?.setOnClickListener{
            if (!validateEmptyFields()){
                return@setOnClickListener
            }
            val user = ListOfAddresses.addresses.find { it.id == binding?.etEnterId?.text.toString().toInt() }

            user?.apply {
                name = binding?.etEnterName?.text.toString()
                address =binding?.etEnterAddress?.text.toString()
                icon = locationToValue()
            }
            parentFragmentManager.popBackStack()
        }

    }

    private fun editMode(){
        if (arguments != null){
            binding?.etEnterId?.setText(arguments?.getInt("Id").toString())
            binding?.etEnterName?.setText(arguments?.getString("Name"))
            binding?.etEnterAddress?.setText(arguments?.getString("Address"))
            binding?.autoCompleteTextView?.setText(arguments?.getString("Icon"))
            binding?.etEnterId?.isFocusable = false
            binding?.btnAdd?.visibility = View.GONE
            binding?.btnEdit?.visibility = View.VISIBLE
            setDropDown()
        }
    }

    private fun validateEmptyFields(): Boolean {
        if (binding?.etEnterId?.text.toString().isEmpty()){
            binding?.etEnterId?.error = getString(R.string.id_empty)
            return false
        }

        if (binding?.etEnterName?.text.toString().isEmpty()){
            binding?.etEnterName?.error = getString(R.string.name_empty)
            return false
        }

        if (binding?.etEnterAddress?.text.toString().isEmpty()){
            binding?.etEnterAddress?.error = getString(R.string.address_empty)
            return false
        }

        if (binding?.autoCompleteTextView?.text.toString() == getString(R.string.choose_address_location)){
            Snackbar.make(binding!!.root, getString(R.string.select_location), Snackbar.LENGTH_SHORT).show()
            return false
        }


        return true
    }

    private fun addAddressValidation(): Boolean {
        if (ListOfAddresses.addresses.any {
                it.id == binding?.etEnterId?.text.toString().toInt()
            }) {
            binding?.etEnterId?.error = getString(R.string.address_already_exists)
            return false
        }
        return true
    }

    private fun locationToValue(): Int {
        if (binding?.autoCompleteTextView?.text.toString() == getString(R.string.house)){
            return R.drawable.house_icon
        }
        if (binding?.autoCompleteTextView?.text.toString() == getString(R.string.office)){
            return R.drawable.office_icon
        }
        if (binding?.autoCompleteTextView?.text.toString() == getString(R.string.apartment)){
            return R.drawable.apartment
        }
        return 0

    }

}