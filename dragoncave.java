import java.util.*;

public class dragoncave {

    public static void main(String[] args) {
        
        // Output for setting up the dialog
        System.out.println("You are in a land full of dragons. In front of you,\n" + //
                        "You see two caves. In one cave, the dragon is friendly\n" + //
                        "and will share his treasure with you. The other dragon\n" + //
                        "is greedy and hungry and will eat you on sight.\n" + //
                        "Which cave will you go into? (1 or 2)");

        boolean goodScenario = true;
        // Making the decisions arbitrary
        int safety = (int)(Math.random()*11);
        if (safety < 5) {
            goodScenario = false;
        }
        
        Scanner input = new Scanner(System.in);
        int decision;
        boolean end = true;
        boolean isValid = false;
        while (!isValid && end) {
            try {
                decision = input.nextInt();
                if (decision == 1 && goodScenario) {
                    System.out.println("You approach the cave...\n" + //
                        "It is dark and spooky, but..\n" + //
                        "You get a glimpse of something shiny at the end!\n" + //
                        "There is a dragon waiting for you...\n" + //
                        "Making you the rightful earl of its treasure");
                        end = false;
                }
                else if (decision == 1 && goodScenario==false) {
                    System.out.println("You approach the cave…\n" + //
                                                "It is dark and spooky…\n" + //
                                                "A large dragon jumps out in front of you!\n" + //
                                                "He opens his jaws and…\n" + //
                                                "Gobbles you down in one bite!");
                                                end = false;
                }
                else if (decision == 2 && goodScenario){
                    System.out.println("You approach the cave...\n" + //
                        "It is dark and spooky, but..\n" + //
                        "You get a glimpse of something shiny at the end!\n" + //
                        "There is a dragon waiting for you...\n" + //
                        "Making you the rightful earl of its treasure");
                        end = false;
                }
                else if(decision == 2 && goodScenario==false){
                    System.out.println("You approach the cave…\n" + //
                                                "It is dark and spooky…\n" + //
                                                "A large dragon jumps out in front of you!\n" + //
                                                "He opens his jaws and…\n" + //
                                                "Gobbles you down in one bite!");
                                                end = false;
                }
            } catch (Exception e) {
                System.out.println("Error: enter a valid input");
                input.nextLine();
            }
        }


    }
}