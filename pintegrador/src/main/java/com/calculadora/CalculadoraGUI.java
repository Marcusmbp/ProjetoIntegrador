package com.calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class CalculadoraGUI extends JFrame implements ActionListener {

    private JLabel display;
    private JLabel displayOperacao;
    private double num1 = 0;
    private String num2 = "indefinido";
    private String operador = "";
    private boolean novoNumero = true;
    private com.calculadora.Calculadora calculadora = new com.calculadora.Calculadora();

    public CalculadoraGUI() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 0;

        JPanel displayOperacaoPanel = new JPanel(new BorderLayout());
        displayOperacaoPanel.setBackground(Color.DARK_GRAY);
        displayOperacaoPanel.setBorder(BorderFactory.createEmptyBorder(25, 5, 5, 5));

        displayOperacao = new JLabel("");
        displayOperacao.setFont(new Font("Arial", Font.PLAIN, 13));
        displayOperacao.setHorizontalAlignment(JLabel.RIGHT);
        displayOperacao.setForeground(Color.LIGHT_GRAY);
        displayOperacaoPanel.add(displayOperacao, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        add(displayOperacaoPanel, gbc);

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(Color.DARK_GRAY);
        displayPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 20, 5));

        display = new JLabel("0");
        display.setFont(new Font("Arial", Font.BOLD, 46));
        display.setHorizontalAlignment(JLabel.RIGHT);
        display.setForeground(Color.WHITE);
        displayPanel.add(display, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4;
        add(displayPanel, gbc);

        JPanel botoesPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        botoesPanel.setBackground(Color.DARK_GRAY);

        String[][] botoes = {
                {"CE", "C", "⌫", "/"},
                {"7", "8", "9", "X"},
                {"4", "5", "6", "-"},
                {"1", "2", "3", "+"},
                {"+/-", "0", ",", "="}
        };

        for (int i = 0; i < botoes.length; i++) {
            for (int j = 0; j < botoes[i].length; j++) {
                JButton button = new JButton(botoes[i][j]);
                button.setFont(new Font("Segoe UI Symbol", Font.BOLD, 20));
                button.setBackground(Color.DARK_GRAY);
                button.setForeground(Color.WHITE);
                button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                button.setFocusPainted(false);
                button.addActionListener(this);
                botoesPanel.add(button);
            }
        }

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(botoesPanel, gbc);

        setSize(335, 550);
        setLocationRelativeTo(null);
        setVisible(true);
        displayOperacao.setText(" ");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        Double num1Old = num1;

        if ("0123456789.".contains(comando)) {
            if (novoNumero) {
                display.setText(comando);
                num2 = display.getText();
                novoNumero = false;
            } else {
                display.setText(display.getText() + comando);
            }
        } else if ("+-X/=".contains(comando)) {
            if ("=".contains(comando) && num2.equals("indefinido")) {
                num2 = String.valueOf(num1);
            }
            if ((!operador.isEmpty()) && (!num2.equals("indefinido"))) {
                calcular();
            }
            
            num1 = Double.parseDouble(display.getText().replace(",", "."));
            
            if ("+-X/".contains(comando)) {
                num2 = "indefinido";
                operador = comando;
                DecimalFormat df = new DecimalFormat("#.########");
                displayOperacao.setText(String.valueOf(df.format(num1)) + " " + comando);
            } else {
                DecimalFormat df = new DecimalFormat("#.########");
                displayOperacao.setText(String.valueOf(df.format(num1Old)) + " " + operador + " " + String.valueOf(df.format(Double.parseDouble(num2))) + " = ");
            }
            
            novoNumero = true;
        } else if ("C".equals(comando)) {
            display.setText("0");
            displayOperacao.setText(" ");
            num1 = 0;
            operador = "";
            novoNumero = true;
        } else if ("CE".equals(comando)) {
            display.setText("0");
            novoNumero = true;
        } else if ("⌫".equals(comando)) {
            if (displayOperacao.getText().contains("=")) {
                displayOperacao.setText(" ");
                num1 = 0;
                operador = "";
                novoNumero = true;
            } else {
                String texto = display.getText();
                if (texto.length() > 1) {
                    display.setText(texto.substring(0, texto.length() - 1));
                } else {
                    display.setText("0");
                    novoNumero = true;
                }
            }
        } else if ("+/-".equals(comando)) {
            double valor = Double.parseDouble(display.getText().replace(",", "."));
            DecimalFormat df = new DecimalFormat("#.########");
            display.setText(String.valueOf(df.format(-valor)));
        } else if (",".equals(comando)) {
            if (!display.getText().contains(",")) {
                display.setText(display.getText() + ",");
            }
            novoNumero = false;
        }
    }

    private void calcular() {
        double resultado = 0;
        try {
            resultado = calculadora.calcular(num1, Double.parseDouble(num2), operador);

            DecimalFormat df = new DecimalFormat("#.########");
            String resultadoFormatado = df.format(resultado);

            if (resultadoFormatado.endsWith(".0")) {
                resultadoFormatado = resultadoFormatado.substring(0, resultadoFormatado.length() - 2);
            }

            display.setText(resultadoFormatado);
            displayOperacao.setText(" ");
        } catch (ArithmeticException ex) {
            display.setText(ex.getMessage());
        }
        novoNumero = false;
    }

    public static void main(String[] args) {
        new CalculadoraGUI();
    }
}