package service;

public class IndecServiceException extends Exception {

    public IndecServiceException(final Exception ex) {
        super(ex.getMessage(), ex.getCause());
    }

    public IndecServiceException(final String message) {
        super(message);
    }
}