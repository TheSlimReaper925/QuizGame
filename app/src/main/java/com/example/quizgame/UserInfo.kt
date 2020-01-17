package com.example.quizgame

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties

data class UserInfo(
    val name : String? = "",
    val lastname : String? ="",
    val nickname : String? = "",
    val gender : String? = ""
)