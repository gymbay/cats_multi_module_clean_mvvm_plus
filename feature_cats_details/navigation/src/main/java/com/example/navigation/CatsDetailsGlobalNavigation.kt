package com.example.navigation

import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.example.feature_cats_details.navigation.R

object ARGS {
    const val CAT_ID = "CatId"
}

fun Fragment.navigateToCatDetails(catId: String) {
    val navController = findNavController()
    val navRoute = getString(R.string.navigation_feature_cats_details_deeplink)
    val navUri = navRoute.replace("{${ARGS.CAT_ID}}", catId).toUri()

    val request = NavDeepLinkRequest.Builder
        .fromUri(navUri)
        .build()
    navController.navigate(request)
}