package br.com.cit.contacts.repository.exception;

public class RepositoryException extends Exception {

    public RepositoryException() {
    }

    public RepositoryException(String s) {
        super(s);
    }

    public RepositoryException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public RepositoryException(Throwable throwable) {
        super(throwable);
    }
}
