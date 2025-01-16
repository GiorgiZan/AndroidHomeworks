package com.example.androidhomeworks.field

import android.app.DatePickerDialog
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomeworks.databinding.EditTextRecyclerViewBinding
import com.example.androidhomeworks.databinding.SpinnerRecyclerViewBinding
import com.squareup.picasso.Picasso
import java.util.Calendar


class FieldAdapter :
    ListAdapter<Field, RecyclerView.ViewHolder>(FieldDiffUtil()) {
    companion object {
        const val EDIT_TEXT = 1
        const val SPINNER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == EDIT_TEXT) {
            return EdiTextViewHolder(
                EditTextRecyclerViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return SpinnerViewHolder(
                SpinnerRecyclerViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EdiTextViewHolder) {
            holder.onBind()
        } else if (holder is SpinnerViewHolder) {
            holder.onBind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].fieldType == "input") EDIT_TEXT else SPINNER
    }

    inner class EdiTextViewHolder(private val binding: EditTextRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val field = getItem(adapterPosition)
            binding.et.hint = field.hint
            binding.et.inputType =
                if (field.keyboard == "number") InputType.TYPE_CLASS_NUMBER else InputType.TYPE_CLASS_TEXT

            Picasso.get()
                .load(field.icon)
                .into(binding.iv)
        }
    }

    inner class SpinnerViewHolder(private val binding: SpinnerRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val field = getItem(adapterPosition)

            if (field.hint == "Birthday") {
                binding.spinner.visibility = View.GONE
                binding.datePickerButton.visibility = View.VISIBLE

                binding.datePickerButton.setOnClickListener {
                    val calendar = Calendar.getInstance()
                    val year = calendar.get(Calendar.YEAR)
                    val month = calendar.get(Calendar.MONTH)
                    val day = calendar.get(Calendar.DAY_OF_MONTH)

                    val datePickerDialog = DatePickerDialog(
                        binding.root.context,
                        { _, selectedYear, selectedMonth, selectedDay ->
                            binding.datePickerButton.text =
                                "$selectedDay/${selectedMonth + 1}/$selectedYear"
                        },
                        year,
                        month,
                        day
                    )
                    datePickerDialog.show()
                }
            } else {
                binding.spinner.visibility = View.VISIBLE
                binding.datePickerButton.visibility = View.GONE

                binding.spinner.prompt = field.hint
                val spinnerAdapter = ArrayAdapter(
                    binding.root.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    listOf("Select Gender", "Male", "Female")
                )
                binding.spinner.adapter = spinnerAdapter
            }
        }
    }


}

class FieldDiffUtil : DiffUtil.ItemCallback<Field>() {
    override fun areItemsTheSame(oldItem: Field, newItem: Field): Boolean {
        return oldItem.fieldId == newItem.fieldId
    }

    override fun areContentsTheSame(oldItem: Field, newItem: Field): Boolean {
        return oldItem == newItem
    }
}