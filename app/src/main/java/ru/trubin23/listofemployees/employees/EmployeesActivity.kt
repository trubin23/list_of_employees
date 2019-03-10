package ru.trubin23.listofemployees.employees

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.employeedetail.EmployeeDetailActivity
import ru.trubin23.listofemployees.util.addFragmentToActivity
import ru.trubin23.listofemployees.util.obtainViewModel

class EmployeesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.employees_act)

        supportFragmentManager.findFragmentById(R.id.content_frame)
            ?: addFragmentToActivity(EmployeesFragment.newInstance(), R.id.content_frame)

        obtainViewModel().openTaskEvent.observe(this, Observer { employeeId ->
            val intent = Intent(this, EmployeeDetailActivity::class.java).apply {
                putExtra(EmployeeDetailActivity.EXTRA_EMPLOYEE_ID, employeeId)
            }
            startActivity(intent)
        })
    }

    fun obtainViewModel(): EmployeesViewModel = obtainViewModel(EmployeesViewModel::class.java)
}
