package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorHistoryTest {
    Calculation calc1;
    Calculation calc2;

    CalculatorHistory history;


    @BeforeEach
    void runBefore() {
        calc1 = new Calculation("1+1", 2);
        calc2 = new Calculation("1+1", 2);

        history = new CalculatorHistory();
    }

    @Test
    void testMean() {
        history.addCalculation(calc1);
        history.addCalculation(calc2);

        assertEquals(2, history.mean());
    }

    @Test
    void testGetCalculations() {
        history.addCalculation(calc1);
        history.addCalculation(calc2);

        assertEquals(2, history.getCalculations().get(0).getResult());
        assertEquals(2, history.getCalculations().get(1).getResult());
    }

    @Test
    void testClearHistory() {
        history.addCalculation(calc1);
        history.addCalculation(calc2);

        assertEquals(2, history.getCalculations().size());

        history.clearHistory();

        assertEquals(0, history.getCalculations().size());
    }


    @Test
    void testDelete() {
        history.addCalculation(calc1);
        history.addCalculation(calc2);

        assertEquals(2, history.getCalculations().size());

        history.delete(1);

        assertEquals(1, history.getCalculations().size());
        assertEquals(2, history.getCalculations().get(0).getResult());
    }


    @Test
    void testSetCalculations() {
        assertEquals(0, history.getCalculations().size());

        CalculatorHistory newHistory = new CalculatorHistory();

        newHistory.addCalculation(calc1);
        newHistory.addCalculation(calc2);

        assertEquals(2, newHistory.getCalculations().size());

        history.setCalculations(newHistory.getCalculations());

        assertEquals(2, history.getCalculations().size());

        assertEquals(2, history.getCalculations().get(0).getResult());
    }
}
