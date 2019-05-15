package service.Actions;


public class APIException extends RuntimeException {

    public APIException(final Exception ex) {
        super(ex.getMessage(), ex.getCause());
    }

}
