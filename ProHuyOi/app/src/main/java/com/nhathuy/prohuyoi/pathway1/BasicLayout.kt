
package com.nhathuy.prohuyoi.pathway1
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min


import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.core.graphics.drawable.toDrawable
import com.nhathuy.prohuyoi.R
import com.nhathuy.prohuyoi.alignYourBodyData
import com.nhathuy.prohuyoi.favoriteCollectionsData
import com.nhathuy.prohuyoi.ui.theme.ProHuyOiTheme

class BasicLayout : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProHuyOiTheme {
                // A surface container using the 'background' color from the theme
                val windowSize = calculateWindowSizeClass(activity = this)
                MySootheApp(windowSize = windowSize)
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchInput(modifier: Modifier=Modifier){
    TextField(value = "", onValueChange = {}
    , leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription =null)
        },
    modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 25.dp),
    colors = TextFieldDefaults.colors(
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        focusedContainerColor = MaterialTheme.colorScheme.surface
    ), placeholder ={
        Text(text = stringResource(R.string.search_placeholder))
        })
}

@Composable
fun AlignYourBodyElement(@DrawableRes drawable:Int,@StringRes text:Int, modifier: Modifier=Modifier){
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = drawable)
            , contentDescription =null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(88.dp)
            .clip(CircleShape))
        Text(text = stringResource(id = text),
        modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
        style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun FavoriteCollectionCard(@DrawableRes  drawable: Int,@StringRes text : Int,modifier: Modifier=Modifier){
    Surface(shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surfaceVariant, modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.width(255.dp)) {
            Image(painter = painterResource(id = drawable)
                , contentDescription =null,
                contentScale = ContentScale.Crop,
            modifier = Modifier.size(80.dp))
            Text(text = stringResource(id = text),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Composable
fun AlignYourBodyRow(modifier: Modifier=Modifier){
    LazyRow(modifier=modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    contentPadding = PaddingValues(horizontal = 16.dp)
    ){
        items(alignYourBodyData){item ->
            AlignYourBodyElement(item.drawable, item.text)
        }
    }
}
@Composable
fun FavoriteCollectionGrid(modifier: Modifier=Modifier){
    LazyHorizontalGrid(rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ){
        items(favoriteCollectionsData){ item ->
            FavoriteCollectionCard(item.drawable,item.text,Modifier.height(80.dp))
        }
    }
}
@Composable
fun HomeSection(
    @StringRes title:Int,
    modifier: Modifier=Modifier,
    content: @Composable ()->Unit
){
    Column(modifier) {
        Text(text = stringResource(id = title),
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
            .padding(horizontal = 16.dp)
            .paddingFromBaseline(top = 40.dp, bottom = 16.dp))
        content()
    }

}
@Composable
fun HomeScreen(modifier: Modifier=Modifier){
    Column(modifier=modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = modifier.height(16.dp))
        SearchInput(modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionGrid()
        }
        Spacer(modifier = Modifier.height(16.dp) )
    }
}
@Composable
fun SootheBottomNavigation(modifier: Modifier=Modifier){
    NavigationBar(modifier = modifier) {
        NavigationBarItem(selected = true
            , onClick = {/*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.Spa, contentDescription = null)
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_home))
            })
        NavigationBarItem(selected = false
            , onClick = {/*TODO*/ },
            icon = {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
            },
            label = {
                Text(text = stringResource(id = R.string.bottom_navigation_profile))
            })

    }
}
@Composable
fun SootheNavigationRail(modifier: Modifier=Modifier){
    NavigationRail(
        modifier = modifier.padding(end = 8.dp, start = 8.dp),
        contentColor = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            NavigationRailItem(selected = true
                , onClick = {/*TODO*/ },
                icon = {
                    Icon(imageVector = Icons.Default.Spa, contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = R.string.bottom_navigation_home))
                })
            Spacer(modifier = Modifier.height(8.dp))
            NavigationRailItem(selected = false
                , onClick = {/*TODO*/ },
                icon = {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = R.string.bottom_navigation_profile))
                })
        }
    }
}
@Composable
fun MySoothAppPortrait(){
    Scaffold(bottomBar = { SootheBottomNavigation()}) {
        padding-> HomeScreen(Modifier.padding(padding))
    }
}

@Composable
fun MySootheAppLandscape() {
    ProHuyOiTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                SootheNavigationRail()
                HomeScreen()
            }
        }
    }
}

@Composable
fun MySootheApp(windowSize: WindowSizeClass){
    when(windowSize.widthSizeClass){
        WindowWidthSizeClass.Compact ->{
            MySoothAppPortrait()
        }
        WindowWidthSizeClass.Expanded ->{
            MySootheAppLandscape()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MySoothAppPreview(){
    ProHuyOiTheme {
        MySoothAppPortrait()
    }
}
@Preview(showBackground = true)
@Composable
fun BottomNavigationPreview(){
    ProHuyOiTheme {
        SootheBottomNavigation()
    }
}
@Preview(showBackground = true)
@Composable
fun SearchInputPreview(){
    ProHuyOiTheme {
        SearchInput()
    }
}

@Preview(showBackground = true,backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElementPreview(){
    ProHuyOiTheme {
        AlignYourBodyElement(drawable = R.drawable.ab1_inversions, text =R.string.ab1_inversions, modifier = Modifier.padding(8.dp) )
    }
}
@Preview(showBackground = true, backgroundColor =0xFFF5F0EE )
@Composable
fun FavoriteCollectionCard(){
    ProHuyOiTheme {
        FavoriteCollectionCard(drawable = R.drawable.fc2_nature_meditations, text = R.string.fc2_nature_meditations, modifier = Modifier.padding(8.dp))
    }
}
@Preview(showBackground = true, backgroundColor =0xFFF5F0EE )
@Composable
fun AlignYourBodyRowPreview(){
    ProHuyOiTheme {
        AlignYourBodyRow()
    }
}
@Preview(showBackground = true, backgroundColor =0xFFF5F0EE )
@Composable
fun FavoriteGridPreview(){
    ProHuyOiTheme {
        FavoriteCollectionGrid()
    }
}
@Preview(showBackground = true, backgroundColor =0xFFF5F0EE )
@Composable
fun HomeSectionPreview(){
    ProHuyOiTheme {
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}
@Preview(showBackground = true, backgroundColor =0xFFF5F0EE , heightDp = 180)
@Composable
fun HomeScreenPreview(){
    ProHuyOiTheme {
        HomeScreen()
    }
}
@Preview(showBackground = true)
@Composable
fun MySootheAppLandscapePreview(){
    ProHuyOiTheme {
        MySootheAppLandscape()
    }
}