package ru.trubin23.listofemployees.employees

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.util.addFragmentToActivity
import ru.trubin23.listofemployees.util.obtainViewModel

class EmployeesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.employees_act)

        supportFragmentManager.findFragmentById(R.id.content_frame)
            ?: EmployeesFragment.newInstance().let {
                addFragmentToActivity(it, R.id.content_frame)
            }
    }

    fun obtainViewModel(): EmployeesViewModel = obtainViewModel(EmployeesViewModel::class.java)
}
