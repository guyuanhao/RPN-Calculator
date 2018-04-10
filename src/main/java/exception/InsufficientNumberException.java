package exception;

public class InsufficientNumberException extends Exception{
    // Parameterless Constructor
    public InsufficientNumberException() {}

    // Constructor that accepts a message
    public InsufficientNumberException(String message)
    {
        super(message);
    }
}
