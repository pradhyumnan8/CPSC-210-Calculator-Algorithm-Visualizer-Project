package persistence;

import model.Calculation;
import model.CalculatorHistory;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CalculatorHistory history = new CalculatorHistory();
            history.setCalculations(reader.read());
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testReaderHistory() {
        JsonReader reader = new JsonReader("./data/testReaderHistory.json");
        JsonWriter jsonWriter = new JsonWriter("./data/testReaderHistory.json");
        CalculatorHistory history = new CalculatorHistory();
        Calculation calc1 = new Calculation("1+1", 2);

        history.addCalculation(calc1);

        try {
            jsonWriter.open();
            jsonWriter.write(history);
            jsonWriter.close();

            history.clearHistory();
            CalculatorHistory newHistory = new CalculatorHistory();

            ArrayList<Calculation> jsonOutput = reader.read();

            newHistory.setCalculations(jsonOutput);
            assertEquals(1, newHistory.getCalculations().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

   /* @Test
    void testParseHistory() {
        JSONArray jsonArray = new JSONArray();
        CalculatorHistory history = new CalculatorHistory();
        Calculation calc1 = new Calculation("1+1", 2);
        Calculation calc3 = new Calculation("1+3", 4);
        ArrayList<Calculation> =

        history.addCalculation(calc1);
        history.addCalculation(calc3);

        jsonArray.put(history.getCalculations().get(0).toJson());
        jsonArray.put(history.getCalculations().get(1).toJson());
    }*/
}
