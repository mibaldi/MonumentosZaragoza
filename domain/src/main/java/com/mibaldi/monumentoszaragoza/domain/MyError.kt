package com.mibaldi.monumentoszaragoza.domain

sealed interface MyError {
    class Server(val code: Int) : MyError
    object Connectivity : MyError
    class Unknown(val message: String) : MyError
}