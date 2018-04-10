import exception.insufficientNumberException;

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
            if(isNumber(currentElement)){
                //check if the item is number
                stack.push( Double.parseDouble(currentElement));
            }
            else if (currentElement.equals("clear")){
                //if it's a clear command
                Calculator.stack.clear();
            }
            else if (currentElement.equals("+")){
                //if it's a "+"
                try{
                    if(Calculator.stack.size()>=2){
                        //check if there's sufficient number for the operator
                        add();
                    }
                    else{
                        throw new insufficientNumberException();
                    }
                }
                catch (insufficientNumberException ex){
                    result.append("operator + (position:"+ index + " ): insufficient parameters \n");
                    break;
                }
            }
            else if (currentElement.equals("-")){
                //if it's a "-"
                try{
                    if(Calculator.stack.size()>=2){
                        //check if there's sufficient number for the operator
                        minus();
                    }
                    else{
                        throw new insufficientNumberException();
                    }
                }
                catch (insufficientNumberException ex){
                    result.append("operator - (position:"+ index + " ): insufficient parameters \n");
                    break;
                }
            }
            else if (currentElement.equals("*")){
                //if it's a "*"
                try{
                    if(Calculator.stack.size()>=2){
                        //check if there's sufficient number for the operator
                        multiply();
                    }
                    else{
                        throw new insufficientNumberException();
                    }
                }
                catch (insufficientNumberException ex){
                    result.append("operator * (position:"+ index + " ): insufficient parameters \n");
                    break;
                }
            }
            else if (currentElement.equals("/")){
                //if it's a "/"
                try{
                    if(Calculator.stack.size()>=2){
                        //check if there's sufficient number for the operator
                        divide();
                    }
                    else{
                        throw new insufficientNumberException();
                    }
                }
                catch (insufficientNumberException ex){
                    result.append("operator / (position:"+ index + " ): insufficient parameters \n");
                    break;
                }
            }
            else{
                //other unknow char
                for(int m=0;m<index-1;m++){
                    Calculator.stack.pop();
                }
                result.append("ERROR, UNKOWN COMMAND\n") ;
                break;
            }
        }
        result.append("Stack : ");
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

    private static void add(){
        Double value1 = Calculator.stack.pop();
        Double value2 = Calculator.stack.pop();
        Calculator.stack.push(value2 + value1);
    }

    private static void minus(){
        Double value1 = Calculator.stack.pop();
        Double value2 = Calculator.stack.pop();
        Calculator.stack.push(value2 - value1);
    }

    private static void multiply(){
        Double value1 = Calculator.stack.pop();
        Double value2 = Calculator.stack.pop();
        Calculator.stack.push(value2 * value1);
    }

    private static void divide(){
        Double value1 = Calculator.stack.pop();
        Double value2 = Calculator.stack.pop();
        Calculator.stack.push(value2 / value1);
    }



}
