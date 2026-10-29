package com.example.contactsharing

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val permissionCode = 100

    private lateinit var btnLoad: Button
    private lateinit var contactList: ListView
    private lateinit var txtCount: TextView

    private val contacts = ArrayList<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLoad = findViewById(R.id.btnLoad)
        contactList = findViewById(R.id.contactList)
        txtCount = findViewById(R.id.txtCount)

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            contacts
        )

        contactList.adapter = adapter

        btnLoad.setOnClickListener {
            checkPermission()
        }
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            loadContacts()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                permissionCode
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )

        if (requestCode == permissionCode) {
            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                loadContacts()
            } else {
                Toast.makeText(
                    this,
                    "Contact permission denied",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun loadContacts() {
        contacts.clear()
        var cursor: Cursor? = null

        try {
            cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                ),
                null,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            )

            cursor?.use {
                val nameIndex = it.getColumnIndexOrThrow(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                )

                val numberIndex = it.getColumnIndexOrThrow(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                )

                while (it.moveToNext()) {
                    val name = it.getString(nameIndex) ?: "Unknown"
                    val number = it.getString(numberIndex) ?: "No number"

                    contacts.add("$name\n$number")
                }
            }

            adapter.notifyDataSetChanged()
            txtCount.text = "Contacts: ${contacts.size}"

            if (contacts.isEmpty()) {
                Toast.makeText(
                    this,
                    "No contacts with phone numbers found",
                    Toast.LENGTH_SHORT
                ).show()
            }

        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Error loading contacts",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
