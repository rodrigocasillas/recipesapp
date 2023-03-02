package com.example.viapath.recipeslist.presentation.recipeslistactivity.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.viapath.R
import com.example.viapath.recipeslist.presentation.recipeslistactivity.models.Recipe
import com.example.viapath.recipeslist.presentation.recipeslistactivity.models.RecipeResponse

class RecipesAdapter: RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {

    interface OnClick {
        fun onRecipeClicked(
            id: String,
            title: String
        )
    }

    lateinit var onClick: OnClick
    private var recipesList = emptyList<Recipe>()
    private lateinit var baseUrl: String
    fun setData(recipeResponse: RecipeResponse) {
        recipesList = recipeResponse.recipes
        baseUrl = recipeResponse.baseUri
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bindData(recipesList[position])
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }

    inner class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindData(recipe: Recipe) {
            val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
            val recipeImageView = itemView.findViewById<ImageView>(R.id.recipeImageView)
            val containerLayout = itemView.findViewById<ConstraintLayout>(R.id.containerLayout)

            titleTextView.text = recipe.title
            recipeImageView.load(baseUrl + recipe.image) {
                transformations(CircleCropTransformation())
            }
            containerLayout.setOnClickListener {
                onClick.onRecipeClicked(recipe.id.toString(), recipe.title)
            }
        }
    }
}