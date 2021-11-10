package com.kovalin.shared

import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidWebAppTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", WebApp().start().contains("Android"))
    }
}