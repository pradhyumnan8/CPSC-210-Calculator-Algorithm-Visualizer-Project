package model;

//Class to hold each calculation. That is, the expression inputted by the user as well as the result calculated
public class Calculation {
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
}
