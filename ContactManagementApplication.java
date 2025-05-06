import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Comparator;

class Contact{

    int contactId;
    String name;
    String phoneNumber;
    String email;
    String[] contactTypeOptions = {"Personal", "Professional"};
    String contactType;

    public Contact(){
        contactId = 0;
        name = "Unknown";
        phoneNumber = "XXXXXXXX";
        email = "Unknown";
        contactType = contactTypeOptions[0];
    }

    public Contact(int contactId, String name, String phoneNumber, String email, int contactType){
        this.contactId = contactId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.contactType = this.contactTypeOptions[contactType];
    }

    public static Contact addContactInformation(Scanner sc){
        System.out.println("Enter contact details");
        sc.nextLine();
        System.out.println("Contact ID: ");
        int contactID = sc.nextInt();
        sc.nextLine();
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("Phone number: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Contact type: 0-Personal 1-Professional");
        int contactType = sc.nextInt();

        return new Contact(contactID,name,phoneNumber,email,contactType);
    }

    public void contactInfo(){
        System.out.println("Name: "+name+" Contact ID: "+contactId+" Type: " +contactType);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Contact) {
            return this.name.equals(((Contact) o).name);
        }
        return false;
    }

}


class ContactDirectory{

    // ArrayList to store a list of all contacts
    static ArrayList<Contact> contacts = new ArrayList<>();
    static HashSet<Contact> hscontacts = new HashSet<>();
    static HashMap<String,Contact> hmcontacts = new HashMap<>();

    // Focusing on list functions
    public static void addContactToCollections(Contact C){
        contacts.add(C);
        hscontacts.add(C);
        hmcontacts.put(C.name, C);
    }

    // Making a single function
    public static void updateContactGlobal(Scanner sc){
        // Search by position or by name
        int decision = 0;
        int position;
        String name;
        sc.nextLine();
        System.out.println("Serach by 1.Positon 2.Name");
        decision = sc.nextInt();
        switch (decision) {
            case 1:
                // Position
                position = sc.nextInt();
                contacts.set(position, Contact.addContactInformation(sc));
                hmcontacts.put(contacts.get(position).name,Contact.addContactInformation(sc));
                break;
            case 2:
                // By name
                System.out.println("Enter the name: ");
                name = sc.nextLine();
                hmcontacts.put(name, Contact.addContactInformation(sc));
                for(int i =0;i<contacts.size();i++){
                    if(contacts.get(i).name.equals(name)){
                        contacts.set(i, Contact.addContactInformation(sc));
                    }
                }
                break;
            default:
                break;
        }

    }

    /* 
    public static void updateContact(int position, Scanner sc){
        contacts.set(position, Contact.addContactInformation(sc));
    }

    public static void updateContactHM(Scanner sc){
        sc.nextLine();
        System.out.println("Enter the Contact name: ");
        String name = sc.nextLine();
        if(hmcontacts.get(name) == null){
            System.out.println("404: Contact not found");
        }
        else{
            hmcontacts.put(name,Contact.addContactInformation(sc));
        }
    }
    */

    public static void printingList(){
        Iterator<Contact> iterator = contacts.iterator();
        while (iterator.hasNext()) {
            System.out.println("Contact: " + iterator.next().name);
        }

    }

    public static void sortingList(){
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2){
                return c1.name.compareTo(c2.name);
            }
        });
    }

    // Hash Set functions
    public static void printHashSet(int option){
        if (option == 1) {
            for(Contact c: hscontacts){
                if (c.contactType.equals("Personal")) {
                    c.contactInfo();
                }
            }
        }
        else if(option == 2){
            for(Contact c: hscontacts){
                if (c.contactType.equals("Professional")) {
                    c.contactInfo();
                }
            }
        }
    }

    // Hash Map function
    public static void searchByName(Scanner sc){
        boolean nameMatches = false;
        sc.nextLine();
        do{
            System.out.println("Enter the name: ");
            String name = sc.nextLine();
            if (hmcontacts.containsKey(name)) {
                hmcontacts.get(name).contactInfo();
                nameMatches = true;
            }else{
                System.out.println("404: Contact not found. Try again");
            }
        }
        while (!nameMatches);
        
    }


}

public class ContactManagementApplication {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int option;
        int test;
        boolean isFinished = false;

        do {
            System.out.println("Select an option:\n" + //
                        "1. Add Contact\n" + //
                        "2. Display Unique Contact Types\n" + //
                        "3. Search Contacts by name\n" + //
                        "4. Update Contact Information\n" + //
                        "5. Sort Contacts\n" + //
                        "6. Exit");
            System.out.println("Select an option: 1,2,3,4,5,6");
            try {
                option = input.nextInt();
                if(option == 6)isFinished = true;
                if(option == 1){
                    System.out.println("Adding Contact to List, HashSet and HashMap");
                    // The three collections are capable of this
                    ContactDirectory.addContactToCollections(Contact.addContactInformation(input));
                    // CD.addContactToCollections(C);
                    clearScreen();
                }
                else if(option == 2){
                    System.out.println("Display unique contact types");
                    System.out.println("Contacts: 1-Personal 2-Professional");
                    test = selectOption(input);
                    clearScreen();
                    ContactDirectory.printHashSet(test);
                }
                else if(option == 3){
                    // Search by name hash map
                    clearScreen();
                    System.out.println("Search contact:");
                    ContactDirectory.searchByName(input); 
                }
                else if(option == 4){
                    ContactDirectory.updateContactGlobal(input);
                    // Array List and Hash Map
                    /* 
                    //
                    System.out.println("Update info by: 1-List 2-HashMap");
                    test = selectOption(input);
                    switch (test) {
                        case 1:
                            int index = ContactDirectory.contacts.size()-1;
                            System.out.println("From 1 to "+index);
                            try {
                                input.nextLine();
                                index = input.nextInt();
                                ContactDirectory.updateContact(index, input);
                            } catch (Exception e) {
                                System.out.println("Not a valid input");
                            }
                            clearScreen();
                            break;
                        case 2:
                            ContactDirectory.updateContactHM(input);
                            clearScreen();
                            break;
                        default:
                            break;
                    }
                    // Test */
                }
                else if(option == 5){
                    clearScreen();
                    System.out.println("Sort and display alphabetically");
                    ContactDirectory.sortingList();
                    ContactDirectory.printingList();  
                }
                
            } catch (Exception e) {
                System.out.println("Wrong input, try again: ");
                input.nextLine();
                // TODO: handle exception
            }
        } while (!isFinished);
        input.close();

    }

    public static int selectOption(Scanner sc){
        boolean isValid = true;
        int userInput=0;
        sc.nextLine();
        while(isValid){
            try {
                System.out.println("Enter an option between 1 and 2");
                userInput = sc.nextInt();
                if(userInput >= 1 && userInput<=2){
                    break;
                }
                else{
                    System.out.println("Not in the valid boundaries, try again");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, enter an integer number");
                sc.next();
            }
        }
        System.out.println(userInput);
        return userInput;
    } // End selectOption Class


    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    
} // MAIN CLASS