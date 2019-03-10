package ru.trubin23.listofemployees.employees

import android.util.Log
import androidx.annotation.StringRes
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.SingleLiveEvent
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.data.source.EmployeesRepository

class EmployeesViewModel(
    private val employeesRepository: EmployeesRepository
) : ViewModel() {

    val openTaskEvent = SingleLiveEvent<String>()

    val refreshing = ObservableBoolean(false)

    val snackbarMessage = SingleLiveEvent<Int>()

    val loading = ObservableBoolean(true)

    var dataEmployeesDisposable: Disposable? = null

    val dataEmployees = SingleLiveEvent<LiveData<PagedList<Employee>>>()

    fun loadData() = loadData(true, false)

    private fun loadData(firstUpdate: Boolean, forceUpdate: Boolean) {
        dataEmployeesDisposable = employeesRepository
            .getEmployees(forceUpdate)
            .subscribeOn(Schedulers.io())
            .map { sourceFactory ->
                sourceFactory.toLiveData(pageSize = PAGE_SIZE)
            }
            .doFinally {
                refreshing.set(false)
                loading.set(false)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { pagedListLiveData ->
                    if (firstUpdate) {
                        dataEmployees.value = pagedListLiveData
                    }
                },
                { showSnackbarMessage(R.string.loading_employees_error) })
    }

    private fun showSnackbarMessage(@StringRes message: Int) {
        snackbarMessage.value = message
    }

    fun refreshEmployees() {
        if (loading.get() || refreshing.get()) {
            return
        }

        refreshing.set(true)
        loadData(false, true)
    }

    override fun onCleared() {
        super.onCleared()
        dataEmployeesDisposable?.dispose()
    }

    companion object {
        private const val PAGE_SIZE = 50
    }
}