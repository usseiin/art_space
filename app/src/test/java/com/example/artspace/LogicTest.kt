package com.example.artspace

import junit.framework.TestCase
import org.junit.Test

class ArtSpaceUnitTest {
    @Test
    fun increase_the_value_to_four() {
        val index = 3
        val expectedValue = 4
        val maxValue = 5
        val actualTip = increase(index, maxValue)
        TestCase.assertEquals(expectedValue, actualTip)
    }

    @Test
    fun decrease_the_value_to_three() {
        val index = 4
        val maxValue = 4
        val expectedValue = 3
        val actualValue = decrease(index, maxValue)
        TestCase.assertEquals(expectedValue, actualValue)
    }
}

