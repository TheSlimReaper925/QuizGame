package com.example.quizgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("userInfo")

        var selected = ""

        val genders = arrayOf("მამრობითი", "მდედრობითი", "სხვა")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genders)

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        spinner.adapter = adapter

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selected = "${parent?.getItemAtPosition(position).toString()}"
            }

        }


        saveBtn.setOnClickListener {

            val name: String = nameTx.text.toString()
            val lastName: String = lastnameTx.text.toString()
            val nickname: String = nickTx.text.toString()
            val gender: String = selected
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(nickname) || TextUtils.isEmpty(gender)) {
                Toast.makeText(this, "რომელიღაც ველი ცარიელია", Toast.LENGTH_LONG).show()
            } else {
                contactInfo(name, lastName, nickname, gender)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun contactInfo(name: String, lastname: String?, nickname: String?, gender: String?) {
        val userInfo = UserInfo(name, lastname, nickname, gender)
        database.child(auth.currentUser?.uid!!).setValue(userInfo)
    }

}
