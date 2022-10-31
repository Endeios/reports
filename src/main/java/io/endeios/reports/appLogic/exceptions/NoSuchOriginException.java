package io.endeios.reports.appLogic.exceptions;

public class NoSuchOriginException extends RuntimeException{
    public NoSuchOriginException(String missingOrigin) {
       super("There is not an origin called "+missingOrigin);
    }
}
