package persistence;

import model.Calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {
    protected void checkCalculation(String expression, double result, Calculation calculation) {
        assertEquals(expression, calculation.getExpression());
        assertEquals(result, calculation.getResult());
    }
}
