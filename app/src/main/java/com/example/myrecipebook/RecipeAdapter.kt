package com.example.myrecipebook

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipebook.databinding.RecyclerRowBinding

class RecipeAdapter(val recipeList: ArrayList<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeHolder>() {
    class RecipeHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.binding.recyclerViewTextView.text = recipeList.get(position).name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, RecipeActivity::class.java)
            intent.putExtra("info", "old")
            intent.putExtra("id", recipeList.get(position).id )
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
}