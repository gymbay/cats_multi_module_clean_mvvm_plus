package com.example.feature_cats_list.list.di

import com.example.feature_cats_list.list.ui.CatsListViewModel
import dagger.Subcomponent

@Subcomponent
internal interface CatsListSubcomponent {

    val viewModel: CatsListViewModel

}