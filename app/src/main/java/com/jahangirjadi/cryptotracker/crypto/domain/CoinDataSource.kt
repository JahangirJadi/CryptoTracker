package com.jahangirjadi.cryptotracker.crypto.domain

import com.jahangirjadi.cryptotracker.core.domain.util.NetworkError
import com.jahangirjadi.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins():Result<List<Coin>, NetworkError>
}