package com.example.viapath.recipedetail.presentation.recipesdetailactivity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.activityViewModels
import com.example.viapath.R
import com.example.viapath.databinding.FragmentInstructionsBinding
import com.example.viapath.recipedetail.domain.util.OperationResult
import com.example.viapath.recipedetail.presentation.recipesdetailactivity.models.RecipeDetail
import com.example.viapath.recipedetail.presentation.recipesdetailactivity.models.getInstructions
import com.example.viapath.recipedetail.presentation.recipesdetailactivity.viewmodel.RecipeDetailsViewModel
import com.google.android.material.snackbar.Snackbar

class InstructionsFragment : Fragment() {

    private lateinit var viewBinding: FragmentInstructionsBinding
    private val recipeDetailsViewModel: RecipeDetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        recipeDetailsViewModel.recipeDetailLiveData.observe(viewLifecycleOwner) { operationResult ->
            recipeDetailsResult(operationResult)
        }
        viewBinding = FragmentInstructionsBinding.inflate(layoutInflater)
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
                viewBinding.instructionsTextView.text = HtmlCompat.fromHtml(
                    operationResult.data?.getInstructions()?: resources.getString(R.string.empty_string),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
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