package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculationTest {
    String expression = "1+1";
    Calculation newCalculation = new Calculation("1+1", 2);

    @Test
    void testGetExpression() {
        assertEquals(expression.charAt(0), newCalculation.getExpression().charAt(0));
        assertEquals(expression.charAt(1), newCalculation.getExpression().charAt(1));
        assertEquals(expression.charAt(2), newCalculation.getExpression().charAt(2));
    }

    @Test
    void testGetResult() {
        assertEquals(2,newCalculation.getResult());
    }
}
