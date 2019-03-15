package ru.trubin23.listofemployees.util

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


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