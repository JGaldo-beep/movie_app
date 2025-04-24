package com.example.movieapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.data.Resource
import com.example.movieapp.ui.theme.Typography

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieDetailScreen(
    movieId: Int = 0,
    onWatchClick: () -> Unit = {}
) {
    val movie = Resource.allMovies[movieId]

    Column (
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Box (
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .height(224.dp)
        ) {
            Image(
                painter = painterResource(id = movie.imageResId),
                contentDescription = "Image Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Text(
            text = stringResource(id = movie.title),
            style = Typography.displayMedium,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = movie.genre),
                style = Typography.titleLarge
            )
            Text(
                text = stringResource(id = R.string.circle_black),
                style = Typography.bodySmall
            )
            Text(
                text = stringResource(id = movie.year),
                style = Typography.titleLarge
            )
        }

        HorizontalDivider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Text(
            text = stringResource(id = movie.description),
            style = Typography.headlineMedium
        )

        Button(
            onClick = onWatchClick,
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth(),
            shape = shapes.medium
        ) {
            Text (
                text = stringResource(id = R.string.watch_now)
            )
        }
    }
}