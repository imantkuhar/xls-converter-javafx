package domain.service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Imant on 13.05.17.
 */
public class JsonService {
    protected static final String JSON_END = ".json";

    public void saveJson(String json, String filePath, String fileName) {
        try {
            PrintWriter writer = new PrintWriter(filePath + fileName + JSON_END);
            writer.write(json);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
