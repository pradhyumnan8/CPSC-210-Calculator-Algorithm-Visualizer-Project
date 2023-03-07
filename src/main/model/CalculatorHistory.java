package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

//Stores the history of calculations and has statistical methods to act upon them.
public class CalculatorHistory implements Writable {
    private ArrayList<Calculation> calculations = new ArrayList<>();

    public ArrayList<Calculation> getCalculations() {
        return calculations;
    }

    public void setCalculations(ArrayList<Calculation> calculations) {
        this.calculations = calculations;
    }

    public void addCalculation(Calculation newCalculation) {
        calculations.add(newCalculation);
    }


    //REQUIRES: calculations (history) should not be empty
    //EFFECTS: returns the mean of all the calculations performed so far.
    public double mean() {
        int i = 0;
        double sum = 0;

        for (i = 0; i < calculations.size(); i++) {
            sum += calculations.get(i).getResult();
        }

        return sum / calculations.size();
    }

    /*public double median() {
        ArrayList<Double> results = mapResults(calculations);
        results.sort(Comparator.naturalOrder());

        int middle = 0;

        if (calculations.size() == 1) {
            return results.get(0);
        } else if (calculations.size() == 2) {
            return (results.get(0) + results.get(1)) / 2;
        } else if (isEven(calculations.size())) {
            middle = results.size() / 2;
            return (results.get(middle) + results.get(middle + 1)) / 2;
        } else {
            middle = (results.size() - 1) / 2;
            return results.get(middle);
        }
    }


    //EFFECTS: maps the ArrayList<Calculation> onto a new ArrayList<Double>. Helper for median()
    private ArrayList<Double> mapResults(ArrayList<Calculation> calculations) {
        ArrayList<Double> results = new ArrayList<>();

        for (Calculation calculation : calculations) {
            results.add(calculation.getResult());
        }

        return results;
    }


    //EFFECTS: checks if the given number is even. Helper for median()
    private boolean isEven(int num) {
        return (num % 2) == 0;
    }*/


    //REQUIRES: a valid index for this.calculations.
    //MODIFIES: this
    //EFFECTS: removes the calculation at the given index from the history.
    public void delete(int index) {
        calculations.remove(index - 1);
    }

    //MODIFIES: this
    //EFFECTS: clears history. (resets this.calculations to no elements)
    public void clearHistory() {
        calculations.clear();
    }


    @Override
    //EFFECTS: converts the JSON Array returned by calculationsToJson to a JSON Object.
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("calculations", calculationsToJson());

        return json;
    }


    // EFFECTS: returns ArrayList<Calculation> calculations as a JSON Array. helper for toJson()
    public JSONArray calculationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Calculation calculation : calculations) {
            jsonArray.put(calculation.toJson());
        }

        return jsonArray;
    }
}
