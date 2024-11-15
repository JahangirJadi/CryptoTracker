package com.jahangirjadi.cryptotracker.crypto.data.mappers

import com.jahangirjadi.cryptotracker.crypto.data.networking.dto.CoinDto
import com.jahangirjadi.cryptotracker.crypto.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}

fun Coin.toCoinDto(): CoinDto {
    return CoinDto(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )

}