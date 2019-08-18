package jp.direboar.spring.boot.app.testutil;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.Period;

public class TestDatabaseDates {
    public static final Period ONE_DAY = Period.ofDays(1);

    public static final Instant NOW = Instant.now();

    public static final Timestamp TIMESTAMP_TODAY = asTimestamp(NOW);
    public static final Timestamp TIMESTAMP_TOMORROW = asTimestamp(NOW.plus(ONE_DAY));
    public static final Timestamp TIMESTAMP_YESTERDAY = asTimestamp(NOW.minus(ONE_DAY));

    public static Timestamp asTimestamp(final Instant instant) {
        return Timestamp.from(instant);
    }
}
