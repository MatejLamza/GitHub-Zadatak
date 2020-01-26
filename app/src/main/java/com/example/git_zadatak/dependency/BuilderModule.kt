package com.example.git_zadatak.dependency

import com.example.git_zadatak.view.ui.HomeActivity
import com.example.git_zadatak.view.ui.OwnerDetailsActivity
import com.example.git_zadatak.viewmodels.modules.HomeModule
import com.example.git_zadatak.viewmodels.modules.OwnerDetailsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {
    @ContributesAndroidInjector(modules = arrayOf(HomeModule::class))
    abstract fun bindsHomeActivity():HomeActivity

    @ContributesAndroidInjector(modules = arrayOf(OwnerDetailsModule::class))
    abstract fun bindsOwnerDetailsActivity():OwnerDetailsActivity
}