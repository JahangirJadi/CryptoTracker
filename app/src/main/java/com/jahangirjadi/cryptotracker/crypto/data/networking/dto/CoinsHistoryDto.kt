package com.jahangirjadi.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinsHistoryDto(
    val data:List<CoinPriceDto>
)
