package persistence;

import model.Calculation;
import model.CalculatorHistory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class JsonReader {
    private String source;
    private ArrayList<Calculation> history;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
        //this.history = new CalculatorHistory();
        this.history = new ArrayList<>();
    }

    // EFFECTS: reads history from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ArrayList<Calculation> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        //return parseCalculatorHistory(jsonObject);
        parseHistory(jsonObject);
        return this.history;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // MODIFIES: history
    // EFFECTS: parses calculations (history) from JSONARRAY and converts it into an ArrayList<Calculation>
    private void parseHistory(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("calculations");
        for (Object json : jsonArray) {
            JSONObject calculation = (JSONObject) json;
            addCalculation(calculation);
        }
    }

    // MODIFIES: history
    // EFFECTS: parses each JSON Object in JSONARRAY and converts it into a Calculation. Helper for parseHistory()
    private void addCalculation(JSONObject jsonObject) {
        String expression = jsonObject.getString("expression");
        double result = jsonObject.getDouble("result");
        Calculation calculation = new Calculation(expression, result);
        history.add(calculation);
        //history.addCalculation(calculation);
    }
}
