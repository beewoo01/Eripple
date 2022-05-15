package com.codebros.eripple.util

class AccountInfoSingleton private constructor(val account_idx: Int) {
    companion object {
        @Volatile
        private var INSTANCE: AccountInfoSingleton? = null
        var account_idx : Int? = null


        fun getInstance(account_idx: Int): AccountInfoSingleton =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: AccountInfoSingleton(account_idx).also {
                    INSTANCE = it
                    this.account_idx = account_idx
                }
            }
    }

}