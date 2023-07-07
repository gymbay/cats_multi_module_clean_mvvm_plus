package com.example.core_android.providers

import android.content.res.Resources
import androidx.annotation.StringRes
import javax.inject.Inject

interface StringProvider {

    fun getString(@StringRes resId: Int, args: Array<Any> = emptyArray()): String

}

class StringProviderImpl @Inject constructor(
    private val resources: Resources
) : StringProvider {

    override fun getString(resId: Int, args: Array<Any>): String {
        return resources.getString(resId, *args)
    }

}