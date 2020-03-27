package com.bakayapps.coctailsbook.adapter

import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bakayapps.coctailsbook.R
import com.bakayapps.coctailsbook.data.ShortItemCoctail
import com.bakayapps.coctailsbook.models.RecipeModelForRV
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipesAdapter(val newRecipeForRVS: List<ShortItemCoctail>, private val itemClickListener: (Int) -> Unit) : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {
    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        //holder.picRecipe.setImageDrawable(newRecipeForRVS[position].pic)
        Glide.with(holder.itemView.context)
            .load(newRecipeForRVS[position].thumbnail)
            .into(holder.picRecipe)
        holder.nameResipe.text = newRecipeForRVS[position].nameCoctail
//        val typeface
//        holder.nameResipe.typeface = typeface
    }

    override fun getItemCount() = newRecipeForRVS.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder{
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipesViewHolder(view)
    }

    inner class RecipesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                Log.d("logi", "Click!") // WORKS!!!
                itemClickListener(adapterPosition)
            }
        }

        val picRecipe: ImageView = itemView.ivRecipe
        val nameResipe: TextView = itemView.tvNameRecipe
        
    }


}
