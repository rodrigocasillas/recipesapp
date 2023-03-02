package com.example.viapath.recipedetail.data.network.entities.response

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)