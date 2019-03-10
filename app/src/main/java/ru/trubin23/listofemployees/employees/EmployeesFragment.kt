package ru.trubin23.listofemployees.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.databinding.EmployeesFragBinding
import ru.trubin23.listofemployees.util.showSnackbar


class EmployeesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewDataBinding = EmployeesFragBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as EmployeesActivity).obtainViewModel()

            lifecycleOwner = this@EmployeesFragment
        }

        viewDataBinding.viewmodel?.let {

            val adapter = EmployeesAdapter(EmployeeDiffCallback(), it)

            it.pagedListLiveDataSingle
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { pagedListLiveData ->
                        viewDataBinding.progressBar.visibility = View.GONE
                        pagedListLiveData.observe(this, Observer { pagedList ->
                            adapter.submitList(pagedList)
                        })
                    },
                    {
                        viewDataBinding.progressBar.visibility = View.GONE
                        showSnackbarError()
                    })

            val dividerItemDecoration = DividerItemDecoration(context, LinearLayout.VERTICAL)
            viewDataBinding.employeesList.addItemDecoration(dividerItemDecoration)
            viewDataBinding.employeesList.layoutManager = LinearLayoutManager(context)
            viewDataBinding.employeesList.adapter = adapter
        }

        return viewDataBinding.root
    }

    private fun showSnackbarError() {
        view?.apply {
            val text = context.getString(R.string.loading_employees_error)
            showSnackbar(text, Snackbar.LENGTH_LONG)
        }
    }

    companion object {
        fun newInstance() = EmployeesFragment()
    }
}