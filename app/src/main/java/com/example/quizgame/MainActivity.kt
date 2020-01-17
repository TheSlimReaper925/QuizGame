package com.example.quizgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        signoutBtn.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        Glide.with(this)
            .load("https://i.ytimg.com/vi/Kp2bYWRQylk/maxresdefault.jpg")
            .into(mathPic)

        Glide.with(this)
            .load("https://www.profleeberger.com/wp-content/uploads/2019/01/Things-You-Have-to-Know-about-Geography.jpg")
            .into(imageView5)

        Glide.with(this)
            .load("https://www.brandcrowd.com/gallery/brands/pictures/picture14314246537980.png")
            .into(imageView6)

        Glide.with(this)
            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Olympic_rings_without_rims.svg/1200px-Olympic_rings_without_rims.svg.png")
            .into(imageView7)

        Glide.with(this)
            .load("https://www.talenttalks.net/wp-content/uploads/2018/06/History.jpg")
            .into(imageView8)

        infobtn.setOnClickListener {
            val intent = Intent(this, StatsAndInfoActivity::class.java)
            startActivity(intent)
        }

        mathPic.setOnClickListener {
            val intent = Intent(this, MathTestActivity::class.java)
            startActivity(intent)
        }

        imageView5.setOnClickListener {
            Toast.makeText(this, "ტესტი მალე დაემატება", Toast.LENGTH_LONG).show()
        }
        imageView6.setOnClickListener {
            Toast.makeText(this, "ტესტი მალე დაემატება", Toast.LENGTH_LONG).show()
        }
        imageView7.setOnClickListener {
            Toast.makeText(this, "ტესტი მალე დაემატება", Toast.LENGTH_LONG).show()
        }
        imageView8.setOnClickListener {
            Toast.makeText(this, "ტესტი მალე დაემატება", Toast.LENGTH_LONG).show()
        }
    }
}
