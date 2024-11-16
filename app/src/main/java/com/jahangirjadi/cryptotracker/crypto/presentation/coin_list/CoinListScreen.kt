package com.jahangirjadi.cryptotracker.crypto.presentation.coin_list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.jahangirjadi.cryptotracker.core.presentation.util.toString
import com.jahangirjadi.cryptotracker.crypto.presentation.coin_list.components.CoinListItem
import com.jahangirjadi.cryptotracker.crypto.presentation.coin_list.components.previewCoin
import com.jahangirjadi.cryptotracker.ui.theme.CryptoTrackerTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun CoinListScreen(
    modifier: Modifier = Modifier,
    state: CoinListState,
    action: (CoinListAction) -> Unit ,
) {



    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins, key = { it.id }) { coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClick = {
                        action(CoinListAction.OnCoinClick(coinUi))
                    }, modifier = Modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }


}

@PreviewLightDark
@Composable
private fun CoinListScreenPrev() {
    CryptoTrackerTheme {
        CoinListScreen(
            action = {},
            state = CoinListState(
                isLoading = false,
                coins = (1..100).map {
                    previewCoin.copy(id = it.toString())
                }
            ), modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        )
    }
}