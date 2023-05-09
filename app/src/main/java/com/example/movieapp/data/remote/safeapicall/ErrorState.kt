package com.example.movieapp.data.remote.safeapicall

sealed class ErrorState() {
    data class ErrorCode(val code: String): ErrorState()
    data class ErrorMessage(val message: String) : ErrorState()
}