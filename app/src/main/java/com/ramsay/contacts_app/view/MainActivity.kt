package com.ramsay.contacts_app.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.os.Build
import android.webkit.PermissionRequest
import androidx.annotation.RequiresApi
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.ramsay.contacts_app.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runTimePermission()
    }



    private fun runTimePermission() {
        Dexter.withContext(this).withPermissions(
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_CONTACTS
        ).withListener(object : MultiplePermissionsListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {

            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                permissionToken: PermissionToken?
            ) {
                permissionToken?.continuePermissionRequest()
            }
        }).check()
    }
}