import java.util.Stack;
import java.util.regex.Pattern;

public final class Calculator {

    private static Stack<Double> stack = new Stack<Double>();
    private static String input;

    public static String calculate(String i) {
        StringBuilder result = new StringBuilder();
        Calculator.input = i;
        String[] arr = Calculator.input.split(" ");         //get all elements in the input string
        int index=0;
        for(String currentElement : arr){
            index++;
            if(Calculator.isNumber(currentElement)){
                stack.push( Double.parseDouble(currentElement));
            }
            else if (currentElement.equals("clear")){
                Calculator.stack.clear();
            }
            else{
                for(int m=0;m<index-1;m++){
                    Calculator.stack.pop();
                }
                result.append("ERROR, UNKOWN COMMAND\n") ;
                break;
            }
        }
        for(Double obj : stack)
        {
            result.append(obj + " ");
        }
        return result.toString();
    }

    private static boolean isNumber(String str){
        Pattern pattern = Pattern.compile("-?[0-9]+.*[0-9]*");
        return pattern.matcher(str).matches();
    }


}
