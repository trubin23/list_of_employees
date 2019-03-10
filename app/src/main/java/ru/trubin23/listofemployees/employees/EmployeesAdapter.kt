package ru.trubin23.listofemployees.employees

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.data.Employee
import ru.trubin23.listofemployees.databinding.EmployeeItemBinding


class EmployeesAdapter(diffCallback: DiffUtil.ItemCallback<Employee>,
                       private val employeesViewModel: EmployeesViewModel) :
    PagedListAdapter<Employee, EmployeesAdapter.EmployeeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding: EmployeeItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.employee_item, parent, false
        )

        binding.listener = object :EmployeeItemListener{

            override fun onEmployeeClicked(employeeId: String) {
                employeesViewModel.openTaskEvent.value = employeeId
            }
        }

        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        with(holder.binding) {
            employee = getItem(position)
            executePendingBindings()
        }
    }

    class EmployeeViewHolder(val binding: EmployeeItemBinding) : RecyclerView.ViewHolder(binding.root)
}