package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

        history.addCalculation(calc1);
        history.addCalculation(calc2);
    }

    @Test
    void meanTest() {
        assertEquals(2, history.mean());
    }
}