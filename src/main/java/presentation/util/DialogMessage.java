package presentation.util;

/**
 * Created by Imant on 15.05.17.
 */
public enum DialogMessage {
    SUCCESS_JSON_SAVE("Successfully save to json!"),
    SUCCESS_DB_SAVE("Successfully save to db!"),
    ERROR_INPUT("CHECK OUT ALL THE TEXT INPUT FIELDS AND TABLE COLUMNS");

    private String name;

    DialogMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

