package com.example.lemonade

import android.os.Bundle
import android.provider.CalendarContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}
@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    Scaffold (topBar = {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF9E44c))
                .padding(16.dp)
                .statusBarsPadding(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Lemonade",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            when (currentStep) {
                1 -> LemonadeDisplay(
                    R.string.lemon_select,
                    R.drawable.lemon_tree,
                    R.string.lemon_tree_content_description,
                    onButtonClick = {
                        currentStep = 2
                        squeezeCount = (2..4).random()
                    }
                )

                2 -> LemonadeDisplay(
                    R.string.lemon_squeeze,
                    R.drawable.lemon_squeeze,
                    R.string.lemon_squeeze_content_description,
                    onButtonClick = {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            currentStep = 3
                        }
                    }
                )

                3 -> LemonadeDisplay(
                    R.string.lemon_juice,
                    R.drawable.lemon_drink,
                    R.string.lemon_juice_content_description,
                    onButtonClick = { currentStep = 4 }
                )

                4 -> LemonadeDisplay(
                    R.string.empty_glass,
                    R.drawable.lemon_restart,
                    R.string.empty_glass_content_description,
                    onButtonClick = { currentStep = 1 }
                )
            }
        }
    }
}
@Composable
fun LemonadeDisplay (
    textResourceId: Int,
    imageResourceId: Int,
    contentDescriptionResourceId: Int,
    onButtonClick: ()-> Unit,
    modifier: Modifier = Modifier
) {
   Column (modifier = modifier.fillMaxSize(),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center
   ){
       Button(
           onClick = onButtonClick,
           colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3ECD2))
       ) {
           Image(
               painter = painterResource(imageResourceId),
               contentDescription = stringResource(contentDescriptionResourceId),
               modifier= Modifier
                   .height(160.dp)
                   .padding(16.dp)
                   .width(128.dp)
           )
       }
       Spacer(modifier = Modifier.height(16.dp))
       Text(text = stringResource(textResourceId))
   }
  }


@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}