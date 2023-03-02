package com.example.viapath.recipeslist.presentation.recipeslistactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.viapath.R
import com.example.viapath.databinding.ActivityRecipesListBinding
import com.example.viapath.recipedetail.presentation.recipesdetailactivity.RecipeDetailActivity
import com.example.viapath.recipeslist.commons.Constants
import com.example.viapath.recipeslist.domain.util.OperationResult
import com.example.viapath.recipeslist.presentation.recipeslistactivity.adapters.RecipesAdapter
import com.example.viapath.recipeslist.presentation.recipeslistactivity.models.RecipeResponse
import com.example.viapath.recipeslist.presentation.recipeslistactivity.viewmodel.RecipesListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesListActivity : AppCompatActivity(), RecipesAdapter.OnClick {

    private val viewModel: RecipesListViewModel by lazy {
        ViewModelProvider(this)[RecipesListViewModel::class.java]
    }
    private lateinit var viewBinding: ActivityRecipesListBinding
    private val recipesAdapter =  RecipesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_list)
        viewBinding = ActivityRecipesListBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        liveDataObserver()
        setupRecyclerView(recipesAdapter)
        searchTermListeners()
    }

    private fun liveDataObserver() {
        viewModel.recipesListLiveData.observe(this) { operationResult ->
            getRecipesListResult(operationResult)
        }
        viewModel.getRecipesList()
    }

    private fun getRecipesListResult(operationResult: OperationResult<RecipeResponse>) {
        when(operationResult) {
            is OperationResult.Loading -> {
                viewBinding.progressBar.visibility = View.VISIBLE
            }
            is OperationResult.Success -> {
                viewBinding.progressBar.visibility = View.GONE
                operationResult.data?.let { recipesAdapter.setData(it) }
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

    private fun setupRecyclerView(adapter: RecipesAdapter) {
        adapter.onClick = this
        viewBinding.recipesRecyclerView.layoutManager = StaggeredGridLayoutManager(
            Constants.SPAN_COUNT,
            StaggeredGridLayoutManager.VERTICAL
        )
        viewBinding.recipesRecyclerView.adapter = recipesAdapter
    }

    private fun searchTermListeners() {
        val searchTerms = resources.getStringArray(R.array.search_terms)
        val adapter = ArrayAdapter(this, R.layout.search_term_item, searchTerms)
        viewBinding.autoCompleteTextView.setAdapter(adapter)
        viewBinding.autoCompleteTextView.setOnItemClickListener { adapterView, _, i, _ ->
            val selectedTerm = adapterView.getItemAtPosition(i)
            viewModel.getRecipesList(selectedTerm.toString())
        }
    }

    override fun onRecipeClicked(id: String,title: String) {
        val intent = Intent(this, RecipeDetailActivity::class.java)
        intent.putExtra(Constants.RECIPE_ID, id)
        intent.putExtra(Constants.RECIPE_TITLE, title)
        startActivity(intent)
    }
}