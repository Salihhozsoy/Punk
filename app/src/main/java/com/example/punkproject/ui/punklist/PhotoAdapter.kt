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
    val onClick: (responseItem: ResponseItem) -> Unit,
    val onRemove: (responseItem: ResponseItem) -> Unit
) : RecyclerView.Adapter<PhotoAdapter.CustomViewHolder>() {

    class CustomViewHolder(binding: PunkListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivPunkImage = binding.ivPunkImage
        val tvPunkName = binding.tvPunkName
        val etPunkDescription = binding.etPunkDescription
        val ivDelete =binding.ivDelete

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = PunkListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val items = items[position]
        holder.ivPunkImage.load(items.image_url)
        holder.tvPunkName.text = items.name
        holder.etPunkDescription.text = items.description

        holder.itemView.setOnClickListener {
            onClick(items)
        }
        holder.ivDelete.setOnClickListener {
            onRemove(items)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}