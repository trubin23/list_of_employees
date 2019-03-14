package ru.trubin23.listofemployees.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.data.source.EmployeesDataSource
import ru.trubin23.listofemployees.databinding.EmployeesFragBinding
import ru.trubin23.listofemployees.util.setupSnackbar
import javax.inject.Inject


class EmployeesFragment : DaggerFragment() {

    @Inject
    lateinit var employeesDataSource: EmployeesDataSource

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewDataBinding = EmployeesFragBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as EmployeesActivity).obtainViewModel()

            viewmodel?.let {
                root.setupSnackbar(this@EmployeesFragment, it.snackbarMessage, Snackbar.LENGTH_LONG)

                val adapter = EmployeesAdapter(EmployeeDiffCallback(), it)

                var liveResults: LiveData<PagedList<Employee>>? = null

                it.invalidDataEmployees.observe(this@EmployeesFragment, Observer {
                    liveResults?.removeObservers(this@EmployeesFragment)
                })

                it.dataEmployees.observe(this@EmployeesFragment, Observer { pagedListLiveData ->
                    liveResults = pagedListLiveData
                    pagedListLiveData.observe(this@EmployeesFragment, Observer { pagedList ->
                        adapter.submitList(pagedList)
                    })
                })

                val dividerItemDecoration = DividerItemDecoration(context, LinearLayout.VERTICAL)
                employeesList.addItemDecoration(dividerItemDecoration)
                employeesList.layoutManager = LinearLayoutManager(context)
                employeesList.adapter = adapter

                it.loadData()
            }
        }

        return viewDataBinding.root
    }

    companion object {
        fun newInstance() = EmployeesFragment()
    }
}