package com.jahangirjadi.cryptotracker.crypto.domain

data class Coin(
    val id: String,
    val rank: String,
    val name: String,
    val symbol: String,
    val marketCapUsd: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double
)

