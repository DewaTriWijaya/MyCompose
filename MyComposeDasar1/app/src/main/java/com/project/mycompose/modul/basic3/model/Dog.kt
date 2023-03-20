package com.project.mycompose.modul.basic3.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Dog(
    @DrawableRes val imageResource: Int,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val hobbies: Int
)