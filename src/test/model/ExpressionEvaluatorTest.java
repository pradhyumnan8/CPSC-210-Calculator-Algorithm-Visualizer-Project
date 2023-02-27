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
}
