package com.example.movieapp.extension

fun String.getURLEncoded(): String {
    return this.replace(" ", "+")
}