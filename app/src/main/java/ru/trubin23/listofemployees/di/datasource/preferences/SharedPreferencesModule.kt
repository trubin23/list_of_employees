package ru.trubin23.listofemployees.di.datasource.preferences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.trubin23.listofemployees.di.ContextModule

@Module(includes = [ContextModule::class])
class SharedPreferencesModule {

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        val preferencesName = "employees_preferences"
        return context.getSharedPreferences(preferencesName, MODE_PRIVATE)
    }
}