package com.example.viapath.recipedetail.presentation.recipesdetailactivity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.viapath.databinding.FragmentIngredientsBinding
import com.example.viapath.recipedetail.domain.util.OperationResult
import com.example.viapath.recipedetail.presentation.recipesdetailactivity.models.RecipeDetail
import com.example.viapath.recipedetail.presentation.recipesdetailactivity.viewmodel.RecipeDetailsViewModel
import com.google.android.material.snackbar.Snackbar


class IngredientsFragment : Fragment() {

    private lateinit var viewBinding: FragmentIngredientsBinding
    private val recipeDetailsViewModel: RecipeDetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        recipeDetailsViewModel.recipeDetailLiveData.observe(viewLifecycleOwner) { operationResult ->
            recipeDetailsResult(operationResult)
        }
        viewBinding = FragmentIngredientsBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    private fun recipeDetailsResult(operationResult: OperationResult<RecipeDetail>) {
        when(operationResult) {
            is OperationResult.Loading -> {
                viewBinding.progressBar.visibility = View.VISIBLE
            }
            is OperationResult.Success -> {
                viewBinding.progressBar.visibility = View.GONE
                viewBinding.cardView.visibility = View.VISIBLE
                viewBinding.ingredientsTextView.text = operationResult.data?.extendedIngredients
            }
            is OperationResult.Error -> {
                viewBinding.progressBar.visibility = View.GONE
                val view = viewBinding.progressBar.rootView
                Snackbar.make(
                    view,
                    operationResult.message.toString(),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}