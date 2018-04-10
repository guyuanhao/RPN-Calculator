import exception.InsufficientNumberException;
import exception.ZeroException;

import java.util.Stack;
import java.util.regex.Pattern;

public final class Calculator {

    private static Stack<Double> stack = new Stack<Double>();
    private static Stack<String> backupStack = new Stack<String>(); // for implement the "undo" function
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
                Calculator.stack.push( Double.parseDouble(currentElement));
                //each undo will release exactly two elements, if it was a "sqrt" or number input, then we use a flag to alert
                Calculator.backupStack.push(currentElement);
                Calculator.backupStack.push("number");
            }
            else if (currentElement.equals("clear")){
                //if it's a clear command
                Calculator.stack.clear();
                Calculator.backupStack.clear();
            }
            else if (currentElement.equals("+")){
                //if it's a "+"
                try{
                    if(Calculator.stack.size()>=2){
                        //check if there's sufficient number for the operator
                        add();
                    }
                    else{
                        throw new InsufficientNumberException();
                    }
                }
                catch (InsufficientNumberException ex){
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
                        throw new InsufficientNumberException();
                    }
                }
                catch (InsufficientNumberException ex){
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
                        throw new InsufficientNumberException();
                    }
                }
                catch (InsufficientNumberException ex){
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
                        throw new InsufficientNumberException();
                    }
                }
                catch (InsufficientNumberException ex){
                    result.append("operator / (position:"+ index + " ): insufficient parameters \n");
                    break;
                }
                catch (ZeroException ex){
                    result.append("operator / (position:"+ index + " ): A number cannot be divided by zero \n");
                    break;
                }
            }
            else if (currentElement.equals("sqrt")){
                //if it's a "sqrt"
                try{
                    if(Calculator.stack.size()>=1){
                        //check if there's sufficient number for the operator
                        sqrt();
                    }
                    else{
                        throw new InsufficientNumberException();
                    }
                }
                catch (InsufficientNumberException ex){
                    result.append("operator sqrt (position:"+ index + " ): insufficient parameters \n");
                    break;
                }
            }
            else if (currentElement.equals("undo")){
                //if it's a "undo"
                undo();
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
        Calculator.backupStack.push(value1.toString());
        Calculator.backupStack.push(value2.toString());
    }

    private static void minus(){
        Double value1 = Calculator.stack.pop();
        Double value2 = Calculator.stack.pop();
        Calculator.stack.push(value2 - value1);
        Calculator.backupStack.push(value1.toString());
        Calculator.backupStack.push(value2.toString());
    }

    private static void multiply(){
        Double value1 = Calculator.stack.pop();
        Double value2 = Calculator.stack.pop();
        Calculator.stack.push(value2 * value1);
        Calculator.backupStack.push(value1.toString());
        Calculator.backupStack.push(value2.toString());
    }

    private static void divide() throws ZeroException {
        Double value1 = Calculator.stack.pop();
        Double value2 = Calculator.stack.pop();
        if(value1 == 0){
            Calculator.stack.push(value2);
            Calculator.stack.push(value1);
            throw new ZeroException();
        }
        Calculator.stack.push(value2 / value1);
        Calculator.backupStack.push(value1.toString());
        Calculator.backupStack.push(value2.toString());
    }

    private static void sqrt(){
        Double value1 = Calculator.stack.pop();
        Calculator.stack.push(Math.sqrt(value1));
        Calculator.backupStack.push(value1.toString());
        Calculator.backupStack.push("sqrt");
    }

    private static void undo(){
        //each undo will release exactly two elements, if it was a "sqrt" or number input, then we use a flag to alert
        if(Calculator.backupStack.size()>0){
            Calculator.stack.pop();
            String value1 = Calculator.backupStack.pop();
            if (value1.equals("sqrt")){
                //undo sqrt
                Double value2 = Double.parseDouble(Calculator.backupStack.pop());
                Calculator.stack.push(value2);
            }
            else if (value1.equals("number")){
                //undo number
                Calculator.backupStack.pop();
            }
            else{
                Double value2 = Double.parseDouble(Calculator.backupStack.pop());
                Calculator.stack.push( Double.parseDouble(value1));
                Calculator.stack.push(value2);
            }
        }
    }



}
