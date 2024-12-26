package ru.nyakshoot.aston_intensive_4.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    var photo: String,
    var firstName: String,
    var lastName: String,
    var phoneNumber: String
): Parcelable
