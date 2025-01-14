package com.example.androidhomeworks.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhomeworks.R
import com.example.androidhomeworks.databinding.CardRecyclerViewBinding
import java.util.UUID


class CardDiffUtils : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }


}

class CardAdapter(val bottomSheet:(id:UUID) -> Unit) : ListAdapter<Card, CardAdapter.CardViewHolder>(CardDiffUtils()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            CardRecyclerViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.onBind()
    }


    inner class CardViewHolder(private val binding: CardRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val card = getItem(adapterPosition)
            val formattedCardNo = card.cardNo.toString().chunked(4).joinToString("   ")
            binding.tvHolderName.text = card.cardHolderName
            binding.tvCardNumbers.text = formattedCardNo
            binding.tvCardExpirationDate.text = card.cardValidThru
            if (card.cardType == CardType.VISA){
                binding.card.background = ContextCompat.getDrawable(binding.card.context,
                    R.drawable.visa
                )
            }
            else if (card.cardType == CardType.MASTERCARD){
                binding.card.background = ContextCompat.getDrawable(binding.card.context,
                    R.drawable.mastercard
                )
            }

            binding.card.setOnLongClickListener{
                bottomSheet.invoke(card.id)
                true
            }
        }
    }


}