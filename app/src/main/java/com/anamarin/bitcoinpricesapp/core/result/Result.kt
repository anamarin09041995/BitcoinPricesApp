package com.anamarin.bitcoinpricesapp.core.result

import java.lang.Exception

abstract class Result<T>

data class Success<T>(val data: T) : Result<T>()

data class Error(val exception: Exception) : Result<Unit>()