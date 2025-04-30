import java.util.*;

public class guessthenumber{
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        boolean endGame = false;
        boolean tryAgain = true;
        int difficulty;
        int randomNumber;
        int totalAttempts;
        initialScreen();
        input.nextLine();
        clearScreen();

    do{
        
        System.out.println("Select the difficulty: 1. Easy 2. Medium 3. Hard");
        difficulty = input.nextInt();
        randomNumber = selectDifficulty(difficulty);

        int guess = 0;
        totalAttempts = 1;

        do{
            //clearScreen();
            System.out.println("Enter a number between the range selected: ");
            guess = input.nextInt();
            if (guess > randomNumber) {
                System.out.println("You value is higher than the value");
                totalAttempts++;
            }
            else if(guess < randomNumber){
                System.out.println("You value is lower than the value");
                totalAttempts++;
            }
            else if(guess == randomNumber){
                System.out.println("YOU WIN!");
                System.out.println("Total attempts: "+totalAttempts);
                endGame = true;
            }
        }
        while(!endGame);
        
        // clearScreen();
            try {
                System.out.println("Try Again? Y/N");
                input.nextLine();
                String another = input.nextLine();
                if (another.equals("N")) {
                    tryAgain = false;
                }
                endGame = false;
            } catch (Exception e) {
                // TODO: handle exception
                tryAgain = false;
            }
    }// Principal DO
    while(tryAgain);

        //System.out.println(randomNumber);
        input.close();
        
    }

    public static void initialScreen(){
        System.out.println("Welcome to guess the number!");
        // Instructions for the user, select difficulty, then start!
        System.out.println("You are going to try to guess a number \n" + //
        "Please keep in mind that your attempts and time spent are being recorded. \n" + //
        "Just enter the number between the selected range!\n" + //
        "Have fun!"    
        );
        System.out.println("Enter to start");
    }

    public static int selectDifficulty(int difficulty){
        if (difficulty == 1) {
            System.out.println("Range 0-10");
            return (int)(Math.random()*11);
        }
        else if(difficulty == 2){
            System.out.println("Range 0-100");
            return (int)(Math.random()*101);
        }
        else if(difficulty == 3){
            System.out.println("Range 0-1000");
            return (int)(Math.random()*1001);
        }
        else{
                System.out.println("Invalid difficulty: setting to default range 1-10");
                return (int)(Math.random()*11);
        }
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    


}