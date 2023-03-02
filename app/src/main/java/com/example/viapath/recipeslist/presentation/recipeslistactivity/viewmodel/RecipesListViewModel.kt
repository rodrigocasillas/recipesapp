package com.example.viapath.recipeslist.presentation.recipeslistactivity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viapath.recipeslist.commons.Constants
import com.example.viapath.recipeslist.data.network.entities.request.RecipesListRequest
import com.example.viapath.recipeslist.domain.usecase.GetRecipesListUseCase
import com.example.viapath.recipeslist.domain.util.OperationResult
import com.example.viapath.recipeslist.presentation.recipeslistactivity.models.RecipeResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val getRecipesListUseCase: GetRecipesListUseCase
): ViewModel() {
    private val _recipesListLiveData = MutableLiveData<OperationResult<RecipeResponse>>()
    val recipesListLiveData: LiveData<OperationResult<RecipeResponse>>
        get() = _recipesListLiveData

    fun getRecipesList(searchTerm: String = Constants.DEFAULT_SEARCH_TERM) {
        val request = RecipesListRequest(query = searchTerm)
        _recipesListLiveData.postValue(OperationResult.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val recipeResponse = getRecipesListUseCase(request)
            _recipesListLiveData.postValue(recipeResponse)
        }
    }
}