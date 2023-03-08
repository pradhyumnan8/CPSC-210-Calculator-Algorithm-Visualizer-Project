package persistence;

import model.Calculation;
import model.CalculatorHistory;
import model.ExpressionEvaluator;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

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
    void testWriterInvalidFile() {
        try {
            //CalculatorHistory history = new CalculatorHistory();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterHistory() {
        try {
            CalculatorHistory history = new CalculatorHistory();
            JSONObject newJson = new JSONObject();

            history.addCalculation(calc1);
            history.addCalculation(calc2);

            JsonWriter writer = new JsonWriter("./data/testWriterHistory.json");
            newJson = history.toJson();

            writer.open();
            writer.write(history);
            writer.close();

            history.clearHistory();

            JsonReader reader = new JsonReader("./data/testWriterHistory.json");
            history.setCalculations(reader.read());

            assertEquals(2, history.getCalculations().size());
            assertEquals(2, history.getCalculations().get(0).getResult());
            assertEquals("1+1", history.getCalculations().get(0).getExpression());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
