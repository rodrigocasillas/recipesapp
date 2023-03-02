package com.example.viapath.recipeslist.domain.usecase

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(request: Parameter): Result
}