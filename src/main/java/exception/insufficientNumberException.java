package exception;

public class insufficientNumberException extends Exception{
    // Parameterless Constructor
    public insufficientNumberException() {}

    // Constructor that accepts a message
    public insufficientNumberException(String message)
    {
        super(message);
    }
}
