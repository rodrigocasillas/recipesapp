package com.example.viapath.recipedetail.presentation.recipesdetailactivity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viapath.recipedetail.data.network.entities.request.RecipeDetailRequest
import com.example.viapath.recipedetail.domain.usecase.GetRecipeDetailsUseCase
import com.example.viapath.recipedetail.domain.usecase.InsertRecipeDetailUseCase
import com.example.viapath.recipedetail.domain.util.OperationResult
import com.example.viapath.recipedetail.presentation.recipesdetailactivity.models.RecipeDetail
import com.example.viapath.recipedetail.presentation.recipesdetailactivity.models.toRecipeDetailDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val insertRecipeDetailUseCase: InsertRecipeDetailUseCase
): ViewModel() {

    private val _recipeDetailLiveData = MutableLiveData<OperationResult<RecipeDetail>>()
    val recipeDetailLiveData: LiveData<OperationResult<RecipeDetail>>
        get() = _recipeDetailLiveData

    fun getRecipeDetails(recipeId: String) {
        val recipeDetailRequest = RecipeDetailRequest(recipeId)
        _recipeDetailLiveData.postValue(OperationResult.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val recipeDetail = getRecipeDetailsUseCase(recipeDetailRequest)
            _recipeDetailLiveData.postValue(recipeDetail)
            recipeDetail.data?.toRecipeDetailDto()?.let { insertRecipeDetailUseCase(it) }
        }
    }
}
