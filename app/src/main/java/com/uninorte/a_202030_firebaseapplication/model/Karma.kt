package com.uninorte.a_202030_firebaseapplication.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Karma (
    val username : String? = "",
    val points: Int?
)