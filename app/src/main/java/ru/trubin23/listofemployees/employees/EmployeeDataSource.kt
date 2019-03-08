package ru.trubin23.listofemployees.employees

import androidx.paging.PositionalDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.data.source.EmployeesRepository

class EmployeeDataSource(private val employeesRepository: EmployeesRepository) : PositionalDataSource<Employee>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Employee>) {
        employeesRepository
            .getEmployees()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { employeeList ->
                run {
                    callback.onResult(employeeList, 0)
                }
            }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Employee>) {
    }
}