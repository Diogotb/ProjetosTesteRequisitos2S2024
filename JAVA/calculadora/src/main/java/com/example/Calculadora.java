package com.example;

public class Calculadora {
    //soma
    public int soma(int a, int b) {
        return a+b;     
    }
    //subtração
    public int subtracao(int a, int b){
        return a-b;
    }
    //multiplicacao
    public int multiplicacao(int a, int b){
        return a*b;
    }
    //divisao
    public double divisao(int a, int b){
        if(b!=0)
            return (double)a/b;
        else
            throw new IllegalArgumentException("Divisão por zero.");
    }
    //potencia
    public int potencia(int base, int expoente) {
        return (int) Math.pow(base, expoente);
    }
    //raiz
    public double raizQuadrada(double numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Número negativo não tem raiz quadrada real");
        }
        return Math.sqrt(numero);
    }

}
