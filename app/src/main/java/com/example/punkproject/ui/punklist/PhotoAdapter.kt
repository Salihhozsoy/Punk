package com.example.punkproject.ui.punklist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.punkproject.data.model.ResponseItem
import com.example.punkproject.databinding.PunkListItemBinding

class PhotoAdapter(
    private val context: Context,
    private var items: List<ResponseItem>,
    val onClick: (responseItem:ResponseItem) -> Unit
) : RecyclerView.Adapter<PhotoAdapter.CustomViewHolder>() {

    class CustomViewHolder(binding: PunkListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivPunkImage = binding.ivPunkImage

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = PunkListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val items = items[position]
        holder.ivPunkImage.load(items.image_url)
        holder.itemView.setOnClickListener {
            onClick(items)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}