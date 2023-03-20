package com.project.mycompose.modul.basic3.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    @StringRes val day: Int,
    @StringRes val city: Int,
    @DrawableRes val image: Int,
    @StringRes val description: Int
)