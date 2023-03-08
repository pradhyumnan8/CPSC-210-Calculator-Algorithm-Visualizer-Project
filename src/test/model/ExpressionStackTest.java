package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpressionStackTest {

    ExpressionStack emptyStack;

    @BeforeEach
    void runBefore() {
        emptyStack = new ExpressionStack();
    }

    @Test
    void testPushChar() {
        emptyStack.push('+');

        assertEquals('+', emptyStack.getTop().getOperator());
    }


    @Test
    void testPushDouble() {
        emptyStack.push(2);

        assertEquals(2, emptyStack.getTop().getOperand());
    }

    @Test
    void testPop() {
        emptyStack.push(2);
        emptyStack.push(3);

        emptyStack.pop(true);

        assertEquals(2, emptyStack.getTop().getOperand());
    }

    @Test
    void testGetTopIndex() {
        assertEquals(-1, emptyStack.getTopIndex());
    }

    @Test
    void testGetTop() {

        assertTrue(emptyStack.getTop() == null);

        emptyStack.push(2);

        assertFalse(emptyStack.getTop() == null);
    }

    @Test
    void testIsEmptyTrue() {
        assertTrue(emptyStack.isEmpty());
    }

    @Test
    void testIsEmptyFalse() {
        emptyStack.push(2);

        assertFalse(emptyStack.isEmpty());
    }
}
