package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ImageWithTitleSlide( modifier: Modifier ) {
    var index by remember { mutableStateOf(1) }

    val imageResource = when(index) {
        1 -> eid3
        2 -> eid5
        3 -> siwesComedy
        else -> jemiWed
    }

    val nextLogic = {
        index = when(index) {
            4 -> 1
            else -> index+1
        }
    }

    val previousLogic = {
        index = when(index) {
            1 -> 4
            else -> index-1
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
            .fillMaxHeight()
    ) {
        Spacer(modifier.weight(1f))
        PictureFrame(imageResource.photo, modifier)
        Spacer(modifier.weight(1f))
        TitleWithPhotoCreditBox(
            title = imageResource.title,
            photographer = imageResource.photographer,
            year = imageResource.year
        )
        Spacer(modifier.height(16.dp))
        NavigatorsButton(
            previousLogic = previousLogic,
            nextLogic = nextLogic,
            modifier = modifier
        )
        Spacer(modifier.height(16.dp))
    }
}

@Composable
fun PictureFrame(pictureIndex: Int,modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .border(width = 2.dp, color = Color.Black)
            .wrapContentSize(align = Alignment.Center)
            .shadow(elevation = 3.dp)
    ) {
        Image(
            painter = painterResource(pictureIndex),
            contentDescription =" ",
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
fun TitleWithPhotoCreditBox(title: String, photographer: String, year: Int) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .shadow(elevation = .5.dp)
                .padding(20.dp)

        ) {
            Text(title, fontSize = 24.sp, fontWeight = FontWeight.Light)
            Row {
                Text("$photographer ", fontWeight = FontWeight.Bold)
                Text("($year)", fontWeight = FontWeight.Light)
            }

        }
}

@Composable
fun NavigatorsButton(previousLogic: ()->Unit, nextLogic: ()->Unit, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        NavigatorButton("Previous", previousLogic, modifier = modifier)
        Spacer(Modifier.width(24.dp))
        NavigatorButton("Next", nextLogic, modifier = modifier)
    }
}

@Composable
fun NavigatorButton(title: String, callback: ()->Unit, modifier: Modifier ) {
    Button(
        onClick = callback,
        modifier = modifier
    ) {
        Text(title, fontSize = 20.sp, modifier = modifier
            .width(100.dp)
            .wrapContentSize(align = Alignment.Center))
    }
}

class PhotoPhotoCredit constructor(val title: String,val photographer: String,val year: Int,val photo: Int)

val eid3 = PhotoPhotoCredit(title = "Eid al Fitr Ife", photographer = "can't remember" , photo = R.drawable.eid_3, year = 2020)
val eid5 = PhotoPhotoCredit(title = "Eid al Fitr Ife", photographer = "Akin's money" , photo = R.drawable.eid_5, year = 2020)
val siwesComedy = PhotoPhotoCredit(title = "Glorious Comedy", photographer = "Hussein kH" , photo = R.drawable.siwes_comedy, year = 2020)
val jemiWed = PhotoPhotoCredit(title = "Jemileyin's wedding", photographer = "Hussein kH" , photo = R.drawable.jemleyin_wedding, year = 2020)

@Preview(showBackground = true)
@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    ImageWithTitleSlide(modifier = modifier)
}