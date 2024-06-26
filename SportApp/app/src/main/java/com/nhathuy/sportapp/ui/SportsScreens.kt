package com.nhathuy.sportapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.*
import com.nhathuy.sportapp.R
import com.nhathuy.sportapp.data.LocalSportsDataProvider
import com.nhathuy.sportapp.model.Sport
import com.nhathuy.sportapp.ui.theme.SportsTheme

//@Composable
//fun SportsApp(){
//
//    val viewModel: SportsViewModel = viewModel()
//    val uiState by viewModel.uiState.collectAsState()
//
//    Scaffold(
//        topBar = {
//            SportsAppBar(isShowingListPage=uiState.isShowingListPage,
//            onBackButtonClick={viewModel.navigateToListPage()})
//        }
//    ) {
//        innerPadding ->
//        if(uiState.isShowingListPage){
//            SportsList(sports=uiState.sportsList,
//            onClick= {
//                viewModel.updateCurrentSport(it)
//                viewModel.navigateToListPage()
//            },contentPadding=innerPadding,
//            modifier= Modifier
//                .fillMaxWidth()
//                .padding(
//                    top = dimensionResource(id = R.dimen.padding_medium),
//                    bottom = dimensionResource(id = R.dimen.padding_medium),
//                    end = dimensionResource(id = R.dimen.padding_medium)
//                ))
//        }
//        else{
//            SportsDetail(
//                selectedSport =uiState.currentSport,
//                contentPadding= innerPadding,
//                onBackPressed= {
//                    viewModel.navigateToDetailPage()
//                }
//            )
//        }
//    }
//}
//
//@Composable
//fun SportsDetail(selectedSport: Sport, contentPadding: PaddingValues, onBackPressed: () -> Unit,modifier: Modifier=Modifier) {
//        BackHandler {
//            onBackPressed()
//        }
//    val scrollState= rememberScrollState()
//    val layoutDirection= LocalLayoutDirection.current
//    Box(modifier = modifier
//        .verticalScroll(state = scrollState)
//        .padding(top = contentPadding.calculateTopPadding())){
//    Column(modifier = Modifier.padding(bottom = contentPadding.calculateBottomPadding(),
//    start = contentPadding.calculateStartPadding(layoutDirection), end = contentPadding.calculateEndPadding(layoutDirection))) {
//        Box{
//            Box {
//               Image(painter = painterResource(id = selectedSport.sportsImageBanner)
//                   , contentDescription = null,
//               alignment = Alignment.TopCenter,
//               contentScale = ContentScale.FillWidth)
//            }
//            Column(
//                Modifier
//                    .align(Alignment.BottomStart)
//                    .fillMaxWidth()
//                    .background(
//                        Brush.verticalGradient(
//                            listOf(Color.Transparent, MaterialTheme.colorScheme.scrim), 0f,
//                            400f
//                        )
//                    )) {
//                Text(text = stringResource(id = selectedSport.titleResourceId), style = MaterialTheme.typography.headlineLarge,
//                color=MaterialTheme.colorScheme.inverseOnSurface,
//                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)))
//                Row(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))) {
//                    Text(text = pluralStringResource(R.plurals.player_count_caption,selectedSport.playerCount,selectedSport.playerCount),
//                    style = MaterialTheme.typography.bodySmall,
//                    color = MaterialTheme.colorScheme.inverseOnSurface)
//                    Spacer(modifier = Modifier.weight(1f))
//                    Text(text = stringResource(id = R.string.olympic_caption), style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.inverseOnSurface)
//                }
//
//            }
//        }
//        Text(text = stringResource(id = selectedSport.sportDetails), style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(vertical = dimensionResource(
//            id = R.dimen.padding_detail_content_vertical), horizontal = dimensionResource(id = R.dimen.padding_detail_content_horizontal)
//        ))
//    }
//    }
//}
//
//@Composable
//fun SportsList(sports: List<Sport>, onClick: (Sport) -> Unit, contentPadding: PaddingValues= PaddingValues(0.dp), modifier: Modifier=Modifier) {
//    LazyColumn(contentPadding=contentPadding,
//    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)), modifier = modifier){
//        items(sports, key={ sport -> sport.id}) {
//            sport ->
//                SportsListItem(sport =sport, onItemClick =onClick)
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SportsListItem(sport: Sport, onItemClick: (Sport) -> Unit, modifier: Modifier=Modifier) {
//    Card(elevation = CardDefaults.cardElevation(), modifier = modifier,
//    shape = RoundedCornerShape(dimensionResource(id = R.dimen.card_corner_radius)),
//    onClick ={onItemClick(sport)}
//    ) {
//        Row(modifier = Modifier
//            .fillMaxWidth()
//            .size(dimensionResource(id = R.dimen.card_image_height))) {
//            SportsImageItem(sport=sport,modifier=Modifier.size(dimensionResource(id = R.dimen.card_image_height)))
//            Column(modifier = Modifier
//                .padding(
//                    vertical = dimensionResource(id = R.dimen.padding_small),
//                    horizontal = dimensionResource(id = R.dimen.padding_medium)
//                )
//                .weight(1f)) {
//                Text(text = stringResource(id = sport.titleResourceId),
//                style = MaterialTheme.typography.titleMedium,
//                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.card_text_vertical_space)))
//                Text(text = stringResource(id = sport.subtitleResourceId)
//                , style = MaterialTheme.typography.bodySmall,
//                color = MaterialTheme.colorScheme.secondary,
//                overflow = TextOverflow.Ellipsis,
//                maxLines = 3)
//                Spacer(modifier = Modifier.weight(1f))
//                Row(modifier.weight(1f)) {
//                    Text(text = pluralStringResource(id = R.plurals.player_count_caption,sport.playerCount,sport.playerCount), style = MaterialTheme.typography.bodySmall)
//                    Spacer(modifier = Modifier.weight(1f))
//                    if(sport.olympic){
//                        Text(text = stringResource(id = R.string.olympic_caption), style = MaterialTheme.typography.labelMedium)
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun SportsImageItem(sport: Sport, modifier: Modifier=Modifier) {
//    Box(modifier = modifier){
//        Image(painter = painterResource(id = sport.imageResourceId), contentDescription =null,
//        alignment = Alignment.Center,
//        contentScale = ContentScale.FillWidth)
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SportsAppBar(isShowingListPage: Boolean, onBackButtonClick: () -> Unit,modifier: Modifier=Modifier) {
//    TopAppBar(title = {
//        Text(text =
//        if(!isShowingListPage){
//            stringResource(id = R.string.detail_fragment_label)
//        } else {
//            stringResource(id = R.string.list_fragment_label)
//        }
//        )
//    }, navigationIcon = {
//        if(!isShowingListPage){
//            IconButton(onClick = onBackButtonClick) {
//                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(id = R.string.back_button) )
//        }
//    }
//        else{
//            Box() {
//            }
//        }
//    }, colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary), modifier = modifier)
//}
//@Preview
//@Composable
//fun Preview(){
//    SportsTheme {
//        SportsListItem(sport = LocalSportsDataProvider.defaultSport, onItemClick = {})
//    }
//}
//@Preview
//@Composable
//fun SportsListPreview() {
//    SportsTheme {
//        Surface {
//            SportsList(
//                sports = LocalSportsDataProvider.getSportData(),
//                onClick = {},
//            )
//        }
//    }
//}
@Composable
fun SportsApp(
) {
    val viewModel: SportsViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            SportsAppBar(
                isShowingListPage = uiState.isShowingListPage,
                onBackButtonClick = { viewModel.navigateToListPage() },
            )
        }
    ) { innerPadding ->
        if (uiState.isShowingListPage) {
            SportsList(
                sports = uiState.sportsList,
                onClick = {
                    viewModel.updateCurrentSport(it)
                    viewModel.navigateToDetailPage()
                },
                contentPadding = innerPadding,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(R.dimen.padding_medium),
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                    )
            )
        } else {
            SportsDetail(
                selectedSport = uiState.currentSport,
                contentPadding = innerPadding,
                onBackPressed = {
                    viewModel.navigateToListPage()
                }
            )
        }
    }
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsAppBar(
    onBackButtonClick: () -> Unit,
    isShowingListPage: Boolean,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text =
                if (!isShowingListPage) {
                    stringResource(R.string.detail_fragment_label)
                } else {
                    stringResource(R.string.list_fragment_label)
                }
            )
        },
        navigationIcon = if (!isShowingListPage) {
            {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        } else {
            { Box {} }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SportsListItem(
    sport: Sport,
    onItemClick: (Sport) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(sport) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            SportsListImageItem(
                sport = sport,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(sport.titleResourceId),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Text(
                    text = stringResource(sport.subtitleResourceId),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
                Spacer(Modifier.weight(1f))
                Row {
                    Text(
                        text = pluralStringResource(
                            R.plurals.player_count_caption,
                            sport.playerCount,
                            sport.playerCount
                        ),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(Modifier.weight(1f))
                    if (sport.olympic) {
                        Text(
                            text = stringResource(R.string.olympic_caption),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SportsListImageItem(sport: Sport, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(sport.imageResourceId),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun SportsList(
    sports: List<Sport>,
    onClick: (Sport) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier,
    ) {
        items(sports, key = { sport -> sport.id }) { sport ->
            SportsListItem(
                sport = sport,
                onItemClick = onClick
            )
        }
    }
}

@Composable
private fun SportsDetail(
    selectedSport: Sport,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(top = contentPadding.calculateTopPadding())
    ) {
        Column(
            modifier = Modifier
                .padding(
                    bottom = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection)
                )
        ) {
            Box {
                Box {
                    Image(
                        painter = painterResource(selectedSport.sportsImageBanner),
                        contentDescription = null,
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.FillWidth,
                    )
                }
                Column(
                    Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                                0f,
                                400f
                            )
                        )
                ) {
                    Text(
                        text = stringResource(selectedSport.titleResourceId),
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(R.dimen.padding_small))
                    )
                    Row(
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    ) {
                        Text(
                            text = pluralStringResource(
                                R.plurals.player_count_caption,
                                selectedSport.playerCount,
                                selectedSport.playerCount
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            text = stringResource(R.string.olympic_caption),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                        )
                    }
                }
            }
            Text(
                text = stringResource(selectedSport.sportDetails),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                    horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal)
                )
            )
        }
    }
}

@Preview
@Composable
fun SportsListItemPreview() {
    SportsTheme {
        SportsListItem(
            sport = LocalSportsDataProvider.defaultSport,
            onItemClick = {}
        )
    }
}

@Preview
@Composable
fun SportsListPreview() {
    SportsTheme {
        Surface {
            SportsList(
                sports = LocalSportsDataProvider.getSportData(),
                onClick = {},
            )
        }
    }
}