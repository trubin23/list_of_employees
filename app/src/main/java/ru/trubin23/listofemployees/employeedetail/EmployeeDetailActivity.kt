package ru.trubin23.listofemployees.employeedetail

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.util.addFragmentToActivity
import javax.inject.Inject


class EmployeeDetailActivity : DaggerAppCompatActivity() {

    private var phoneNumber: String? = null

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProviders.of(this, factory).get(EmployeeDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.employee_detail_act)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        supportFragmentManager.findFragmentById(R.id.content_frame)
            ?: EmployeeDetailFragment.newInstance(intent.getStringExtra(EXTRA_EMPLOYEE_ID)).let {
                addFragmentToActivity(it, R.id.content_frame)
            }

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
        }

        viewModel.makeCallEvent.observe(this, Observer { phoneNumber ->
            this.phoneNumber = phoneNumber
            makeCall()
        })
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        phoneNumber = savedInstanceState?.getString(PHONE_NUMBER)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        phoneNumber?.let { outState.putString(PHONE_NUMBER, it) }
        super.onSaveInstanceState(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun makeCall() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermission()
        } else {
            phoneNumber?.let {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$it"))
                startActivity(intent)
            }
        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.CALL_PHONE),
            PERMISSIONS_REQUEST_CALL_PHONE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode != PERMISSIONS_REQUEST_CALL_PHONE || grantResults.isEmpty()) {
            return
        }

        when (grantResults[0]) {
            PackageManager.PERMISSION_GRANTED -> {
                makeCall()
            }
            PackageManager.PERMISSION_DENIED -> {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                    Toast.makeText(themedContext, R.string.request_permission_call_phone, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    companion object {

        private const val PHONE_NUMBER = "PHONE_NUMBER"

        const val EXTRA_EMPLOYEE_ID = "EMPLOYEE_ID"

        private const val PERMISSIONS_REQUEST_CALL_PHONE = 1

    }
}
