package com.example.artspace


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    ImagesWithCreditDetails(modifier = modifier)
}

@Composable
fun ImagesWithCreditDetails(modifier: Modifier) {
    var index by remember { mutableStateOf(1) }
    val maxValue = 4

    val photoPhotoCredit = when(index) {
        1 -> eid3
        2 -> eid5
        3 -> siwesComedy
        else -> jemiWed
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(horizontal = 20.dp)
    ) {
        Spacer(modifier.weight(1f))
        PictureFrame(modifier = modifier, photoCredit = photoPhotoCredit)
        Spacer(modifier.weight(1f))
        PhotoCreditDetails(modifier = modifier, photoCredit = photoPhotoCredit)
        Spacer(modifier.height(16.dp))
        NavigationButton(previous = { index = decrease(index,maxValue) }, next = { index = increase( index, maxValue) })
        Spacer(modifier.height(16.dp))
    }
}

@Composable
fun NavigationButton(previous: ()->Unit, next: ()->Unit) {
    Row {
        CustomButton(title = R.string.previous, callback = previous)
        Spacer(Modifier.width(24.dp))
        CustomButton(title = R.string.next, callback = next)
    }
}

@Composable
fun CustomButton(title: Int, callback: ()->Unit) {
    Button(onClick = callback, Modifier.width(100.dp)) {
        Text(stringResource(title))
    }
}

@Composable
fun PhotoCreditDetails(modifier: Modifier, photoCredit: PhotoPhotoCredit) {
    Column(
        modifier
            .shadow(elevation = .5.dp)
            .padding(20.dp)
    ) {
        Text(stringResource(photoCredit.title))
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            Text(stringResource(photoCredit.photographer))
            Text(" (${stringResource(photoCredit.year)})")
        }
    }
}

@Composable
fun PictureFrame(modifier: Modifier, photoCredit: PhotoPhotoCredit) {
    Image(
        painter = painterResource(photoCredit.photo),
        contentDescription = "background",
        modifier= modifier
            .shadow(elevation = 3.dp)
            .border(width = 2.dp, color = Color.Black)
            .padding(20.dp)
            .wrapContentSize(align = Alignment.Center)

    )
}

@VisibleForTesting
fun increase(value: Int, maxValue: Int): Int {
    return when(value) {
        maxValue -> 1
        else -> value+1
    }
}

@VisibleForTesting
fun decrease(value: Int, maxValue: Int) : Int {
    return when(value) {
        1 -> maxValue
        else -> value-1
    }
}

class PhotoPhotoCredit constructor(val title: Int, val photographer: Int, val year: Int,val photo: Int)

val eid3 = PhotoPhotoCredit(title = R.string.title_one, photographer = R.string.name_one , photo = R.drawable.eid_al_fitr_3, year = R.string.y_2020)
val eid5 = PhotoPhotoCredit(title = R.string.title_two, photographer = R.string.name_two , photo = R.drawable.eid_al_fitr_5, year = R.string.y_2020)
val siwesComedy = PhotoPhotoCredit(title = R.string.title_four, photographer = R.string.name_four , photo = R.drawable.glorious_comedy, year = R.string.y_2020)
val jemiWed = PhotoPhotoCredit(title = R.string.title_three, photographer = R.string.name_three , photo = R.drawable.jemileyin_wedding, year = R.string.y_2020)


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}