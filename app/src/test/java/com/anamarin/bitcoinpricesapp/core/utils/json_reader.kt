package com.anamarin.bitcoinpricesapp.core.utils

import java.io.File

fun readFileAsString(fileName: String){
    File("test_bitcoin_prices.json").readText(Charsets.UTF_8)
}