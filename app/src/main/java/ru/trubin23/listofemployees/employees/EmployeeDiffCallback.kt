package ru.trubin23.listofemployees.employees

import androidx.recyclerview.widget.DiffUtil
import ru.trubin23.listofemployees.data.Employee

class EmployeeDiffCallback : DiffUtil.ItemCallback<Employee>() {

    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.height == newItem.height &&
                oldItem.phone == newItem.phone
    }
}