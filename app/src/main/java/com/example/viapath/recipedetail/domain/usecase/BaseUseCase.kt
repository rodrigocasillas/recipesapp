package com.example.viapath.recipedetail.domain.usecase

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(request: Parameter): Result
}