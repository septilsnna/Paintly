package com.mobcom.paintly

data class UserUpdateResponse(
    val username: String?,
    val password: String?,
    val name: String?,
    val email: String?,
    val edit_freq: Int,
    val share_freq: Int,
)