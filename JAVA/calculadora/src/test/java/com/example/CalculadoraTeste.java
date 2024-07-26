package com.example;

//import das bibliotecas junit
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculadoraTeste {
    Calculadora calc;

    @Before
    public void setUp() {
        calc = new Calculadora();
    }
    
    @Test
    public void testSoma() {
        assertEquals(7, calc.soma(3, 4));
    }
    
    @Test
    public void testSubtracao() {
        assertEquals(1, calc.subtracao(3, 2));
    }
    
    @Test
    public void testMultiplicacao() {
        assertEquals(12, calc.multiplicacao(3, 4));
    }
    
    @Test
    public void testDivisao() {
        assertEquals(1.5, calc.divisao(3, 2), 0.001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDivisaoPorZero() {
        calc.divisao(3, 0);
    }
    
    @Test
    public void testePotencia() {
        int resultado = calc.potencia(2, 3);
        assertEquals(8, resultado);
    }

    @Test
    public void testeRaizQuadrada() {
        double resultado = calc.raizQuadrada(16.0);
        assertEquals(4.0, resultado, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testeRaizQuadradaDeNumeroNegativo() {
        calc.raizQuadrada(-1);
    }
}
