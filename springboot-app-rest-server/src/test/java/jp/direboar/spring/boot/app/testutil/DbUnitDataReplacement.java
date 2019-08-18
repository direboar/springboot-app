package jp.direboar.spring.boot.app.testutil;

import java.util.HashMap;
import java.util.Map;

public class DbUnitDataReplacement {
    private final Map<String, Object> replacementObjects = new HashMap<>();
    private final Map<String, Object> replacementSubstrings = new HashMap<>();

    public DbUnitDataReplacement() {
        populateReplacementObjects();
        populateReplacementSubstrings();
    }

    /**
     * Make replacement objects and populate the map with them.
     */
    private void populateReplacementObjects() {
        replacementObjects.put("[IGNORE]", null);
        replacementObjects.put("[NULL]", null);
        replacementObjects.put("[TIMESTAMP_TODAY]", TestDatabaseDates.TIMESTAMP_TODAY);
        replacementObjects.put("[TIMESTAMP_TOMORROW]", TestDatabaseDates.TIMESTAMP_TOMORROW);
        replacementObjects.put("[TIMESTAMP_YESTERDAY]", TestDatabaseDates.TIMESTAMP_YESTERDAY);
    }

    /**
     * Make replacement substrings and populate the map with them.
     */
    private void populateReplacementSubstrings() {}

    public Map<String, Object> getReplacementObjects() {
        return replacementObjects;
    }

    public Map<String, Object> getReplacementSubstrings() {
        return replacementSubstrings;
    }
}
