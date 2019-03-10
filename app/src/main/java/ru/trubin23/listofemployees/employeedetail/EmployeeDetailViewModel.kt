package ru.trubin23.listofemployees.employeedetail

import android.annotation.SuppressLint
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.data.source.EmployeesRepository

class EmployeeDetailViewModel(
    private val employeesRepository: EmployeesRepository
) : ViewModel() {

    val employee = ObservableField<Employee>()

    @SuppressLint("CheckResult")
    fun start(employeeId: String) {
        employeesRepository
            .getEmployeeById(employeeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    value -> employee.set(value)

                    Log.d("EmployeeDetailViewModel", "success")
                },
                {
                    Log.d("EmployeeDetailViewModel", "error")
                    //TODO: add error message
                })
    }
}