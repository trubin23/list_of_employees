package ru.trubin23.listofemployees.employeedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.util.addFragmentToActivity
import ru.trubin23.listofemployees.util.obtainViewModel

class EmployeeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.employee_detail_act)

        supportFragmentManager.findFragmentById(R.id.content_frame)
            ?: EmployeeDetailFragment.newInstance(intent.getStringExtra(EXTRA_EMPLOYEE_ID)).let {
                addFragmentToActivity(it, R.id.content_frame)
            }
    }

    fun obtainViewModel(): EmployeeDetailViewModel = obtainViewModel(EmployeeDetailViewModel::class.java)

    companion object {

        const val EXTRA_EMPLOYEE_ID = "EMPLOYEE_ID"

    }
}
