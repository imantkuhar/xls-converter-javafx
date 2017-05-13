package domain.utils;

/**
 * Created by Imant on 13.05.17.
 */
public class Validator {

    private static final String COLUMN_NAME_PATTERN = "^[a-zA-Z]+$";

    public static boolean validateTableName(String tableName) {
        return tableName.matches(COLUMN_NAME_PATTERN);
    }

    
}
