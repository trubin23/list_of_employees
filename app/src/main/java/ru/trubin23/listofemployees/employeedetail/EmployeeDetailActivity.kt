package ru.trubin23.listofemployees.employeedetail

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import ru.trubin23.listofemployees.R
import ru.trubin23.listofemployees.util.addFragmentToActivity
import ru.trubin23.listofemployees.util.obtainViewModel


class EmployeeDetailActivity : AppCompatActivity() {

    private var phoneNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.employee_detail_act)

        supportFragmentManager.findFragmentById(R.id.content_frame)
            ?: EmployeeDetailFragment.newInstance(intent.getStringExtra(EXTRA_EMPLOYEE_ID)).let {
                addFragmentToActivity(it, R.id.content_frame)
            }

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
        }

        obtainViewModel().makeCallEvent.observe(this, Observer { phoneNumber ->
            this.phoneNumber = phoneNumber
            makeCall()
        })
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
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                    makeCall()
                } else {
                    Toast.makeText(themedContext, R.string.request_permission_call_phone, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun obtainViewModel(): EmployeeDetailViewModel = obtainViewModel(EmployeeDetailViewModel::class.java)

    companion object {

        const val EXTRA_EMPLOYEE_ID = "EMPLOYEE_ID"

        private const val PERMISSIONS_REQUEST_CALL_PHONE = 1

    }
}
