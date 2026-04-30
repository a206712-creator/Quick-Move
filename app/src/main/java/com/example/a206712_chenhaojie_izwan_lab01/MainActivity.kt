package com.example.a206712_chenhaojie_izwan_lab01

import android.content.ClipData
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Label
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.navigation.compose.NavHost
import androidx.compose.ui.unit.Dp
import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.a206712_chenhaojie_izwan_lab01.WalletViewModel
import com.example.a206712_chenhaojie_izwan_lab01.ui.theme.A206712_ChenHaojie_Izwan_Lab01Theme
import kotlinx.coroutines.sync.Mutex



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A206712_ChenHaojie_Izwan_Lab01Theme {
                val navController = rememberNavController()
                val currentBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStackEntry?.destination?.route
                val viewModel: WalletViewModel = viewModel()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentRoute == "home" || currentRoute == "message") {
                            BottBar(navController)
                        }
                    }
                ) { innerPadding ->
                    val bottomPadding = innerPadding.calculateBottomPadding()

                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable("home") {
                            HomeScreen(
                                modifier = Modifier.padding(bottom = bottomPadding),
                                navController,
                                viewModel
                            )
                        }

                        composable("function1") {
                            FunctionScreen1(
                                navController,
                                modifier = Modifier.padding(bottom = bottomPadding)
                            )
                        }

                        composable("wallet1") {
                            WalletScreen1(
                                navController,
                                viewModel,
                                modifier = Modifier.padding(bottom = bottomPadding)
                            )
                        }
                        composable("booking") {
                            BookingScreen(
                                navController,
                                viewModel,
                                modifier = Modifier.padding(bottom = bottomPadding)
                            )

                        }
                        composable("bookingSummary") {
                            BookingSummaryScreen(
                                navController,
                                viewModel,
                                modifier = Modifier.padding(bottom = bottomPadding)
                            )
                        }
                        composable("message") {
                            MessageScreen(
                                navController,
                                viewModel,
                                modifier = Modifier.padding(bottom = bottomPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

//------------------------------------------------------------------------------
@Composable
fun HomeScreen(modifier: Modifier = Modifier,
               navController: NavController,
               viewModel: WalletViewModel = viewModel()
               ) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        TopBar()

        FunctionGrid(navController)

        WalletSection(navController,viewModel)

        PromoSection()

        RecommendedSection(navController)

        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Composable
fun TopBar(
)  {
    var searchText by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .height(118.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
            .align(Alignment.BottomCenter) ,
            horizontalArrangement = Arrangement.SpaceAround
            ) {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text(text = "Search",
                    color = Color.Gray) },
                modifier = Modifier
                    .weight(1f)
                    .height(55.dp),
                shape = MaterialTheme.shapes.large,
                singleLine = true,
                leadingIcon = {
                    Image(painter = painterResource(id = R.drawable.search),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    ) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults . colors (
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.width(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.scan),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .padding(top=6.dp)
                )
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(id = R.drawable.me),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .padding(top=6.dp)
            )

        }
    }
}



//--------------------------------------------------------------------------------
@Composable
fun FunctionGrid(navController: NavController) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GridItem(icon = R.drawable.car, label = "Car", modifier = Modifier
                .clickable {
                    navController.navigate("function1")
                })
            GridItem(icon = R.drawable.food, label = "Food")
            GridItem(icon = R.drawable.market, label = "Market")
            GridItem(icon = R.drawable.movie, label = "Movie")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GridItem(icon = R.drawable.hotel, label = "Hotel")
            GridItem(icon = R.drawable.dining, label = "Dining")
            GridItem(icon = R.drawable.bill, label = "Bill")
            GridItem(icon = R.drawable.more, label = "More")
        }
    }
}

@Composable
fun GridItem(
    @DrawableRes icon: Int,
    label: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.size(70.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = label,
                modifier = Modifier.size(34.dp)
            )
            Text(
                text = label,
                fontSize = 13.sp
            )
        }
    }
}

//---------------------------------------------------------------------------------

