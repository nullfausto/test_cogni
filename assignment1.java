import java.util.*;

public class assignment1{
    
    public static void main(String[] args){

        // Part 1: Exploring variables and primitive types
        int age = 24;
        double height = 64.96063;
        char initial = 'F';
        boolean isStudent = false;

        System.out.println("Age: " + age + "\nHeight: " + height + "\nFirst letter of my name: " + initial + "\nStudent: " + isStudent);

        // Part 2: Increment and Decrement Operations
        int counter = 10;
        counter++;
        System.out.println(counter);
        counter--;
        System.out.println(counter);
        for(int i=0;i<=5;i++){ 
            System.out.print("" + counter + " ");
            counter++;
        }

        counter--;
        while(counter>=10){
            System.out.print("" + counter + " ");
            counter--;
        }

        // Part 3: Working with Strings and User Input
        Scanner input = new Scanner(System.in);
        String firstName = input.nextLine();
        String lastName = input.nextLine();
        

        String fullName = firstName.concat(" "+lastName);
        String upperFullName = fullName.toUpperCase();
        char[] aux = upperFullName.toCharArray();
        int counterFirstChar = 0;
        for(int i = 0;i<aux.length;i++){
            if (aux[0]==aux[i]) {
                counterFirstChar++;
            }
        }
        System.out.println("Full name: " + fullName + "\nUppercase version: " + upperFullName + "\nNumber of times: " + counterFirstChar);

        // Part 4: Using Conditionals, Logical Operators, and Switch Statements
        int resTest1 = (int)(Math.random()*101);
        int resTest2 = (int)(Math.random()*101);
        int resTest3 = (int)(Math.random()*101);

        int averageScore = (resTest1+resTest2+resTest3)/3;
        if (averageScore >= 90 && averageScore <= 100) {
            System.out.printf("Excellent: %d",averageScore);
        }
        else if (averageScore >= 70 && averageScore <= 89) {
            System.out.printf("Good: %d",averageScore);
        }
        else if (averageScore >= 50 && averageScore <= 69) {
            System.out.printf("Average: %d",averageScore);
        }
        else if (averageScore < 50) {
            System.out.printf("Poor: %d",averageScore);
        }

        int day = (int)(Math.random()*8);
        switch (day) {
            case 1:
                System.out.println("\nMonday");
                break;
            case 2:
                System.out.println("\nTuesday");
                break;
            case 3:
                System.out.println("\nWednesday");
                break;
            case 4:
                System.out.println("\nThursday");
                break;
            case 5:
                System.out.println("\nFriday");
                break;
            case 6:
                System.out.println("\nSaturday");
                break;
            case 7:
                System.out.println("\nSunday");
                break;
            default:
                break;
        }

        // Part 5: Basic Calculator
        double num1;
        double num2;
        double result = 0;
        char operation;
        num1 = input.nextDouble();
        num2 = input.nextDouble();
        operation = input.next().charAt(0);
        

        if (operation == '+') {
            result = num1 + num2;
        }
        else if(operation == '-'){
            result = num1 - num2;
        }
        else if(operation == '*'){
            result = num1 * num2;
        }
        else if(operation == '/'){
            if (num2 == 0)System.out.println("Not possible"); 
            result = num1 / num2;
        }

        switch (operation) {
            case '+':
                System.out.println("\nSum: " + result);
                break;
            case '-':
                System.out.println("\nRest: " + result);
                break;
            case '*':
                System.out.println("\nMultiplication: " + result);
                break;
            case '/':
                System.out.println("\nDivision: " + result);
                break;
        
            default:
                break;
        }

        System.out.println("\n Do you want to increment or decrement by one unit? \nIncrement enter something, decrement just click enter");
        input.nextLine(); // Cleaning the buffer
        String answer = input.nextLine();
        input.close();
        if(answer.isEmpty()){
            result--;
            System.out.println(result);
        }
        else{
            result++;
            System.out.println(result);
        }
        




        


    }

}