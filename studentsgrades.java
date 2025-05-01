import java.util.*;

public class studentsgrades {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        double averageGrade = 0;
        // Array for storing grades
        double[] grades = new double[10];

        // Note validate that grade is not greater than 10.0
        for (int i = 0; i < grades.length; i++) {
                grades[i] = input.nextDouble();
        }

        // Calculate the average grade
        for (int i = 0; i < grades.length; i++) {
            averageGrade += grades[i];
            //System.out.println(averageGrade);
        }

        for (double d : grades) {
            System.out.println(d);
        }

        averageGrade = averageGrade/grades.length;

        // Sorting the array
        Arrays.sort(grades);
        double lowestGrade = grades[0];
        double highestGrade = grades[9];



        System.out.println("Average grade: "+averageGrade);
        System.out.println("Lowest grade: "+lowestGrade);
        System.out.println("Highest grade: "+highestGrade);
        
        input.close();

    }

    


}

