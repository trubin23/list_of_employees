package ru.trubin23.listofemployees.employees

import androidx.annotation.StringRes
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
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
import ru.trubin23.listofemployees.data.source.EmployeesDataSource
import javax.inject.Inject


class EmployeesViewModel @Inject constructor(
    private val employeesDataSource: EmployeesDataSource
) : ViewModel() {

    val openTaskEvent = SingleLiveEvent<String>()

    val refreshing = ObservableBoolean(false)

    val snackbarMessage = SingleLiveEvent<Int>()

    val loading = ObservableBoolean(true)

    var dataEmployeesDisposable: Disposable? = null

    val dataEmployees = SingleLiveEvent<LiveData<PagedList<Employee>>>()

    val invalidDataEmployees = SingleLiveEvent<Void?>()

    fun loadData() = loadData(false, "")

    private fun loadData(forceUpdate: Boolean, searchLine: String  = "") {
        dataEmployeesDisposable?.dispose()
        dataEmployeesDisposable = employeesDataSource
            .getEmployees(forceUpdate, searchLine)
            .subscribeOn(Schedulers.io())
            .map { sourceFactory ->
                sourceFactory.toLiveData(pageSize = PAGE_SIZE)
            }
            .doOnSubscribe { invalidDataEmployees.value = null }
            .doFinally {
                refreshing.set(false)
                loading.set(false)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { pagedListLiveData -> dataEmployees.value = pagedListLiveData },
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
        loadData(true, "")
    }

    override fun onCleared() {
        super.onCleared()
        dataEmployeesDisposable?.dispose()
    }

    fun queryTextChange(text: String) {
        loadData(false, text)
    }

    companion object {
        private const val PAGE_SIZE = 50
    }
}