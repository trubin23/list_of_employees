package ru.trubin23.listofemployees.employeedetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.trubin23.listofemployees.databinding.EmployeeDetailFragBinding

class EmployeeDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewDataBinding = EmployeeDetailFragBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as EmployeeDetailActivity).obtainViewModel()
        }

        Log.d("EmployeeDetailViewModel", "request")
        arguments?.getString(ARGUMENT_EMPLOYEE_ID)?.let {
            Log.d("EmployeeDetailViewModel", "start")
            viewDataBinding.viewmodel?.start(it)
        }

        return viewDataBinding.root
    }

    companion object {

        private const val ARGUMENT_EMPLOYEE_ID = "EMPLOYEE_ID"

        fun newInstance(taskId: String?) = EmployeeDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARGUMENT_EMPLOYEE_ID, taskId)
            }
        }
    }
}