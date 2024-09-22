package com.example.sustainablesteps.model

import android.provider.ContactsContract.CommonDataKinds.Email

data class UserModel(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val current_points: Int = 0
) {
    constructor() : this("", "", "", 0)
}
