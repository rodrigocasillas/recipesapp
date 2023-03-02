package com.example.viapath.recipedetail.presentation.recipesdetailactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.viapath.R
import com.example.viapath.databinding.ActivityRecipeDetailBinding
import com.example.viapath.recipedetail.presentation.recipesdetailactivity.viewmodel.RecipeDetailsViewModel
import com.example.viapath.recipedetail.commons.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityRecipeDetailBinding
    private lateinit var navController: NavController

    private val viewModel: RecipeDetailsViewModel by lazy {
        ViewModelProvider(this)[RecipeDetailsViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        navControllerSetup()
        val recipeId = intent.getStringExtra(Constants.RECIPE_ID) ?: resources.getString(
            R.string.empty_string
        )
        setupActionBarTitle()
        viewModel.getRecipeDetails(recipeId)
    }

    private fun setupActionBarTitle() {
        val recipeTitle = intent.getStringExtra(Constants.RECIPE_TITLE) ?: resources.getString(
            R.string.empty_string
        )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = recipeTitle
    }

    private fun navControllerSetup() {
        navController = Navigation.findNavController(
            this,
            R.id.navHostFragmentContainer
        )
        setupWithNavController(viewBinding.bottomNavigationView, navController)
    }
}