package ru.trubin23.listofemployees.employees

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.employeedetail.EmployeeDetailActivity
import ru.trubin23.listofemployees.util.addFragmentToActivity
import javax.inject.Inject

class EmployeesActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(EmployeesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.employees_act)

        setSupportActionBar(findViewById(R.id.toolbar))

        supportFragmentManager.findFragmentById(R.id.content_frame)
            ?: addFragmentToActivity(EmployeesFragment.newInstance(), R.id.content_frame)

        viewModel.openTaskEvent.observe(this, Observer { employeeId ->
            val intent = Intent(this, EmployeeDetailActivity::class.java).apply {
                putExtra(EmployeeDetailActivity.EXTRA_EMPLOYEE_ID, employeeId)
            }
            startActivity(intent)
        })
    }
}
