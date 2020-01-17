package com.example.quizgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.signUpBtn
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        signUpBtn.setOnClickListener {

            val em = signEm.text.toString()
            val pass = signPass.text.toString()

            if (TextUtils.isEmpty(em) || TextUtils.isEmpty(pass)) {
                Toast.makeText(this, "რომელიღაც ველი ცარიელია", Toast.LENGTH_LONG).show()
            } else {

                auth.createUserWithEmailAndPassword(em, pass)
                    .addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "ყველაფერი არის ბედნიერად!!!", Toast.LENGTH_LONG)
                                .show()
                            val intent = Intent(this, UserInfoActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "რაღაც შეცდომაა, თავიდან სცადეთ!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
            }
        }
        goLoginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
