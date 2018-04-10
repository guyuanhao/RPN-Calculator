package exception;

public class ZeroException extends Exception{
    // Parameterless Constructor
    public ZeroException() {}

    // Constructor that accepts a message
    public ZeroException(String message)
    {
        super(message);
    }
}
