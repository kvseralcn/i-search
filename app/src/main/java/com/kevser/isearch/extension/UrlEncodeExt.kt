package com.kevser.isearch.extension

fun String.getURLEncoded(): String {
    return this.replace(" ", "+")
}