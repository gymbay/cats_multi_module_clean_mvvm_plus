package com.example.feature_cats_details.details.ui.converters

import android.content.res.Resources
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.example.domain_models.response.CatModel
import com.example.feature_cats_details.R
import com.example.feature_cats_details.details.ui.models.CatDetailsModel

internal class CatUiConverter(
    private val resources: Resources
) {

    private val noInfo: String by lazy {
        "<${resources.getString(R.string.feature_cat_details_no_info)}>"
    }

    fun convertTo(cat: CatModel): CatDetailsModel {
        val breed = cat.breeds?.firstOrNull()

        return CatDetailsModel(
            cat.url,
            getString(breed?.name),
            getString(breed?.weight?.metric, R.string.feature_cat_label_weight),
            getString(breed?.temperament, R.string.feature_cat_label_temperament),
            getString(breed?.origin, R.string.feature_cat_label_origin),
            getString(breed?.lifeSpan, R.string.feature_cat_label_life_span),
            getString(breed?.description),
        )
    }

    private fun getString(text: String?, @StringRes res: Int? = null): String {
        val newText = text ?: noInfo
        return if (res != null) resources.getString(res, newText) else newText
    }

}