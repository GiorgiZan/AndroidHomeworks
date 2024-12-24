package com.example.androidhomeworks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomeworks.databinding.ClothesRecycleViewBinding

class ClothesAdapter(val clothes: MutableList<RecyclerClothe>) :
    RecyclerView.Adapter<ClothesAdapter.ClothesViewHolder>() {

    inner class ClothesViewHolder(val binding: ClothesRecycleViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBindView() {
            val model: RecyclerClothe = clothes[adapterPosition]
            binding.ivClothe.setImageResource(model.image)
            binding.tvClotheTitle.text = model.title
            binding.tvClotheTitle.text = model.title
            binding.tvPrice.text =
                binding.root.context.getString(R.string.price_of_clothe, model.price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesViewHolder {
        return ClothesViewHolder(
            ClothesRecycleViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return clothes.size
    }

    override fun onBindViewHolder(holder: ClothesViewHolder, position: Int) {
        holder.onBindView()

        with(holder.binding) {
            btnHeart.setOnClickListener {
                if (!btnHeart.isActivated) {
                    btnHeart.imageTintList =
                        ContextCompat.getColorStateList(root.context, R.color.red)
                } else {
                    btnHeart.imageTintList =
                        ContextCompat.getColorStateList(root.context, R.color.gray)
                }
                btnHeart.isActivated = !btnHeart.isActivated
            }
        }
    }
}