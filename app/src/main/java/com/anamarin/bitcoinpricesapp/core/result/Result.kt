package com.anamarin.bitcoinpricesapp.core.result

import java.lang.Exception

open class Results<out T>{

    data class Success<out T>(val data: T) : Results<T>()

    data class Failure(val exception: Exception) : Results<Nothing>()

}

