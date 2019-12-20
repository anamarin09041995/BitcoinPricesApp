package com.anamarin.bitcoinpricesapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "bitcoin_table")
open class BitcoinInfoModel(
    @PrimaryKey
    val id: Long = 0,
    val status: String? = null,
    val name: String = "",
    val unit: String = "",
    val period: String? = null,
    val description: String? = null
) : Serializable