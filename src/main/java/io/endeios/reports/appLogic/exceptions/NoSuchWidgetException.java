package io.endeios.reports.appLogic.exceptions;

public class NoSuchWidgetException extends RuntimeException {
    public NoSuchWidgetException(String origin, String name) {
        super("Could not find a widget in "+origin+"/"+name );
    }
}
