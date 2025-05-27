package com.example.topacademy_android

import org.junit.Test
import org.junit.Assert.*

class CalculatorRepositoryTest {

    private val repository = CalculatorRepository()

    @Test
    fun `test simple addition`() {
        val result = repository.evaluateExpression("2+3")
        assertTrue(result.isSuccess)
        assertEquals(5.0, result.getOrNull()!!, 0.001)
    }

    @Test
    fun `test simple subtraction`() {
        val result = repository.evaluateExpression("10-3")
        assertTrue(result.isSuccess)
        assertEquals(7.0, result.getOrNull()!!, 0.001)
    }

    @Test
    fun `test simple multiplication`() {
        val result = repository.evaluateExpression("4×5")
        assertTrue(result.isSuccess)
        assertEquals(20.0, result.getOrNull()!!, 0.001)
    }

    @Test
    fun `test simple division`() {
        val result = repository.evaluateExpression("15÷3")
        assertTrue(result.isSuccess)
        assertEquals(5.0, result.getOrNull()!!, 0.001)
    }

    @Test
    fun `test complex expression`() {
        val result = repository.evaluateExpression("2+3×4")
        assertTrue(result.isSuccess)
        assertEquals(14.0, result.getOrNull()!!, 0.001)
    }

    @Test
    fun `test decimal numbers`() {
        val result = repository.evaluateExpression("2,5+1,5")
        assertTrue(result.isSuccess)
        assertEquals(4.0, result.getOrNull()!!, 0.001)
    }

    @Test
    fun `test format result integer`() {
        val formatted = repository.formatResult(5.0)
        assertEquals("5", formatted)
    }

    @Test
    fun `test format result decimal`() {
        val formatted = repository.formatResult(5.5)
        assertEquals("5,5", formatted)
    }

    @Test
    fun `test invalid expression`() {
        val result = repository.evaluateExpression("2+")
        assertTrue(result.isFailure)
    }
} 