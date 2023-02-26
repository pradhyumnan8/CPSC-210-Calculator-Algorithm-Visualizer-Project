package ui;

import model.Calculation;
import model.CalculatorHistory;
import model.ExpressionEvaluator;

public class Main {

    public static void main(String[] args) {
       // InputOutput calculatorProgram = new InputOutput();

       // calculatorProgram.calculator();

        CalculatorHistory history = new CalculatorHistory();
        Calculation testCal = new Calculation("hello", 1.1);

        System.out.println(history.getCalculations());

        history.addCalculation(testCal);

        System.out.println(history.getCalculations());
    }
}
