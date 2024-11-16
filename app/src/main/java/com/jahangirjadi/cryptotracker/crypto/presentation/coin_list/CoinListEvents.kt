package com.jahangirjadi.cryptotracker.crypto.presentation.coin_list

import com.jahangirjadi.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvents {
    data class Error(val error:NetworkError): CoinListEvents
}