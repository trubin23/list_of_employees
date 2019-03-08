package ru.trubin23.listofemployees.employees

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.data.source.EmployeesRepository
import java.util.concurrent.Executors

class EmployeesViewModel(
    private val employeesRepository: EmployeesRepository
) : ViewModel() {

    val pagedListLiveData: LiveData<PagedList<Employee>>

    init {
        val employeesSourceFactory = EmployeesSourceFactory(employeesRepository)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        pagedListLiveData = LivePagedListBuilder(employeesSourceFactory, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()
    }

}