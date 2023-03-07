package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionEvaluatorTest {

    String userExpression1;
    String userExpression2;
    String userExpression3;
    String userExpression4;
    ExpressionEvaluator evaluator;

    @BeforeEach
    void runBefore() {
        userExpression1 = "2+4-5+8*4-3/1";
        userExpression2 = "2^2";
        userExpression3 = "1.1+1.1";
        userExpression4 = "2*(2.2+2.8)^8-45/23^3^4*2";
        evaluator = new ExpressionEvaluator();
    }


    @Test
    void testCalculateValue() throws FileNotFoundException {
        assertEquals(30, evaluator.calculate(userExpression1));
        assertEquals(4, evaluator.calculate(userExpression2));
        assertEquals(2.2, evaluator.calculate(userExpression3));
        assertEquals(781250, evaluator.calculate(userExpression4));
    }

    @Test
    void testPriority() {
        assertEquals(4, evaluator.priority('('));
        assertEquals(3, evaluator.priority('^'));
        assertEquals(2, evaluator.priority('*'));
        assertEquals(2, evaluator.priority('/'));
        assertEquals(1, evaluator.priority('-'));
        assertEquals(1, evaluator.priority('+'));
    }

    @Test
    void testIsValidFalse() {
        assertFalse(evaluator.isValid("la;sjfioew"));
        assertFalse(evaluator.isValid("(."));
        assertFalse(evaluator.isValid("3+2+"));
        assertFalse(evaluator.isValid("3+2.+"));
        assertFalse(evaluator.isValid("3+2.+"));
        assertFalse(evaluator.isValid("+3+2+3"));
        assertFalse(evaluator.isValid("+3+2+-2"));
        assertFalse(evaluator.isValid("+3+2+/3"));
        assertFalse(evaluator.isValid("3+*2"));
        assertFalse(evaluator.isValid("3+(4+7))*2"));
        assertFalse(evaluator.isValid("3+(4+7))*2"));
        assertFalse(evaluator.isValid("+++++"));
    }

    @Test
    void testIsValidTrue() {
        assertTrue(evaluator.isValid("3+2"));
        assertTrue(evaluator.isValid("3+2+(2-4)"));
        assertTrue(evaluator.isValid("3+2^32"));
        assertTrue(evaluator.isValid("3+2*3"));
        assertTrue(evaluator.isValid("3+2*3+(3+4)+4"));
    }

    @Test
    void testGetHistory() throws FileNotFoundException {
        evaluator.calculate(userExpression1);
        evaluator.calculate(userExpression2);

        assertNotNull(evaluator.getHistory());
    }
}
