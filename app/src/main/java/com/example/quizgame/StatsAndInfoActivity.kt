package com.example.quizgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_stats_and_info.*
import kotlinx.android.synthetic.main.activity_user_info.*

class StatsAndInfoActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_and_info)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("userInfo")

        addUserInfoChangeListener()

        goInfoBtn.setOnClickListener {
            val intent = Intent(this, UserInfoActivity::class.java)
            startActivity(intent)
            finish()
        }

        changePassBtn.setOnClickListener {
            val intent = Intent(this, ChangePasswordActivity::class.java)
            startActivity(intent)
        }

        deleteInfoBtn.setOnClickListener {
            val name = null
            val lastName = null
            val nickname = null
            val gender = null
            contactInfo(name, lastName, nickname, gender)
        }
    }

    private fun addUserInfoChangeListener() {
            database.child(auth.currentUser?.uid!!)
                .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError) {

                    }

                    override fun onDataChange(snap: DataSnapshot) {
                        val userInfo: UserInfo = snap.getValue(UserInfo::class.java) ?: return

                        nametext.text = "სახელი: " + userInfo.name
                        lastNameText.text = "გვარი: " + userInfo.lastname
                        nicknameText.text = "ნიკნეიმი: " + userInfo.nickname
                        genderText.text = "სქესი: " + userInfo.gender

                    }

                })
    }

    private fun contactInfo(name: String?, lastname: String?, nickname: String?, gender: String?) {
        val userInfo = UserInfo(name, lastname, nickname, gender)
        database.child(auth.currentUser?.uid!!).setValue(userInfo)
    }

}
