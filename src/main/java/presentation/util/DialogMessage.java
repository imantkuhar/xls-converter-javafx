package presentation.util;

/**
 * Created by Imant on 15.05.17.
 */
public enum DialogMessage {
    SUCCESS_JSON_SAVE("Successfully save to json !"),
    SUCCESS_DB_SAVE("Successfully save to db !"),
    ERROR_INPUT("CHECK OUT ALL THE TEXT INPUT FIELD");

    private String name;

    DialogMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

