package com.calculadora;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    public void testAdicaoPositivos() {
        Calculadora calculadora = new Calculadora();
        assertEquals(5, calculadora.calcular(2, 3, "+"));
    }

    @Test
    public void testAdicaoNegativos() {
        Calculadora calculadora = new Calculadora();
        assertEquals(-1, calculadora.calcular(2, -3, "+"));
    }

    @Test
    public void testAdicaoPorZero() {
        Calculadora calculadora = new Calculadora();
        assertEquals(7, calculadora.calcular(7, 0, "+"));
    }

    @Test
    public void testSubtracaoPositivo() {
        Calculadora calculadora = new Calculadora();
        assertEquals(3, calculadora.calcular(7, 4, "-"));
    }

    @Test
    public void testSubtracaoNegativo() {
        Calculadora calculadora = new Calculadora();
        assertEquals(-11, calculadora.calcular(-7, 4, "-"));
    }

    @Test
    public void testSubtracaoPorZero() {
        Calculadora calculadora = new Calculadora();
        assertEquals(7, calculadora.calcular(7, 0, "-"));
    }

    @Test
    public void testMultiplicacaoPositivo() {
        Calculadora calculadora = new Calculadora();
        assertEquals(15, calculadora.calcular(5, 3, "X"));
    }

    @Test
    public void testMultiplicacaoNegativo() {
        Calculadora calculadora = new Calculadora();
        assertEquals(-6, calculadora.calcular(2, -3, "X"));
    }

    @Test
    public void testMultiplicacaoPorZero() {
        Calculadora calculadora = new Calculadora();
        assertEquals(0, calculadora.calcular(8, 0, "X"));
    }

    @Test
    public void testDivisaoPositivo() {
        Calculadora calculadora = new Calculadora();
        assertEquals(5, calculadora.calcular(15, 3, "/"));
    }
    
    @Test
    public void testDivisaoNegativo() {
        Calculadora calculadora = new Calculadora();
        assertEquals(-2, calculadora.calcular(6, -3, "/"));
    }

    @Test
    public void testDivisaoPorZero() {
        Calculadora calculadora = new Calculadora();
        assertThrows(ArithmeticException.class, () -> calculadora.calcular(6, 0, "/"));
    }
}
