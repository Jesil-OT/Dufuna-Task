package com.mobile.dufunatask.auth.data.models.signin_result

import com.google.gson.annotations.SerializedName

data class SignedInUser(
    val email: String,
    @SerializedName("email_verified")
    val emailVerified: Boolean,
    @SerializedName("family_name")
    val familyName: String,
    @SerializedName("given_name")
    val givenName: String,
    val groups: List<String>,
    val lastRole: String,
    val name: String,
    val organization: String,
    @SerializedName("preferred_username")
    val preferredUsername: String,
    @SerializedName("realm_access")
    val realmAccess: RealmAccess,
    val sub: String,
    val userId: String
)