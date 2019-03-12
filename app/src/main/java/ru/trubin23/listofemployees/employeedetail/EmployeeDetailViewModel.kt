package ru.trubin23.listofemployees.employeedetail

import androidx.annotation.StringRes
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.SingleLiveEvent
import ru.trubin23.listofemployees.data.EducationPeriod
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.data.source.EmployeesRepository
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class EmployeeDetailViewModel(
    private val employeesRepository: EmployeesRepository
) : ViewModel() {

    val employee = ObservableField<Employee>()

    val educationPeriod = ObservableField<String>()

    var employeeDisposable: Disposable? = null

    val makeCallEvent = SingleLiveEvent<String>()

    val snackbarMessage = SingleLiveEvent<Int>()

    fun start(employeeId: String) {
        employeeDisposable = employeesRepository
            .getEmployeeById(employeeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { value ->
                    employee.set(value)
                    educationPeriod.set(educationPeriodToString(value.educationPeriod))
                },
                { showSnackbarMessage(R.string.employee_by_id_error) },
                { showSnackbarMessage(R.string.employee_by_id_not_found) })
    }

    private fun showSnackbarMessage(@StringRes message: Int) {
        snackbarMessage.value = message
    }

    private fun educationPeriodToString(educationPeriod: EducationPeriod): String {
        return "${updateDateString(educationPeriod.start)} - ${updateDateString(educationPeriod.end)}"
    }

    private fun updateDateString(time: String): String {
        try {
            synchronized(inputDateFormat) {
                val date = inputDateFormat.parse(time)
                return outputDateFormat.format(date)
            }
        } catch (ignore: ParseException) {
        }
        return ""
    }


    override fun onCleared() {
        super.onCleared()
        employeeDisposable?.dispose()
    }

    companion object {
        private val inputDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
        private val outputDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    }
}