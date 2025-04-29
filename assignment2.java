import java.nio.channels.Pipe.SourceChannel;
import java.util.*;

public class assignment2{

    public static void main(String[] args){

        
        // Assignment 1: using for loops
        // Print numbers
        for(int i=1;i<=10;i++){
            System.out.print(""+i+" ");
        }

        int sum = 0;
        // Calculate sum
        for (int i = 1; i <=10; i++) {
            sum += i;   
        }
        System.out.println("\n"+sum);

        Scanner input = new Scanner(System.in);
        int inputMultiplitication = input.nextInt();
        // Print multiplication table
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%d x %d = %d \n",inputMultiplitication,i,i*inputMultiplitication);
        }

        // Assignment 2: using while loops
        // Commenting the enhacements for not making a mess with the while loops
        //System.out.println("Enter something for digit sum, else enter No");
        //String decision = input.next();
        //while (!decision.equals("No")) {
            
        System.out.println("\nEnter the number:");
        int positiveInteger = input.nextInt();
        int resDivision = 0;
        int resModule = 0;
        sum = 0;
        boolean hasDigits = true;
        if (positiveInteger < 0) {
            System.out.println("Enter a positive integer number");
        }
        else{         
                while(hasDigits){//Start while
                    resDivision = positiveInteger/10; 
                    resModule = positiveInteger%10;
                    positiveInteger = resDivision;   
                    sum += resModule;
                    if (resDivision == 0) {
                    hasDigits = false;
                    }
                }//End while
                System.out.println(sum);
                //decision = input.next();
            
        }
        
   // }//End while
        
        

        //Assignment 3: Using Do-While Loops
        System.out.println("\nWelcome to the Calculator! \n");
        boolean flag = true;
        do {
            System.out.println("Please select an operation: \n1.Addition\n2.Subtraction\n3.Multiplication\n4.Division\n5.Exit");
            System.out.print("Enter your operation: ");
            int operation = input.nextInt();
            if (operation == 5) {
                flag = false;
            }
            else{
                System.out.printf("Enter first number: ");
                int firstNum = input.nextInt();
                System.out.printf("Enter second number: ");
                int secondNum = input.nextInt();
                int res = 0;
                switch (operation) {
                    case 1:
                        res = firstNum+secondNum;
                        System.out.println(res);
                        break;
                    case 2:
                        res = firstNum-secondNum;
                        System.out.println(res);
                        break;
                    case 3:
                        res = firstNum*secondNum;
                        System.out.println(res);
                        break;
                    case 4:
                        res = firstNum/secondNum;
                        System.out.println(res);
                        break;   
                    default:
                        break;
                }
            }


        } while (flag);

        input.close();
    }//Main

}