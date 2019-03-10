package ru.trubin23.listofemployees.employeedetail

import androidx.annotation.StringRes
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.SingleLiveEvent
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.data.source.EmployeesRepository

class EmployeeDetailViewModel(
    private val employeesRepository: EmployeesRepository
) : ViewModel() {

    val employee = ObservableField<Employee>()

    var employeeDisposable: Disposable? = null

    val makeCallEvent = SingleLiveEvent<String>()
    val snackbarMessage = SingleLiveEvent<Int>()

    fun start(employeeId: String) {
        employeeDisposable = employeesRepository
            .getEmployeeById(employeeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value -> employee.set(value) },
                { showSnackbarMessage(R.string.employee_by_id_error) },
                { showSnackbarMessage(R.string.employee_by_id_not_found) })
    }

    private fun showSnackbarMessage(@StringRes message: Int) {
        snackbarMessage.value = message
    }

    override fun onCleared() {
        super.onCleared()
        employeeDisposable?.dispose()
    }
}