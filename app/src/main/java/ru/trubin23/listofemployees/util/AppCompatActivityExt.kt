package ru.trubin23.listofemployees.util

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import ru.trubin23.listofemployees.ViewModelFactory


fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, @IdRes frameId: Int) {
    supportFragmentManager.transaction {
        add(frameId, fragment)
    }
}

private fun FragmentManager.transaction(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>): T {
    return ViewModelProviders
        .of(this, ViewModelFactory.getInstance(application))
        .get(viewModelClass)
}