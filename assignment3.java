public class assignment3 {

    public static void main(String[] args) {
        
        // Assignment: Implementing Arrays in Java
        int[] numbers;
        numbers = new int[5];
        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
        numbers[3] = 40;
        numbers[4] = 50;
        System.out.println(numbers[0]);

        // Part 2. Exercise
        double[] doubleArray = new double[5];
        doubleArray[0] = 3.1416;
        doubleArray[1] = 9.18;
        doubleArray[2] = 14.2;
        doubleArray[3] = 5.55;
        doubleArray[4] = 3.0;
        for(double item:doubleArray){
            System.out.println(item);
        }

        //Part 3. Exercise
        System.out.println();
        int[] integerArray = {1,2,3,4,5};
        integerArray[2] = 7;
        for(int aux:integerArray){
            System.out.println(aux);
        }

        // Part 4. Exercise
        System.out.println();
        int[] multiple3Array = new int[10];
        for (int i = 0; i < multiple3Array.length; i++) {
            multiple3Array[i] = i*3;
        }
        System.out.println(multiple3Array.length);
        for (int i = 0; i < multiple3Array.length; i++) {
            System.out.print(""+multiple3Array[i]+" ");
        }
        


    }

}
