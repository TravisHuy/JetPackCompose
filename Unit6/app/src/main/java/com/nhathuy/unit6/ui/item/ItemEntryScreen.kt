package com.nhathuy.unit6.ui.item

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.nhathuy.unit6.R
import com.nhathuy.unit6.ui.AppViewModelProvider
import com.nhathuy.unit6.ui.navigation.NavigationDestination
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nhathuy.unit6.InventoryTopAppBar
import com.nhathuy.unit6.ui.theme.Unit6Theme
import kotlinx.coroutines.launch

object ItemEntryDestination : NavigationDestination{
    override val route = "item_entry"
    override val titleRes = R.string.item_entry_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp : () -> Unit,
    canNavigateBack : Boolean= true,
    viewModel: ItemEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = {
            InventoryTopAppBar(title = stringResource(id = ItemEntryDestination.titleRes), canNavigateBack =canNavigateBack,
            navigateUp = onNavigateUp)
    }) {
       innerPadding ->
        ItemEntryBody(
            itemUiState=viewModel.itemUiState,
            onItemValueChange= viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveItem()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
                )
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun ItemEntryBody(itemUiState: ItemUiState, onItemValueChange: (ItemDetails) -> Unit, onSaveClick: () -> Unit,modifier:Modifier=Modifier) {
    Column(modifier=modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_larger))) {
        ItemInputForm(
            itemDetails=itemUiState.itemDetails,
            onValueChange=onItemValueChange,
            modifier=Modifier.fillMaxWidth()
        )
        Button(onClick = onSaveClick,
        enabled = itemUiState.isEntryValid,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.fillMaxWidth()) {
            Text(text = stringResource(id = R.string.save_action))
        }
    }
}

@Composable
fun ItemInputForm(itemDetails: ItemDetails, onValueChange: (ItemDetails) -> Unit = {},enabled:Boolean=true, modifier: Modifier) {
    Column(modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))) {
        OutlinedTextField(value = itemDetails.name, onValueChange ={onValueChange(itemDetails.copy(name=it))},
        label = { Text(text = stringResource(id = R.string.item_name_req))},
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        ), modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = true)

        OutlinedTextField(value = itemDetails.price, onValueChange ={onValueChange(itemDetails.copy(price =it))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { Text(text = stringResource(id = R.string.item_price_req))},
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ), modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true)

        OutlinedTextField(value = itemDetails.quantity, onValueChange ={onValueChange(itemDetails.copy(quantity =it))},
            label = { Text(text = stringResource(id = R.string.quantity_req))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            ), modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true)
        if(enabled){
            Text(text = stringResource(id = R.string.required_fields),modifier=Modifier.padding(start = dimensionResource(
                id = R.dimen.padding_small
            )))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ItemEntryScreenPreview() {
    Unit6Theme{
        ItemEntryBody(itemUiState = ItemUiState(
            ItemDetails(
                name = "Item name", price = "10.00", quantity = "5"
            )
        ), onItemValueChange = {}, onSaveClick = {})
    }
}