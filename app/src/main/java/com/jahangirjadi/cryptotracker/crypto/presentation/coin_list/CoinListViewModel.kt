package com.jahangirjadi.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jahangirjadi.cryptotracker.core.domain.util.onError
import com.jahangirjadi.cryptotracker.core.domain.util.onSuccess
import com.jahangirjadi.cryptotracker.crypto.domain.CoinDataSource
import com.jahangirjadi.cryptotracker.crypto.presentation.models.CoinUi
import com.jahangirjadi.cryptotracker.crypto.presentation.models.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart {
            loadCoins()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    private val _events = Channel<CoinListEvents>()
    val events = _events.receiveAsFlow()

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClick -> {
                selectCoin(action.coinUI)
            }
        }
    }

    private fun selectCoin(coinUI: CoinUi) {
        _state.update {
            it.copy(
                selectedCoin = coinUI
            )
        }

        viewModelScope.launch {

            coinDataSource
                .getCoinHistory(
                    coinId = coinUI.id,
                    start = ZonedDateTime.now().minusDays(5),
                    end = ZonedDateTime.now()
                )
                .onSuccess {
                    println(it)
                }
                .onError { error ->
                    _events.send(CoinListEvents.Error(error))
                }
        }

    }


    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            coinDataSource
                .getCoins()
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map { it.toCoinUi() }
                        )
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(CoinListEvents.Error(error))

                }

        }
    }

}