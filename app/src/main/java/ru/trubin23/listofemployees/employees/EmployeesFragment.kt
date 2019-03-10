package ru.trubin23.listofemployees.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.databinding.EmployeesFragBinding

class EmployeesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewDataBinding = EmployeesFragBinding.inflate(inflater, container, false).apply {
            viewmodel = (activity as EmployeesActivity).obtainViewModel()
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

            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = RecyclerView.VERTICAL

            viewDataBinding.employeesList.layoutManager = linearLayoutManager
            viewDataBinding.employeesList.adapter = adapter
        }

        return viewDataBinding.root
    }

    private fun showSnackbarError() {
        view?.let {
            val text = it.context.getString(R.string.loading_employees_error)
            Snackbar.make(it, text, Snackbar.LENGTH_LONG).show()
        }
    }

    companion object {
        fun newInstance() = EmployeesFragment()
    }
}