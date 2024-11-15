package com.jahangirjadi.cryptotracker.crypto.data.networking

import com.jahangirjadi.cryptotracker.core.data.networking.constructUrl
import com.jahangirjadi.cryptotracker.core.data.networking.safeCall
import com.jahangirjadi.cryptotracker.core.domain.util.NetworkError
import com.jahangirjadi.cryptotracker.core.domain.util.Result
import com.jahangirjadi.cryptotracker.core.domain.util.map
import com.jahangirjadi.cryptotracker.crypto.data.mappers.toCoin
import com.jahangirjadi.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.jahangirjadi.cryptotracker.crypto.domain.Coin
import com.jahangirjadi.cryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val client: HttpClient
) : CoinDataSource {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            client.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin()
            }
        }
    }
}