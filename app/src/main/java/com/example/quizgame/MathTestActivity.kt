package com.example.quizgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_math_test.*
import kotlinx.android.synthetic.main.activity_stats_and_info.*
import kotlinx.android.synthetic.main.activity_user_info.*

class MathTestActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math_test)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("userInfo")

        val radioGroups = arrayOf(
            radioGroupOne,
            radioGroupTwo,
            radioGroupThree,
            radioGroupFour,
            radioGroupFive
        )

        submitBtn.setOnClickListener {
            var score = 0
            var isQuizFinished = false
            for (radioGroup in radioGroups) {
                if (findViewById<RadioButton>(radioGroup.checkedRadioButtonId) == null) {
                    Toast.makeText(this, "მონიშნეთ ყველა პასუხი!", Toast.LENGTH_SHORT).show()
                    isQuizFinished = false
                    break
                } else {
                    val radio = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                    when (radio.text) {
                        "b - a" -> score += 2
                        "75" -> score += 2
                        "64000 სმ^2" -> score += 2
                        "4" -> score += 2
                        "-3" -> score += 2
                    }

                    isQuizFinished = true

                }


            }

            if (isQuizFinished) {
                scoreValue.text = "ქულა: 10/" + score.toString()
                submitBtn.isClickable = false
            }



        }

    }
}