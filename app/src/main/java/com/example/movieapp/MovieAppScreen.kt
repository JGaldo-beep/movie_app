package com.example.movieapp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.ui.MovieDetailScreen
import com.example.movieapp.ui.MovieListScreen
import com.example.movieapp.ui.MovieOnLiveScreen

sealed class MovieAppScreen(val route: String) {
    object ListScreen : MovieAppScreen("list")
    object DetailScreen : MovieAppScreen("detail/{movieId}") {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
    object OnLiveScreen : MovieAppScreen("onlive")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieApp(
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val screenTitle = getCurrentScreenTitle(currentRoute)

    Scaffold (
        topBar = {
            MovieAppBar(
                titleRes = screenTitle,
                canNavigateBack = navController.previousBackStackEntry != null,
                modifier = Modifier,
                onBackClick = { navController.popBackStack() }
            )
        }
    ) { innerPadding ->
        Surface (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            NavHost(
                navController = navController,
                startDestination = MovieAppScreen.ListScreen.route,
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                composable(route = MovieAppScreen.ListScreen.route) {
                    MovieListScreen (
                        onMovieClick = { movieId ->
                            navController.navigate(MovieAppScreen.DetailScreen.createRoute(movieId))
                        }
                    )
                }

                composable(
                    route = MovieAppScreen.DetailScreen.route,
                    arguments = listOf(navArgument("movieId") { type = NavType.IntType } )
                ) { backStackEntry ->
                    val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
                    MovieDetailScreen(
                        movieId = movieId,
                        onWatchClick = {
                            navController.navigate(MovieAppScreen.OnLiveScreen.route)
                        }
                    )
                }

                composable(route = MovieAppScreen.OnLiveScreen.route) {
                    MovieOnLiveScreen(
                        onBackHome = {
                            navController.popBackStack(MovieAppScreen.ListScreen.route, inclusive = false)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieAppBar(
    @StringRes titleRes: Int,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = titleRes)
            )
        },
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorScheme.primaryContainer
        ),
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = onBackClick
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_arrow)
                    )
                }
            }
        }
    )
}

@Composable
fun getCurrentScreenTitle(route: String?): Int {
    return when {
        route == null -> R.string.list_screen
        route.startsWith("list") -> R.string.list_screen
        route.startsWith("detail") -> R.string.detail_screen
        route.startsWith("onlive") -> R.string.on_live_screen
        else -> R.string.app_name
    }
}