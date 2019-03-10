package ru.trubin23.listofemployees

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.trubin23.listofemployees.data.source.EmployeesRepository
import ru.trubin23.listofemployees.employeedetail.EmployeeDetailViewModel
import ru.trubin23.listofemployees.employees.EmployeesViewModel
import ru.trubin23.listofemployees.util.Injection

class ViewModelFactory private constructor(
    private val employeesRepository: EmployeesRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(EmployeesViewModel::class.java) ->
                    EmployeesViewModel(employeesRepository)
                isAssignableFrom(EmployeeDetailViewModel::class.java) ->
                    EmployeeDetailViewModel(employeesRepository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
    }

    companion object {

        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory {
            return INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(
                    Injection.provideEmployeesRepository(application.applicationContext)
                )
                    .also { INSTANCE = it }
            }
        }
    }
}