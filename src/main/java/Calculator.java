import java.util.Stack;
import java.util.regex.Pattern;

public final class Calculator {

    private static Stack<Double> stack = new Stack<Double>();
    private static String input;

    public static String calculate(String i) {
        Calculator.input = i;
        String[] arr = Calculator.input.split(" ");         //get all elements in the input string
        for(String currentElement : arr){
            if(Calculator.isNumber(currentElement)){
                
            }
        }
        return Calculator.input;
    }

    private static boolean isNumber(String str){
        Pattern pattern = Pattern.compile("-?[0-9]+.*[0-9]*");
        return pattern.matcher(str).matches();
    }


}
