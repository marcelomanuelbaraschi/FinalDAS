package repository.exceptions;

public class RepositoryException extends Exception {

    public RepositoryException(final Exception ex) {
        super(ex.getMessage(), ex.getCause());
    }

    public RepositoryException(final String message) {
        super(message);
    }
}