@Composable
fun WalletSection(navController: NavController,viewModel: WalletViewModel) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        item {
            WalletCard(
                title = "eWallet",
                subtitle = "RM0.00",
                icon = R.drawable.ewallet,
                cardWidth = 170.dp,
                modifier = Modifier.clickable {
                    viewModel.setWalletData("eWallet",
                        "RM0.00")
                    navController.navigate("wallet1")
                }
            )
        }

        item {
            WalletCard(
                title = "Take a vehicle to go",
                subtitle = "Savanna Executive...",
                icon = R.mipmap.car,
                cardWidth = 190.dp
            )
        }

        item {
            WalletCard(
                title = "appointment",
                subtitle = "Ride to airport",
                icon = R.drawable.appointment,
                cardWidth = 170.dp
            )
        }
    }
}

@Composable
fun WalletCard(
    title: String,
    subtitle: String,
    @DrawableRes icon: Int,
    cardWidth: Dp,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .width(cardWidth)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            ),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 12.sp,
                    color = Color.Yellow
                )
                Spacer(modifier = Modifier.weight(1f))
                ExpandButton(
                    modifier = Modifier.size(20.dp),
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = subtitle, fontSize = 13.sp)
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier
                        .height(35.dp)
                        .width(30.dp)
                        .padding(bottom = 4.dp)
                )
            }

            if (expanded) {
                ExtendContent(modifier = Modifier.padding(top = 6.dp))
            }
        }
    }
}

@Composable
   fun ExpandButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector =  if (expanded)
                Icons.Filled.ExpandLess
            else
                Icons.Filled.ExpandMore,
            contentDescription =null,
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun ExtendContent(
    modifier: Modifier = Modifier
) {Column(
    modifier = modifier
) {
    Text(
        text ="More Details",
        fontSize = 12.sp,
    )
}
}

//--------------------------------------------------------------------------

@Composable
fun PromoSection() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Promotion Activity",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
            Image(
                painter = painterResource(id = R.mipmap.forwardarrow),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp, bottom = 8.dp)
            )
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item {
                PromoItem(
                    image = R.drawable.promotion,
                    text = "40% OFF",
                    textColor = Color.Black
                )
            }

            item {
                PromoItem(
                    image = R.drawable.promotion1,
                    text = "20% OFF",
                    textColor = Color.Yellow
                )
            }

            item {
                Spacer(modifier = Modifier.width(25.dp))
                Card(
                    modifier = Modifier.size(70.dp),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.forwardarrow1),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(25.dp))
            }
        }
    }
}

@Composable
fun PromoItem(
    @DrawableRes image: Int,
    text: String,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(180.dp)
            .width(280.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                alpha = 0.9F
            )
            Text(
                text = text,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}


//------------------------------------------------------------------------------------

@Composable
fun RecommendedSection(navController: NavController) {
    val context = LocalContext.current

    Spacer(modifier = Modifier.height(14.dp))
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Recommended",
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 16.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item {
                RecommendedItem(image = R.drawable.mixue, modifier = Modifier.clickable {
                    shareShop(
                        context = context,
                        title = "MI XUE",
                        content = "MI XUE IS GOOD！"
                    ) } )
            }
            item {
                RecommendedItem(image = R.drawable.kfc)
            }
            item {
                RecommendedItem(image = R.drawable.starbuck)
            }
            item {
                RecommendedItem(image = R.drawable.maodie)
            }
        }
    }
}

@Composable
fun RecommendedItem(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(167.dp)
            .height(172.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

//-----------------------------------------------------------------------------

@Composable
fun BottBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .align(Alignment.TopCenter),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            BotItem(icon = R.drawable.home, label = "Home",modifier = Modifier.
            clickable {navController.navigate("home") })
            BotItem(icon = R.drawable.activity, label = "Activity")
            BotItem(icon = R.drawable.message, label = "Message",modifier = Modifier.
                clickable {navController.navigate("message") }
            )
            BotItem(icon = R.drawable.deliver, label = "delivery")
        }
    }
}

@Composable
fun BotItem(
    @DrawableRes icon: Int,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(33.dp)
                .padding(bottom = 4.dp)
        )
        Text(
            text = label,
            fontSize = 14.sp
        )
    }
}

//------------------------------------------------
private fun shareShop(
    context: Context,
    title: String,
    content: String
) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, title)
        putExtra(Intent.EXTRA_TEXT, content)
    }

    context.startActivity(
        Intent.createChooser(intent, "share shop")
    )
}



@Preview(showBackground = true)
@Composable
fun QuickMove() {
    A206712_ChenHaojie_Izwan_Lab01Theme{
        HomeScreen( modifier = Modifier,
            rememberNavController(),

            )

    }
    }
