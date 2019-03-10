package ru.trubin23.listofemployees.employees

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import io.reactivex.Single
import ru.trubin23.listofemployees.SingleLiveEvent
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.data.source.EmployeesRepository

class EmployeesViewModel(
    private val employeesRepository: EmployeesRepository
) : ViewModel() {

    val pagedListLiveDataSingle: Single<LiveData<PagedList<Employee>>>

    val openTaskEvent = SingleLiveEvent<String>()

    init {
        pagedListLiveDataSingle =
            employeesRepository
                .getEmployees()
                .map { sourceFactory -> sourceFactory.toLiveData(pageSize = 50) }
    }
}