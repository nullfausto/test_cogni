import java.util.*;


// Library -> Categories -> Books
// [Fantasy, Scifi, Thriller] -> Fantasy - Lord of the ring -> Book details
// Registering by accessing the category, then put the book details
// [0,1,2] -> 1. Name

class Book{
    String title;
    String author;
    int availability=0;

    // Constructor
    public Book(){
        this.title = "Unknown";
        this.author = "Unknown";
    }

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, int availability){
        this.title = title;
        this.author = author;
        this.availability = availability;
    }

    public void bookInfo(){
        System.out.println("Title: "+title+"\nAutor: "+author+"\nAvailability: "+availability);
    }


    public static Book addBookInfo(Scanner sc){
        while (true) {
            try {
                System.out.println("Enter book details");
                sc.nextLine();
                System.out.println("Title: ");
                String title = sc.nextLine();
                System.out.println("Author: ");
                String author = sc.nextLine();
                System.out.println("Availability: ");
                int availability = sc.nextInt();
                title = title.toUpperCase();
                if (!title.trim().isEmpty() && !author.trim().isEmpty()) {
                    return new Book(title,author,availability);
                }
                else{
                    System.out.println("Title can't be empty, try again");
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Wrong input, try again: ");
                sc.nextLine();
            }
        }
    }

    public void reduceAvailability(){
        if(availability <= 0){
            System.out.println("No more books available of this title");
        }
        else{
            availability -= 1;
        }
    }

    public void increaseAvailability(){
        availability += 1;
    }

    @Override
    public String toString(){
        return "Title: "+title;
    }

} // END of Class Book

class Category{

    String categoryType;
    // Structure that will keep books
    HashMap <String,Book> booksByCategory = new HashMap<>();

    // Constructors
    public Category(){
        this.categoryType = "Unknown";
        this.booksByCategory = new HashMap<String,Book>();
    }

    public Category(String categoryType){
        this.categoryType = categoryType;
        this.booksByCategory = new HashMap<>();
    }

    public void categoryInfo(){
        System.out.println("Category: "+categoryType);
    }

    public void searchByName(String title){
        booksByCategory.get(title).bookInfo();
    }

    public void addBookToCategory(Scanner sc){
        Book B = Book.addBookInfo(sc);
        int count = 0;
        // Validation for duplicate books

        // Workaround for duplicates 
        for(int i=0; i<Library.categories.size();i++){
            if (Library.categories.get(i).booksByCategory.get(B.title) != null) {
                count +=1;
            }
        }
        if(count == 0){
            booksByCategory.put(B.title, B);
        }
        else{
            System.out.println("Book already registered in a Category");
        }
        //

        // Working code
        /* 
        if(booksByCategory.get(B.title) == null){
            booksByCategory.put(B.title, B);
        }
        else{
            System.out.println("Book already registered");
        } */
        // End of validation, and Working code
        // booksByCategory.put(B.title, B);
    }

    public void displayBooks(){
        for (Book b : booksByCategory.values()) {
            System.out.println(b.toString());
        }
    }


    // Testing toString
    @Override
    public String toString(){
        return "Category: " +categoryType;
    }


} // END of Class Category


class Library{

    static ArrayList<Category> categories = new ArrayList<>();


    public static void createLibrary(){
        Category C1 = new Category("Fantasy");
        Category C2 = new Category("Science Fiction");
        Category C3 = new Category("Thriller/Suspense");

        categories.add(C1);
        categories.add(C2);
        categories.add(C3);
    }

    public static void addCategory(String name){
        // Logic for no repeating categories
        Category C = new Category(name);
        categories.add(C);
    }

    public static void displayCategories(){
        int aux = 0;
        for (int i = 0; i < categories.size(); i++) {
            aux += 1;
            System.out.println(aux+". "+categories.get(i).toString());
        }
    }

    public static void displayBooksByCategory(int index){
        categories.get(index).displayBooks();
    }

