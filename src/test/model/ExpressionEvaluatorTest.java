package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpressionEvaluatorTest {

    String userExpression1 = "2+4-5+8*4-3/1";
    String userExpression2 = "2^2";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();



    @Test
    void calculateTestValue() {
        assertEquals(30, evaluator.calculate(userExpression1));
        assertEquals(4, evaluator.calculate(userExpression2));
    }

    @Test
    void priorityTest() {
        assertEquals(4, evaluator.priority('('));
        assertEquals(3, evaluator.priority('^'));
        assertEquals(2, evaluator.priority('*'));
        assertEquals(2, evaluator.priority('/'));
        assertEquals(1, evaluator.priority('-'));
        assertEquals(1, evaluator.priority('+'));
    }

    @Test
    void isValidTestFalse() {
        assertFalse(evaluator.isValid("la;sjfioew"));
        assertFalse(evaluator.isValid("(."));
        assertFalse(evaluator.isValid("3+2+"));
        assertFalse(evaluator.isValid("3+2.+"));
        assertFalse(evaluator.isValid("3+2.+"));
        assertFalse(evaluator.isValid("+3+2+3"));
    }

    @Test
    void isValidTestTrue() {
        assertTrue(evaluator.isValid("3+2"));
        assertTrue(evaluator.isValid("3+2+(2-4)"));
        assertTrue(evaluator.isValid("3+2^32"));
        assertTrue(evaluator.isValid("3+2*3"));
    }
}
