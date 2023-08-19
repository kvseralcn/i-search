package com.kevser.isearch.extension


import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionTest {
    @Test
    fun `check URLEncoded success`() {
        val value = "michael jackson"
        val expectedValue = "michael+jackson"
        assertEquals(expectedValue, value.getURLEncoded())
    }
}