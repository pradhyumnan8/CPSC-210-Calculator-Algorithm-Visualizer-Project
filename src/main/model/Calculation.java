package model;

import org.json.JSONObject;
import persistence.Writable;

//Class to hold each calculation. That is, the expression inputted by the user as well as the result calculated
public class Calculation implements Writable {
    private String expression;
    private double result;


    //MODIFIES: this
    //EFFECTS: instantiates a new Calculation with the given expression and result.
    public Calculation(String expression, double result) {
        this.expression = expression;
        this.result = result;
    }

    //EFFECTS: returns expression.
    public String getExpression() {
        return expression;
    }

    //EFFECTS: returns result.
    public double getResult() {
        return result;
    }

    @Override
    //EFFECTS: Converts each calculation to a JSON Object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("expression", expression);
        json.put("result", result);

        return json;
    }
}
