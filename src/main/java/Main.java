import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String result;
        while(true){
            String str = readDataFromConsole("Please enter your command(type \"end\" to exit):");
            if(str.equals("end")){
                break;
            }
            result =  Calculator.calculate(str);
            System.out.println("Stack:" + result);
        }
        System.out.println("bye...");
    }

    /**
     * Use java.util.Scanner to read data from console
     *
     * @param prompt
     *
     * @return input string
     */
    private static String readDataFromConsole(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
