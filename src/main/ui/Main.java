package ui;

import java.io.FileNotFoundException;

public class Main {

    //MODIFIES: this.calculatorProgram
    //EFFECTS: starts the program
    public static void main(String[] args) {
        try {
            CalculatorApp calculatorApp = new CalculatorApp();
            calculatorApp.calculator();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}

