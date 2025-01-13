package com.example.myapplication1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication1.ui.theme.MyApplication1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MenuApp()
                }
            }
        }
    }
}

@Composable
fun MenuApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomePage(navController) }
        composable("menuList") { MenuList(navController) }
        composable("menuApplication") { MenuApplication(navController) }
        composable("payment") { Payment(navController) }
    }
}

@Composable
fun HomePage(navController: NavController) {
    PageTemplate(
        title = "Home Page",
        buttonTextList = listOf("Menu List", "Menu Application", "Payment"),
        buttonActions = listOf(
            { navController.navigate("menuList") },
            { navController.navigate("menuApplication") },
            { navController.navigate("payment") }
        )
    )
}

@Composable
fun MenuList(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Menu List", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(5) { index ->
                val imageRes = when (index) {
                    0 -> R.drawable.burger
                    1 -> R.drawable.pizza
                    2 -> R.drawable.pasta
                    3 -> R.drawable.sushi
                    4 -> R.drawable.salad
                    else -> R.drawable.sample_food
                }
                val name = when (index) {
                    0 -> "Burger"
                    1 -> "Pizza"
                    2 -> "Pasta"
                    3 -> "Sushi"
                    4 -> "Salad"
                    else -> "Food $index"
                }
                val price = "\$${5 + index}.99"
                MenuItem(name, imageRes, price)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Go Back")
        }
    }
}

@Composable
fun MenuItem(name: String, imageRes: Int, price: String) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = name, style = MaterialTheme.typography.bodyLarge)
                Text(text = price, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}

@Composable
fun MenuApplication(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Order Menu", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(5) { index ->
                val name = when (index) {
                    0 -> "Burger"
                    1 -> "Pizza"
                    2 -> "Pasta"
                    3 -> "Sushi"
                    4 -> "Salad"
                    else -> "Food $index"
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = name, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
                    Button(onClick = { /* Handle Order */ }) {
                        Text(text = "Order")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Go Back")
        }
    }
}

@Composable
fun Payment(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Payment Methods", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))

        val paymentMethods = listOf("Credit Card", "PayPal", "Cash")

        paymentMethods.forEach { method ->
            Button(
                onClick = { navController.navigate("payment") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = method)
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Go Back")
        }
    }
}

@Composable
fun PageTemplate(title: String, buttonTextList: List<String>, buttonActions: List<() -> Unit>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(16.dp))

        Spacer(modifier = Modifier.height(16.dp))

        buttonTextList.forEachIndexed { index, buttonText ->
            Button(
                onClick = buttonActions[index],
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = buttonText)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplication1Theme {
MenuApp()
    }
}