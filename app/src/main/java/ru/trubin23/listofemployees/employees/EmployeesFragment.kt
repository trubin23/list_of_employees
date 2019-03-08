package ru.trubin23.listofemployees.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.trubin23.listofemployees.databinding.EmployeesFragBinding

class EmployeesFragment : Fragment() {

    private lateinit var viewDataBinding: EmployeesFragBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = EmployeesFragBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as EmployeesActivity).obtainViewModel()
        }

        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            val adapter = EmployeesAdapter(EmployeeDiffCallback())

            viewModel.pagedListLiveData.observe(this, Observer { pagedList ->
                run {
                    adapter.submitList(pagedList)
                }
            })

            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = RecyclerView.VERTICAL

            viewDataBinding.employeesList.layoutManager = linearLayoutManager
            viewDataBinding.employeesList.adapter = adapter
        }

        return viewDataBinding.root
    }

    companion object {
        fun newInstance() = EmployeesFragment()
    }
}