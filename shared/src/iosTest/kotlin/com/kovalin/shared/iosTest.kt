package com.kovalin.shared

import kotlin.test.Test
import kotlin.test.assertTrue

class IosGreetingTest {

    @Test
    fun testExample() {
        assertTrue(WebApp().start().contains("iOS"), "Check iOS is mentioned")
    }
}