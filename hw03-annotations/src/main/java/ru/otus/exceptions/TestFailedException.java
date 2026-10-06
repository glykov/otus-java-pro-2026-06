package ru.otus.exceptions;

public class TestFailedException extends RuntimeException {
    public TestFailedException() {
        super();
    }

    public TestFailedException(String message) {
        super(message);
    }

    public TestFailedException(Throwable cause) {
        super(cause);
    }

    public TestFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
