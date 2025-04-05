package com.calculadora;

public class Calculadora {

    public double somar(double a, double b) {
        return a + b;
    }

    public double subtrair(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Não é possível dividir por zero.");
        }
        return a / b;
    }

    public double calcular(double num1, double num2, String operador) {
        double resultado = 0;
        switch (operador) {
            case "+":
                resultado = somar(num1, num2);
                break;
            case "-":
                resultado = subtrair(num1, num2);
                break;
            case "X":
                resultado = multiplicar(num1, num2);
                break;
            case "/":
                resultado = dividir(num1, num2);
                break;
        }
        return resultado;
    }
}