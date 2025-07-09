package com.example.myapplication.models

data class CoffeeBeanVariant(
    val resourceId: Int,
    val baseSize: Int
)

data class AnimationConfig(
    val beanCount: Int,
    val spreadRange: Float,
    val duration: Int
)