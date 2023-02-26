package model;

import java.util.ArrayList;
import java.util.LinkedList;

public class CalculatorHistory {
    public ArrayList<Calculation> calculations;

    public CalculatorHistory() {
       // Calculation tempCalculation = new Calculation("test", 0.0);
       // calculations.add(0, tempCalculation);
    }

    public ArrayList<Calculation> getCalculations() {
        return calculations;
    }

    public void addCalculation(Calculation newCalculation) {
        calculations.add(newCalculation);
    }

    public double mean() {
        int i = 0;
        double sum = 0;

        for (i = 0; i < calculations.size(); i++) {
            sum += calculations.get(i).getResult();
        }

        return sum / calculations.size();
    }

    public double median() {
        int middle = 0;

        if (isEven(calculations.size())) {
            middle = calculations.size() / 2;

            return (calculations.get(middle).getResult() + calculations.get(middle + 1).getResult()) / 2;
        } else {
            middle = (calculations.size() - 1) / 2;
            return calculations.get(middle).getResult();
        }
    }

    private boolean isEven(int num) {
        return (num % 2) == 0;
    }
}
