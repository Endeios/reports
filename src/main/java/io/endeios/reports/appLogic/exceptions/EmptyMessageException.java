package io.endeios.reports.appLogic.exceptions;

public class EmptyMessageException extends RuntimeException{
    public EmptyMessageException(String message) {
        super(message);
    }
}
