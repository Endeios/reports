package io.endeios.reports.appLogic;

import java.sql.Date;

public class DateProvider {
    public Date now() {
        return new Date(System.currentTimeMillis());
    }
}
