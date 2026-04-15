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
import androidx.compose.material3.Shapes
import com.example.a206712_chenhaojie_izwan_lab01.ui.theme.A206712_ChenHaojie_Izwan_Lab01Theme
import kotlinx.coroutines.sync.Mutex

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A206712_ChenHaojie_Izwan_Lab01Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottBar() }
                ){
                    innerPadding ->
                    HomeScreen(modifier = Modifier.padding
                        (bottom = innerPadding.calculateBottomPadding()))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        TopBar()

       // UserInputSection()

        FunctionGrid()

        WalletSection()

        PromoSection()

        RecommendedSection()

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
                shape = RoundedCornerShape(20.dp),
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
                        .clip(RoundedCornerShape(12.dp))
                        .padding(top=6.dp)
                )
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(id = R.drawable.me),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .padding(top=6.dp)
            )

        }
    }
}

/*@Composable
fun UserInputSection() {

    var name by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = "Matric No: A206712",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Enter your name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                result = "Hello $name (A206712)"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = result,
            fontSize = 18.sp,
            color = Color(0xFF3F51B5)
        )
    }
}*/

@Composable
fun FunctionGrid(){
    Column(modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)  ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
                Card(
                    modifier = Modifier
                        .size(70.dp),
                    shape = RoundedCornerShape(12.dp)

                ){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement  = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally){
                        Image(
                            painter = painterResource(id = R.drawable.car),
                            contentDescription = null,
                            modifier = Modifier.size(34.dp)
                        )
                    Text(
                       text ="Car",
                        fontSize = 13.sp
           )}
                }

            Card (
                modifier = Modifier
                    .size(70.dp),
                shape = RoundedCornerShape(12.dp)

            ){
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement  = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        painter = painterResource(id = R.drawable.food),
                        contentDescription = null,
                        modifier = Modifier.size(34.dp)
                    )
            Text(
                text ="Food",
                fontSize = 13.sp
            )}
            }

            Card(
                modifier = Modifier
                    .size(70.dp),
                shape = RoundedCornerShape(12.dp)

            ){
                Column( modifier = Modifier.fillMaxSize(),
                    verticalArrangement  = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        painter = painterResource(id = R.drawable.market),
                        contentDescription = null,
                        modifier = Modifier.size(34.dp)
                    )
            Text(
                text ="Market",
                fontSize = 13.sp
            )}
            }

            Card(
                modifier = Modifier
                    .size(70.dp),
                shape = RoundedCornerShape(12.dp)

            ){
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement  = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        painter = painterResource(id = R.drawable.movie),
                        contentDescription = null,
                        modifier = Modifier.size(34.dp)
                    )
            Text(
                text ="Movie",
                fontSize = 13.sp
            )}
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Card(
                modifier = Modifier
                    .size(70.dp),
                shape = RoundedCornerShape(12.dp)

            ) {
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement  = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        painter = painterResource(id = R.drawable.hotel),
                        contentDescription = null,
                        modifier = Modifier.size(34.dp)
                    )
                    Text(text = "Hotel",
                        fontSize = 13.sp)
                }
            }

            Card(
                modifier = Modifier
                    .size(70.dp)
                    ,
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement  = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        painter = painterResource(id = R.drawable.dining),
                        contentDescription = null,
                        modifier = Modifier.size(34.dp)
                    )
                    Text(text = "Dining",
                        fontSize = 13.sp)
                }
            }

            Card(
                modifier = Modifier
                    .size(70.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement  = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        painter = painterResource(id = R.drawable.bill),
                        contentDescription = null,
                        modifier = Modifier.size(34.dp)
                    )
                    Text(text = "Bill",
                        fontSize = 13.sp)
                }
            }

            Card(
                modifier = Modifier
                    .size(70.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement  = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally){
                    Image(
                        painter = painterResource(id = R.drawable.more),
                        contentDescription = null,
                        modifier = Modifier.size(34.dp)
                    )
                    Text(text = "More",
                        fontSize = 13.sp)
                }
            }
        }
    }
}

