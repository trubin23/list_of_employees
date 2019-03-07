package ru.trubin23.listofemployees.employees

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.util.Injection

class EmployeesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Injection.provideEmployeesRepository(this)
        repository
            .getEmployees()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { employeeList ->
                run {
                    Log.d("employees", employeeList.size.toString())
                }
            }
    }
}
