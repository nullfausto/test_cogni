/* Instructions:
 * 
 * Added a new folder call lib with two jar files:
 * 1. gson-2.10.1.jar
 * 2. json-20231013.jar
 * 
 * Libraries for creating JSON Files to save system data
 * 
 * 
 * Java Configure: Classpath, then select libraries and add both jar files
 * 
 * Once that is done, the compile instructions as I am using Linux:
 * javac -cp ".:./lib/gson-2.10.1.jar:./lib/json-20231013.jar" PetAdoptionCenter.java 
 * Run instructions:
 * java -cp ".:./lib/gson-2.10.1.jar:./lib/json-20231013.jar" PetAdoptionCenter
 * 
 * 
 * Running for the first time will throw a message.
 * Will create a file called people.json where all the data is stored.
 * First create users, exit the program and then register a pet.
 * After registering a pet, select option 5 to associate a Pet to an Adopter.
 * Once they are related Pet will be added to a list for the Adopter.
 */


import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
// For creating a JSON File
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
// For reading the JSON File
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Pet{

    protected String petId;
    protected String name;
    protected String species;
    protected int age;
    protected String breed;
    protected boolean adoptionStatus;
    protected static Pattern patternPetID = Pattern.compile("\\d{4}$");

    // Constructor
    public Pet(String petId, String name, String species, int age, String breed, boolean adoptionStatus){

        this.petId = petId;
        this.name = name;
        this.species = species;
        this.age = age;
        this.breed = breed;
        this.adoptionStatus = adoptionStatus;

    }

    public Pet(){
    }

    // Getters
    public String getPetid(){
        return this.petId;
    }

    public String getName(){
        return this.name;
    }

    public String getSpecie(){
        return this.species;
    }

    public void getSpecies(){
        System.out.println(this.species);
    }

    public void displayPetInfo(){
        System.out.println("Pet ID: "+petId+"\nName: "+name+"\nSpecie: "+species);
    }

    public int getPetage(){
        return this.age;
    }

    public String getBreed(){
        return this.breed;
    }

    public boolean getAdoptionstatus(){
        return this.adoptionStatus;
    }

    public boolean availableForadoption(){
        return this.adoptionStatus = false;
    }

    // Setters

    public void setpetID(String petId){
        this.petId = petId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setSpecies(String species){
        this.species = species;
    }

    public void setBreed(String breed){
        this.breed = breed;
    }

    public void setAdoptionStatus(boolean flag){
        this.adoptionStatus = flag;
    }

    // Registering a new pet
    public static Pet registerePet(Scanner sc){

        String petId = "";
        String name = "";
        int specie = 0;
        int age = 0;
        String breed = "";
        boolean adoptionStatus = true;
        Matcher matcherPetID;

        while (petId.isEmpty()) {
            System.out.println("Enter the Pet ID: (4 digits long) ");
            petId = sc.nextLine().trim();
            matcherPetID = patternPetID.matcher(petId);
            if(!matcherPetID.matches()){
                System.out.println("Not a valid ID");
                petId = "";
            }
            else if(petId.isEmpty()){
                System.out.println("Error: Pet ID can't be empty");
            }
        }
        
        while(name.isEmpty()){
            System.out.println("Enter the name");
            name = sc.nextLine().trim();
            if(name.isEmpty()){
                System.out.println("Invalid input, must contain something");
            }
        }

        boolean validSpecie = false;
        while(!validSpecie){
            System.out.println("Enter the specie: 1. Canine 2. Feline 3. Bird");
            try {
                specie = Integer.parseInt(sc.nextLine());
                if(specie <= 0 || specie > 3){
                    System.out.println("Error: option is not valid. \nNo negative numbers nor integers bigger than 3 allowed.");
                }
                else{
                    validSpecie = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number for age.");
            }
        }

        boolean validAge = false;
        while (!validAge) {
            System.out.println("Enter the age:");
            try {
                age = Integer.parseInt(sc.nextLine());
                if(age <= 0 || age >= 30){
                    System.out.println("Error: age is not valid.");
                }
                else{
                    validAge = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number for age.");
            }
        }

        while(breed.isEmpty()){
            System.out.println("Enter the breed: ");
            breed = sc.nextLine().trim();
            if(breed.isEmpty()){
                System.out.println("Error: can't be empty.");
            }
        }

        if(specie == 1){
            return new Dog(petId, name, age, breed, adoptionStatus);
        }
        else if(specie == 2){
            return new Cat(petId, name, age, breed, adoptionStatus);
        }
        else{
            return new Bird(petId, name, age, breed, adoptionStatus);
        }
        

        
    }

}

class Dog extends Pet{

    String specie = "Canine";

    public Dog(String petID, String name, int age, String breed, boolean adoptionStatus){
        super.petId = petID;
        super.name = name;
        super.species = this.specie;
        super.age = age;
        super.breed = breed;
        super.adoptionStatus = adoptionStatus;
    }
}

class Cat extends Pet{

    String specie = "Feline";

    public Cat(String petID, String name, int age, String breed, boolean adoptionStatus){
        super.petId = petID;
        super.name = name;
        super.species = this.specie;
        super.age = age;
        super.breed = breed;
        super.adoptionStatus = adoptionStatus;
    }
}

class Bird extends Pet{

    String species = "Bird";

    public Bird(String petID, String name, int age, String breed, boolean adoptionStatus){
        super.petId = petID;
        super.name = name;
        super.species = this.species;
        super.age = age;
        super.breed = breed;
        super.adoptionStatus = adoptionStatus;
    }
}

class Adopter{
    
    private String adopterId;
    private String name;
    private ArrayList<String> contactInfo;
    private ArrayList<Pet> adoptedPets;

    private static Pattern patternAdopterID = Pattern.compile("\\d{5}$");
    private static Pattern patternName = Pattern.compile("^[A-Za-z]+(?: [A-Za-z]+)?$");
    private static Pattern patternPhoneNumber = Pattern.compile("\\d{10}$");
    private static Pattern patternEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    // Constructor 
    public Adopter(String adopterId, String name, ArrayList<String> contactInfo, ArrayList<Pet> adoptedPets){
        this.adopterId = adopterId;
        this.name = name;
        this.contactInfo = contactInfo;
        this.adoptedPets = adoptedPets;
    }

    public Adopter(){
    }

    // Getters
    public String getName(){
        return this.name;
    }

    public String getAdopterID(){
        return this.adopterId;
    }

    public ArrayList<String> getContactInfo(){
        return this.contactInfo;
    }

    public ArrayList<Pet> getAdoptedPets(){
        return this.adoptedPets;
    }

    // Setters
    public void setName(String name){ 
        this.name = name; 
    }

    public void setAdopterID(String adopterID){
        this.adopterId = adopterID;
    }

    public void setContactInfo(ArrayList<String> c){
        this.contactInfo = c;
    }

    public void setAdoptedPets(ArrayList<Pet> pets){
        this.adoptedPets = pets;
    }

    public int getTotalPets(){
        return this.adoptedPets.size();
    }

    public void displayInfo(){
        System.out.println("Name: "+this.name+"\nAdopter ID: "+this.adopterId);
    }

    public void displayAmountOfPets(){
        System.out.println(adoptedPets.size());
    }

    public void displayContactInfo(){
        for (String s : contactInfo) {
            System.out.println(s);
        }
    }

    public void displayAdoptedPets(){
        if(adoptedPets.size()!=0){
            System.out.println(adoptedPets.size());
            for(int i=0;i<adoptedPets.size();i++){
                adoptedPets.get(i).displayPetInfo();;
            }
        }
        else{
            System.out.println("Hasn't adopted any pets. ");
        }
    }

    @Override
    public String toString(){
        return "Adopter[adopterID=" +adopterId +", name= "+name+", contactInfo= "+contactInfo+", pets=" +adoptedPets+"]";
    }

    public void adoptPet(Pet P){
        adoptedPets.add(P);
        P.availableForadoption();
    }

    public static Adopter registerAdopterInfo(Scanner sc){
        final int MAX_ATTEMPTS = 10;
        int attempts = 0;
        String name;
        String adopterID;
        String address;
        String email;
        String phoneNumber;
        boolean isRegistered=false;
        boolean isCorrect = false;

        Matcher matcherName;
        Matcher matcherAdopterID;
        Matcher matcherEmail;
        Matcher matcherPhoneNumber;

        sc.nextLine();
        while(attempts<MAX_ATTEMPTS){
            try {
                System.out.println("Enter the adopter's name");
                name = sc.nextLine();
                System.out.println("Enter the Adopter's ID. String must be only 5 digits");
                do {
                    adopterID = sc.nextLine(); 
                    isRegistered = true; // Add Validation if ID is already registered
                } while (!isRegistered);
                System.out.println("Enter the address: ");
                do {
                    address = sc.nextLine();
                    if (address.isEmpty()) {
                        System.out.println("Empty address, not valid. Try again.");
                    }
                    else{
                        isCorrect = true;
                    }
                } while (!isCorrect);
                System.out.println("Enter the email: (example@domain.com)");
                email = sc.nextLine();
                System.out.println("Enter the phone number: (10 digits only)");
                phoneNumber = sc.nextLine();
                matcherName = patternName.matcher(name);
                matcherAdopterID = patternAdopterID.matcher(adopterID);
                matcherEmail = patternEmail.matcher(email);
                matcherPhoneNumber = patternPhoneNumber.matcher(phoneNumber);
                if(matcherName.matches() && matcherAdopterID.matches() && matcherEmail.matches() && matcherPhoneNumber.matches()){
                    ArrayList<String> contactInfo = new ArrayList<>();
                    contactInfo.add(name);
                    contactInfo.add(address);
                    contactInfo.add(email);
                    contactInfo.add(phoneNumber);
                    return new Adopter(adopterID, name, contactInfo, null);        
                }
                else{
                    if(!matcherName.matches()){
                        System.out.println("Error. Not a valid name.");
                    }
                    if(!matcherAdopterID.matches()){
                        System.out.println("Error. ID must be 5 digits long.");
                    }
                    if(!matcherEmail.matches()){
                        System.out.println("Error. E-mail must contain @domain.com");
                    }
                    if(!matcherPhoneNumber.matches()){
                        System.out.println("Error. Not a valid phone number (10 digits long).");
                    }
                    attempts++;
                }
                
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                sc.nextLine(); 
                attempts++;
            }
        }

        System.out.println("Too many failed attempts. Exiting.");
        return null; 


    } // Registering Adopter Info

}

class PetAdoptionManager{
    
    HashMap <String, Adopter> people = new HashMap<>();
    HashMap <String, Pet> pets = new HashMap<>();

    public PetAdoptionManager(){
        this.people = new HashMap<>();
        this.pets = new HashMap<>();
    }

    public void addPeople(Adopter A){
        people.put(A.getAdopterID(), A);
    }

    public boolean alreadyExists(Adopter A){
        if(people.get(A.getAdopterID())==null){
            addPeople(A);
            return false;  
        }
        else{
            return true;
        }
    }

    public void addPet(Pet pet){
        pets.put(pet.getPetid(), pet);
    }

    public boolean petAlreadyExists(Pet p){
        if(pets.get(p.getPetid())==null){
            addPet(p);
            return false;
        }
        else{
            return true;
        }
    }

    public void associatePetToAdopter(Adopter A, Pet P){
        people.get(A.getAdopterID()).adoptPet(P);
    }

} // End of Class PetAdoptionManager

public class PetAdoptionCenter {

    // HashMap for Adopters, since the search will be <String AdopterID, Adopter>

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }    


    private static JSONObject findMemberJson(JSONArray peopleArray, String memberID) {
        for (int i = 0; i < peopleArray.length(); i++) {
            JSONObject person = peopleArray.getJSONObject(i);
            if (memberID.equals(person.optString("adopterId"))) {
                return person;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        PetAdoptionManager PAM = new PetAdoptionManager();

        // File path JSON
        String filePath = "people.json";
        JSONArray peopleArray;

        Gson gson = new Gson();
        Path filePa = Paths.get("people.json");
        // Checking if file exists before reading
        if(!Files.exists(filePa)){
            System.out.println("First time executing...");
        }
        try {
            String jsonContent = Files.readString(filePa);
            Type type = new TypeToken<List<Adopter>>(){}.getType();
            List<Adopter> objects = gson.fromJson(jsonContent, type);
            for(Adopter ad : objects){
                PAM.addPeople(ad);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            peopleArray = new JSONArray(content);
        } catch (IOException e) {
            peopleArray = new JSONArray();
        }

        JSONObject newPerson;
        JSONObject newPet;

        Scanner input = new Scanner(System.in);
        int option = 0;
        boolean isFinished = false;
        String memberID;
        String petID;
        boolean flag;

        
        System.out.println("Running the manage library system");
        do {
            System.out.println("Select an option:\n" + //
                        "1. Register a New Member\n" + //
                        "2. Display Member Information\n" + //
                        " 2.1 Display Member Information\n" + //
                        " 2.2 Display Pets by Member Information\n" +//
                        "3. Register a New Pet\n" + //
                        "4. Display Pet\n" + //
                        "5. Adopt Pet\n" + //
                        "6. Display Pets by Member ID\n" + //
                        "7. Exit");
            try {
                option = input.nextInt();
                if(option == 7){
                    isFinished = true;
                }
                else if (option == 1) {
                    Adopter A = Adopter.registerAdopterInfo(input);
                    flag = PAM.alreadyExists(A);
                    if (!flag) {
                        newPerson = new JSONObject();
                        newPerson.put("adopterId",A.getAdopterID());
                        newPerson.put("name",A.getName());
                        newPerson.put("contactInfo",A.getContactInfo());
                        newPerson.put("adoptedPets",A.getAdoptedPets());
                        peopleArray.put(newPerson);
                        try(FileWriter file = new FileWriter(filePath)){
                            file.write(peopleArray.toString(2));
                            System.out.println("Added new person to "+filePath);
                        }
                        catch(IOException e){
                            e.printStackTrace();
                        }
                        System.out.println("\nUser registered successfully!\n");
                    }
                    else{
                        System.out.println("\nID already in use.\n");
                    }
                }
                else if(option == 2){
                    // Logic to display member's pets or member info
                    input.nextLine();
                    System.out.println("Enter the Member ID: ");
                    memberID = input.nextLine();
                    clearScreen();
                    PAM.people.get(memberID).displayContactInfo();
                    System.out.println();
                }
                else if(option == 3){
                    input.nextLine();
                    Pet P = Pet.registerePet(input);
                    flag = PAM.petAlreadyExists(P);
                    if (!flag) {
                        System.out.println("\nPet registered successfully!\n");
                    }
                    else{
                        System.out.println("\nID already in use for Pet.\n");
                    }
                }
                else if(option == 4){
                    input.nextLine();
                    System.out.println("Enter the Pet ID: ");
                    petID = input.nextLine();
                    clearScreen();
                    PAM.pets.get(petID).displayPetInfo();
                    System.out.println();
                }
                else if(option == 5){
                    
                    input.nextLine();
                    System.out.println("Enter the Member ID: ");
                    memberID = input.nextLine();
                    System.out.println("Enter the Pet ID");
                    petID = input.nextLine();
                    PAM.people.get(memberID).adoptPet(PAM.pets.get(petID));
                    Pet P = PAM.pets.get(petID);
                    newPet = new JSONObject();
                    newPet.put("petId", P.getPetid());
                    newPet.put("name", P.getName());
                    newPet.put("species", P.getSpecie());
                    newPet.put("age", P.getPetage());
                    newPet.put("breed", P.getBreed());
                    newPet.put("adoptionStatus", P.getAdoptionstatus());

                    JSONObject mmJson = findMemberJson(peopleArray, memberID);
                    if (mmJson != null) {
                        JSONArray adoptedPetsArray = mmJson.optJSONArray("adoptedPets");
                        if (adoptedPetsArray == null) {
                            adoptedPetsArray = new JSONArray();
                        }
                    adoptedPetsArray.put(newPet);
                    mmJson.put("adoptedPets", adoptedPetsArray);
                    Files.write(Paths.get("people.json"), peopleArray.toString(2).getBytes());
                    } 
                    else {
                        System.out.println("Member not found in JSON data");
                    }
                }
                else if(option == 6){
                    input.nextLine();
                    System.out.println("Display adopted pets by Member: ");
                    System.out.println("Enter the Member ID: ");
                    memberID = input.nextLine();
                    PAM.people.get(memberID).displayAdoptedPets();;
                }
                else if(option == 7){

                }
                else if(option == 8){

                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        } while (!isFinished);
        input.close();
    } // END OF MAIN
}