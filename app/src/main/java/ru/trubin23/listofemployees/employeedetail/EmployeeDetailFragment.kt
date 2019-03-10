package ru.trubin23.listofemployees.employeedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.trubin23.listofemployees.databinding.EmployeeDetailFragBinding
import ru.trubin23.listofemployees.util.setupSnackbar

class EmployeeDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewDataBinding = EmployeeDetailFragBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as EmployeeDetailActivity).obtainViewModel()

            lifecycleOwner = this@EmployeeDetailFragment

            viewmodel?.let {
                root.setupSnackbar(this@EmployeeDetailFragment, it.snackbarMessage, Snackbar.LENGTH_LONG)
            }

            listener = object : EmployeePhoneListener {
                override fun onEmployeePhoneClicked(phone: String) {
                    viewmodel?.makeCallEvent?.value = phone
                }
            }
        }

        arguments?.getString(ARGUMENT_EMPLOYEE_ID)?.let {
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