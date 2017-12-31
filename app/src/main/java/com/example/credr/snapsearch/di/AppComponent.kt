package com.example.credr.snapsearch.di

import android.app.Application
import com.example.credr.snapsearch.SnapSearchApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by punitdama on 31/12/17.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class,
        RepositoryModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class))
interface AppComponent : AndroidInjector<SnapSearchApplication>{

    @Component.Builder
    interface Builder{
        fun build() : AppComponent

        @BindsInstance
        fun application(app : Application) : AppComponent.Builder
    }
}