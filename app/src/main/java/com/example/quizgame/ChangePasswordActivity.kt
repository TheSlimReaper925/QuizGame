package com.example.quizgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        auth = FirebaseAuth.getInstance()

        updatePassBtn.setOnClickListener {
            val password: String = passwordInput.text.toString()

            if (TextUtils.isEmpty(password)) {

                Toast.makeText(this, "გთხვოთ შეიყვანეთ პაროლი!", Toast.LENGTH_LONG).show()

            } else {

                auth.currentUser?.updatePassword(password)
                    ?.addOnCompleteListener(this, OnCompleteListener { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(this, "პაროლი წარმატებით შეიცვალა!", Toast.LENGTH_LONG)
                                .show()
                            finish()

                        } else {

                            Toast.makeText(this, "პაროლი ვერ შეიცვალა!", Toast.LENGTH_LONG)
                                .show()

                        }
                    })
            }
        }
    }
}