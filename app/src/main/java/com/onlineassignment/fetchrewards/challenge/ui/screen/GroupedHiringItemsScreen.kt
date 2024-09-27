package com.onlineassignment.fetchrewards.challenge.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlineassignment.fetchrewards.challenge.R
import com.onlineassignment.fetchrewards.challenge.domain.models.HiringItem
import com.onlineassignment.fetchrewards.challenge.ui.theme.FetchRewardsOATheme
import com.onlineassignment.fetchrewards.challenge.viewmodel.HiringViewModel
import com.onlineassignment.fetchrewards.challenge.viewmodel.HiringUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun GroupedHiringItemsScreen(
    modifier: Modifier = Modifier,
    viewModel: HiringViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Column(modifier = modifier
        .fillMaxSize()
        .padding(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.grouped_items_list_title),
            style = MaterialTheme.typography.displaySmall,
        )
        Box(modifier = Modifier.fillMaxSize()) {
            when (val value = uiState.value) {
                is HiringUiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                is HiringUiState.Success -> GroupedHiringItemsList(groupedItems = value.data)
                is HiringUiState.Error -> ErrorWithRetryButton {
                    viewModel.fetchGroupedHiringItems()
                }
            }
        }
    }

}

@Composable
fun GroupedHiringItemsList(groupedItems: Map<Long, List<HiringItem>>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        groupedItems.forEach { (listId, hiringItems) ->
            item {
                HiringItemHeader(listId = listId)
            }
            items(hiringItems) { hiringItem ->
                HiringItemCard(hiringItem = hiringItem)
            }
        }
    }
}

@Composable
fun HiringItemHeader(listId: Long) {
    Text(
        text = stringResource(id = R.string.list_id_header, listId),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun HiringItemCard(hiringItem: HiringItem) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = hiringItem.name,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}

@Composable
fun ErrorWithRetryButton(onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.error_loading_text),
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = onRetry,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.error_retry_button_text))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RewardsListScreenPreview() {
    FetchRewardsOATheme {
        GroupedHiringItemsScreen()
    }
}
