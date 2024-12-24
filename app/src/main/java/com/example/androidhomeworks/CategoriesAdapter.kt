package com.example.androidhomeworks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomeworks.databinding.CategoriesRecyclerViewBinding

class CategoriesAdapter(
    val categories: MutableList<RecyclerCategory>,
    val categorySelected: (String) -> Unit
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {
    private var selectedPosition = 0

    inner class CategoriesViewHolder(val binding: CategoriesRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBindView() {
            val model: RecyclerCategory = categories[adapterPosition]
            binding.btnCategories.text = model.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(
            CategoriesRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.onBindView()

        with(holder.binding) {

            if (position == selectedPosition) {
                btnCategories.backgroundTintList = ContextCompat.getColorStateList(
                    root.context,
                    R.color.light_green
                )
                btnCategories.setTextColor(ContextCompat.getColor(root.context, R.color.white))
            } else {
                btnCategories.backgroundTintList = ContextCompat.getColorStateList(
                    root.context,
                    R.color.dark_green
                )
                btnCategories.setTextColor(ContextCompat.getColor(root.context, R.color.gray))
            }


            btnCategories.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = holder.adapterPosition
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)

                categorySelected(categories[selectedPosition].text)
            }
        }
    }
}