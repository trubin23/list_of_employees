package ru.trubin23.listofemployees.employees

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.databinding.EmployeesFragBinding
import ru.trubin23.listofemployees.util.setupSnackbar
import javax.inject.Inject


class EmployeesFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(EmployeesViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewDataBinding = EmployeesFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel

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

        setHasOptionsMenu(true)

        return viewDataBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.searchBar)

        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("newText", ": $newText")
                viewModel.queryTextChange(newText ?: "")
                return true
            }
        })
        searchView.queryHint = getString(R.string.search_hint)
        searchView.setIconifiedByDefault(false)
    }

    companion object {
        fun newInstance() = EmployeesFragment()
    }
}