    public static void globalBookSearch(String name){
        boolean isFound = false;
        int aux = 0;
        int counter = 0;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).booksByCategory.get(name)!= null) {
                isFound = true;   
                aux = i;
                counter += 1;
            }
        }
        if (isFound) {
            categories.get(aux).categoryInfo();
            categories.get(aux).searchByName(name);
            // System.out.println(counter);
        }
        else{
            System.out.println("404: Book not found");
        }
        
    }

    public static void getAvailableBook(String name){
        boolean isFound = true;
        int aux = 0;
        int counter = 0;
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).booksByCategory.get(name)!=null){
                categories.get(i).booksByCategory.get(name).reduceAvailability();
                aux = i;
                counter +=1;
            }
        }
        if (isFound) {
            categories.get(aux).categoryInfo();
            categories.get(aux).booksByCategory.get(name).bookInfo();
        }
        else{
            System.out.println("404: Book not found");
        }
    }

    public static void bookGivenBack(String name){
        boolean isFound = true;
        int aux = 0;
        int counter = 0;
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).booksByCategory.get(name)!=null){
                categories.get(i).booksByCategory.get(name).increaseAvailability();
                aux = i;
                counter +=1;
            }
        }
        if (isFound) {
            categories.get(aux).categoryInfo();
            categories.get(aux).booksByCategory.get(name).bookInfo();
        }
        else{
            System.out.println("404: Book not found");
        }
    }

    


}

public class LMS2 {

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        int option = 0;
        boolean isFinished = false;
        Library.createLibrary();
        String categoryName;
        int category;
        String bookName;

        System.out.println("Running the manage library system");
        do {
            System.out.println("Select an option:\n" + //
                        "1. Display Categories\n" + //
                        "2. Add New Category\n" + //
                        "3. Add Book\n" + //
                        "4. Search Book by Category\n" + //
                        "5. Display Books by Category\n" + //
                        "6. Search Book\n" + //
                        "7. Lend a Book\n" + //
                        "8. Increase Availability\n" + //
                        "9. Exit");
            try {
                option = input.nextInt();
                if(option == 9){
                    isFinished = true;
                }
                else if (option == 1) {
                    clearScreen();
                    Library.displayCategories();
                }
                else if(option == 2){
                    System.out.println("Adding a new category: ");
                    input.nextLine();
                    categoryName = input.nextLine();
                    Library.addCategory(categoryName);
                    clearScreen();
                }
                else if(option == 3){
                    // Add Book
                    // Select category
                    System.out.println("Enter the category: ");
                    category = selectOption(input,Library.categories.size());
                    category -= 1;
                    Library.categories.get(category).addBookToCategory(input);
                }
                else if(option == 4){
                    // Search Book by Category
                    System.out.println("Enter the category: ");
                    category = selectOption(input,Library.categories.size());
                    category -= 1;
                    bookName = enterBookName(input);
                    bookName = bookName.toUpperCase();
                    clearScreen();
                    Library.categories.get(category).searchByName(bookName);
                }
                else if(option == 5){
                    // Display Books in a Category
                    System.out.println("Enter the category: ");
                    category = selectOption(input,Library.categories.size());
                    category -= 1;
                    clearScreen();
                    Library.displayBooksByCategory(category);
                }
                else if(option == 6){
                    // Search in all Categories
                    System.out.println("Enter the book's name: ");
                    bookName = enterBookName(input);
                    bookName = bookName.toUpperCase();
                    clearScreen();
                    Library.globalBookSearch(bookName);
                }
                else if(option == 7){
                    // Lending a book
                    System.out.println("Enter the book's name you want to lend: ");
                    bookName = enterBookName(input);
                    bookName = bookName.toUpperCase();
                    clearScreen();
                    Library.getAvailableBook(bookName);
                }
                else if(option == 8){
                    System.out.println("Enter the book's name you want to lend: ");
                    bookName = enterBookName(input);
                    bookName = bookName.toUpperCase();
                    clearScreen();
                    Library.bookGivenBack(bookName);;
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        } while (!isFinished);
        input.close();
    }

    public static int selectOption(Scanner sc,int size){
        int userInput = 0;
        sc.nextLine();
        while (true) {
            try {
                System.out.println("Enter an option between 1 and "+size);
                userInput = sc.nextInt();
                if (userInput >= 1 && userInput <= size) {
                    break;
                }
                else{
                    System.out.println("Not between the valid inputs, try again: ");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, enter an integer number");
                sc.next();
            }
        }
        return userInput;
    }

    public static String enterBookName(Scanner sc){
        sc.nextLine();
        String name;
        while(true){
            try {
                System.out.println("Enter the book name: ");
                name = sc.nextLine();
                name.trim();
                if(name.isEmpty()){
                    System.out.println("Empty string, not a valid input");
                }
                else{
                    break;
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Invalid input, please try again");
                sc.next();
            }
        }
        return name;
    }

    // Function for cleaning the screen
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
}