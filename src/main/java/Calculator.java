import java.util.Stack;

public final class Calculator {

    private static Stack<Double> stack = new Stack<Double>();
    private static String input;

    public static String getInput() {
        return input;
    }

    public static String setInput(String input) {
        Calculator.input = input;
        Calculator.input="aaaaaaaaaaaa";
        return Calculator.input;
    }


}