@Composable
fun WalletSection() {

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            item {
                var expanded by remember { mutableStateOf(false) }
                    Card (
                        modifier = Modifier
                            .width(170.dp)
                            //.height(60.dp)
                            .animateContentSize(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioNoBouncy,
                                    stiffness = Spring.StiffnessMedium
                                )
                            ),
                          shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondary)
                            )
                     {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.dp),
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()) {
                                Text(
                                    text = "eWallet", fontSize = 12.sp,
                                    color = Color.Yellow
                                )
                                Spacer(modifier = Modifier.weight(1f))

                                ExpandButton(
                                    modifier = Modifier.size(20.dp),
                                    expanded = expanded,
                                    onClick = { expanded = !expanded })
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "RM0.00", fontSize = 13.sp)
                                Image(
                                    painter = painterResource(id = R.drawable.ewallet),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(35.dp)
                                        .width(30.dp)
                                        .padding(bottom = 4.dp)
                                )
                            }
                            if (expanded) {
                                ExtendContent(modifier = Modifier.padding(10.dp))
                            }
                        }
                    }
            }
            item {
                var expanded by remember { mutableStateOf(false) }
                Card(
                        modifier = Modifier
                            .width(190.dp)
                            //.height(60.dp)
                            .animateContentSize(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioNoBouncy,
                                    stiffness = Spring.StiffnessMedium
                                )
                            )
                            ,
                               shape =  RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary)
                            )
                     {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.dp),
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()) {
                                Text(
                                    text = "Take a vehicle to go", fontSize = 12.sp,
                                    color = Color.Yellow )
                                            Spacer (modifier = Modifier.weight(1f))
                                            ExpandButton (modifier = Modifier.size(20.dp),
                                    expanded = expanded,
                                    onClick = { expanded = !expanded })

                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Savanna Executive...", fontSize = 13.sp)
                                Image(
                                    painter = painterResource(id = R.mipmap.car),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(38.dp)
                                        .width(33.dp)
                                        .padding(bottom = 4.dp)
                                )
                            }
                            if (expanded) {
                                ExtendContent(modifier = Modifier.padding(10.dp))
                            }
                        }
                    }
            }
            item {
                Card(
                    modifier = Modifier
                        .width(190.dp)
                        .height(60.dp)
                    ,colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondary),
                    shape =  RoundedCornerShape(12.dp)
                ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(2.dp),
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                                Text(
                                    text = "appointment", fontSize = 12.sp,
                                    color = Color.Yellow
                                )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Ride to airport", fontSize = 13.sp)
                                Image(
                                    painter = painterResource(id = R.drawable.appointment),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(35.dp)
                                        .width(30.dp)
                                        .padding(bottom = 4.dp)
                                )
                            }
                        }
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


@Composable
fun PromoSection() {

        Column(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Promotion Activity",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                Image(
                    painter = painterResource(id = R.mipmap.forwardarrow),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
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
                    Card(
                        modifier = Modifier
                            .height(180.dp)
                            .width(280.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.promotion),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                            alpha = 0.9F
                        )
                        Text(
                            text = "40% OFF",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(6.dp)
                        )
                    }

                }

                item {
                    Card(
                        modifier = Modifier
                            .height(180.dp)
                            .width(280.dp)
                        ,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.promotion1),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                            alpha = 0.9F
                        )
                        Text(
                            text = "20% OFF",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Yellow,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(6.dp)
                        )
                    }
                }
                item {
                        Spacer(modifier = Modifier.width(25.dp))

                       Card(modifier = Modifier.size(70.dp),
                          shape = RoundedCornerShape(50.dp)
                        ) {  Image(
                            painter = painterResource(id = R.mipmap.forwardarrow1),
                            contentDescription = null,
                            modifier = Modifier.size(70.dp)
                        ) }
                        Spacer(modifier = Modifier.width(25.dp))

                }
            }
        }
    }


@Composable
fun RecommendedSection() {
    Spacer(modifier = Modifier.height(14.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        Text(
            text = "Recommended",
            fontSize = 16.sp,
            modifier = Modifier
                .padding(start = 16.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
           item {
               Card(
                   modifier = Modifier
                       .width(167.dp)
                       .height(172.dp),
                   shape = RoundedCornerShape(16.dp)
               ) {
                   Image(
                       painter = painterResource(id = R.drawable.mixue),
                       contentDescription = null,
                       contentScale = ContentScale.Crop,
                       modifier = Modifier.fillMaxSize()

                   )
               }
           }
           item {
               Card(
                   modifier = Modifier
                       .width(167.dp)
                       .height(172.dp),
                   shape = RoundedCornerShape(16.dp)
               ) {
                   Image(
                       painter = painterResource(id = R.drawable.kfc),
                       contentDescription = null,
                       contentScale = ContentScale.Crop,
                       modifier = Modifier.fillMaxSize()
                   )
               }
           }
            item {
                Card(
                    modifier = Modifier
                        .width(167.dp)
                        .height(172.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.starbuck),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()

                    )
                }
            }
            item {
                Card(
                    modifier = Modifier
                        .width(167.dp)
                        .height(172.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.maodie),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()

                    )
                }
            }
        }
    }
}



@Composable
fun BottBar() {
   Box(modifier = Modifier.fillMaxWidth()
       .height(100.dp)
       .background(MaterialTheme.colorScheme.surfaceContainerHigh)){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding( 12.dp)
                .align(Alignment.TopCenter)
                ,
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Column(modifier = Modifier,
                verticalArrangement  = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(bottom = 4.dp)
                )
                Text(
                    text ="Home",
                    fontSize = 14.sp
                )}
            Column(modifier = Modifier,
                verticalArrangement  = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    painter = painterResource(id = R.drawable.activity),
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(bottom = 4.dp)
                )
                Text(
                    text ="Activity",
                    fontSize = 14.sp
                )}
            Column(modifier = Modifier,
                verticalArrangement  = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    painter = painterResource(id = R.drawable.message),
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(bottom = 4.dp)
                )
                Text(
                    text ="Message",
                    fontSize = 14.sp
                )}
            Column(modifier = Modifier,
                verticalArrangement  = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    painter = painterResource(id = R.drawable.deliver),
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(bottom = 4.dp)
                )
                Text(
                    text ="delivery",
                    fontSize = 14.sp
                )}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuickMove() {
    A206712_ChenHaojie_Izwan_Lab01Theme{
        HomeScreen()
    }
    }
