package domain.service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Imant on 13.05.17.
 */
public class JsonService {
    protected static final String JSON_END = ".json";

    public void saveToJson(String jsonString, String filePath) {
        try {
            PrintWriter writer = new PrintWriter(filePath + JSON_END);
            writer.write(jsonString);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
