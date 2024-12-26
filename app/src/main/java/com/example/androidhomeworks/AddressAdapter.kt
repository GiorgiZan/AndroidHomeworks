package com.example.androidhomeworks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomeworks.databinding.AddressRecyclerViewBinding

class AddressDiffUtil : DiffUtil.ItemCallback<AddressDetails>() {
    override fun areItemsTheSame(oldItem: AddressDetails, newItem: AddressDetails): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AddressDetails, newItem: AddressDetails): Boolean {
        return oldItem == newItem
    }

}


class AddressAdapter(private val fragment: FragmentManager) :
    ListAdapter<AddressDetails, AddressAdapter.AddressViewHolder>(AddressDiffUtil()) {
    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder(
            AddressRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val addressDetails = getItem(position)
        holder.onBind(addressDetails)
        holder.binding.radioBtnAddress.isChecked = selectedPosition == holder.adapterPosition



        with(holder.binding) {
            tvAddressContainer.setOnLongClickListener {
                ListOfAddresses.addresses.removeAt(holder.adapterPosition)
                notifyItemRemoved(holder.adapterPosition)
                true
            }
            radioBtnAddress.setOnClickListener {
                val previousSelectedPosition = selectedPosition
                if (selectedPosition == holder.adapterPosition) {
                    selectedPosition = -1
                } else {
                    selectedPosition = holder.adapterPosition
                }


                notifyItemChanged(previousSelectedPosition)
                notifyItemChanged(selectedPosition)
            }

            tvEdit.setOnClickListener {
                if (radioBtnAddress.isChecked) {
                    val bundle = Bundle().apply {
                        putInt("Id", ListOfAddresses.addresses[holder.adapterPosition].id)
                        putString("Name", ListOfAddresses.addresses[holder.adapterPosition].name)
                        putString(
                            "Address",
                            ListOfAddresses.addresses[holder.adapterPosition].address
                        )
                        putString(
                            "Icon",
                            iconToText(ListOfAddresses.addresses[holder.adapterPosition].icon)
                        )
                    }

                    val addAddressFragment = AddAddressFragment().apply {
                        arguments = bundle
                    }
                    fragment.beginTransaction()
                        .replace(R.id.main, addAddressFragment, "AddAddressFragment")
                        .addToBackStack("AddAddressFragment")
                        .commit()
                }
            }
        }


    }


    inner class AddressViewHolder(val binding: AddressRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(addressDetails: AddressDetails) {
            binding.ivAddressIcon.setImageResource(addressDetails.icon)
            binding.tvAddressName.text = addressDetails.name
            binding.tvAddress.text = addressDetails.address
        }
    }

    private fun iconToText(icon: Int): String {
        if (icon == R.drawable.office_icon) {
            return "Office"
        }
        if (icon == R.drawable.house_icon) {
            return "House"
        }
        if (icon == R.drawable.apartment) {
            return "Apartment"
        }
        return ""
    }
}