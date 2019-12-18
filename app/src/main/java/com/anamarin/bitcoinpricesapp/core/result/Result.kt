package com.anamarin.bitcoinpricesapp.core.result

import java.lang.Exception

//out:

open class Outcome<out T>{

    data class Success<out T>(val data: T) : Outcome<T>()
    data class Failure(val cause: Exception) : Outcome<Nothing>()

}



