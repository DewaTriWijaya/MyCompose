package com.project.mycompose.modul.basic3.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val title: Int,
    val count: Int,
    @DrawableRes val image: Int
)