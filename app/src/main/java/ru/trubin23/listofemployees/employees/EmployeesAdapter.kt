package ru.trubin23.listofemployees.employees

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.data.Employee

class EmployeesAdapter(diffCallback: DiffUtil.ItemCallback<Employee>) :
    PagedListAdapter<Employee, EmployeesAdapter.EmployeeViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.employee_item, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Employee?) {
            item ?: return

            itemView.findViewById<TextView>(R.id.name).text = item.name
            itemView.findViewById<TextView>(R.id.height).text = item.height.toString()
            itemView.findViewById<TextView>(R.id.phone).text = item.phone
        }
    }
}