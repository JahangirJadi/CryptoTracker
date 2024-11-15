package com.jahangirjadi.cryptotracker.crypto.presentation.coin_list

import com.jahangirjadi.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUI: CoinUi) : CoinListAction

}