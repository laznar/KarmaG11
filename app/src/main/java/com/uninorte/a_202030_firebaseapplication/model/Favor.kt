package com.uninorte.a_202030_firebaseapplication.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Favor (val uid: String?="",val key: String?="" ,val id: Int?=0 ,val type: String?="", val status: String?="", val details: String?="")
