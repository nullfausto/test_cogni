import java.util.*;

// Book class
 class Book {
        
        String title;
        String author;
        String ISBN;
        int availability;

        // Constructor
        public Book(String title, String author, String ISBN, int availability){
            this.title = title;
            this.author = author;
            this.ISBN = ISBN;
            this.availability = availability;
        }

        public Book(){
            title = "Unknown";
            author = "Unkwnow";
            ISBN = "123";
            availability = 1;
        }

        void bookInfo(){
            System.out.println("Title: "+title+" Author: "+author+" Available: "+availability);
        }

        void takingBook(){
            if(availability > 0){
                availability -= 1;
            }
            else{
                System.out.println("No more books available of: "+this.title);
            }
        }

        // Function for adding parameters
        public static Book addBookParameter(Scanner sc){
            System.out.println("Enter book details");
            sc.nextLine();
            System.out.println("Title: ");
            String title = sc.nextLine();
            System.out.println("Author: ");
            String author = sc.nextLine();
            System.out.println("ISBN: ");
            String ISBN = sc.nextLine();
            System.out.println("Availability: ");
            int availability = sc.nextInt();

            return new Book(title, author, ISBN, availability);
        }
        
} // END of Book class


class EBook extends Book{
    
    String fileFormat;
    int fileSize;

    // Constructor
    public EBook(String title, String author, String fileFormat, int fileSize){
        super.title = title;
        super.author = author;
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
    }

    public EBook(){
        super.title = "Unknown";
        super.author = "Unknown";
    }

    @Override
    public void bookInfo(){
        System.out.println("Title: " + title + " Author: " + author + " [Type: EBook]");
    }

    
    public static EBook addEBookParameter(Scanner sc){
        System.out.println("Enter book details");
        sc.nextLine();
        System.out.println("Title: ");
        String title = sc.nextLine();
        System.out.println("Author: ");
        String author = sc.nextLine();
        System.out.println("File Format: ");
        String fileFormat = sc.nextLine();
        System.out.println("File Size: ");
        int fileSize = sc.nextInt();

        return new EBook(title, author, fileFormat, fileSize);
    }



}
    
// Member class
class Member {
        
        String name;
        int memberId;
        int borrowedBooks;
        boolean canHaveMoreBooks = false;

        // Constructor
        public Member(String name, int memberId, int borrowedBooks){
            this.name = name;
            this.memberId = memberId;
            this.borrowedBooks = 0;
        }

        public Member(){
            this.name = "Unknown";
            this.memberId = 0;
        }

        void memberInfo(){
            System.out.println("Name: "+name+" MemberID: " + memberId+" Books: "+borrowedBooks);
        }

        public static Member addMemberParameter(Scanner sc){
            System.out.println("Enter member details");
            sc.nextLine();
            System.out.println("Name: ");
            String name = sc.nextLine();
            System.out.println("Member ID: ");
            int memberID = sc.nextInt();
            int borrowedBooks=0;

            return new Member(name,memberID,borrowedBooks);
        }

        void borrowBook(){
            if (borrowedBooks == 15) {
                System.out.println("Can't take more books");
                this.canHaveMoreBooks = true;
            }
            else{
                borrowedBooks +=1 ;
            }
        }
    
} // END Member Class

class VIPMember extends Member{

    boolean vipStatus = true;

    public VIPMember(String name, int memberID, int borrowedBooks){
        super.name = name;
        super.memberId = memberID;
        super.borrowedBooks = borrowedBooks;

    }


    @Override
    public void memberInfo(){
        System.out.println("Name: " + super.name + " ID: " + super.memberId + " [VIP Member]"+" Books: "+super.borrowedBooks);
    }


    public static VIPMember addVMemberParameter(Scanner sc){
        System.out.println("Enter member details");
            sc.nextLine();
            System.out.println("Name: ");
            String name = sc.nextLine();
            System.out.println("Member ID: ");
            int memberID = sc.nextInt();
            int borrowedBooks=0;

            return new VIPMember(name,memberID,borrowedBooks);
    }

    @Override
    void borrowBook(){
        if (borrowedBooks == 50) {
            System.out.println("Can't take more books");
            this.canHaveMoreBooks = true;
        }
        else{
            borrowedBooks +=1 ;
        }
    }

} // END VIP Member Class

// Main

public class LibraryManagementSystem {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        int option;
        boolean isFinished = false;
        int typeOfBook = 0;
        int typeOfMember = 0;

        Book[] books = new Book[0];
        Member[] members = new Member[0];

        // Do while cycle for selection
        do {
            System.out.println("Select an option:\n" + //
                        "1. Add Book\n" + //
                        "2. Add Member\n" + //
                        "3. Borrow a Book\n" + //
                        "4. Display Books\n" + //
                        "5. Display Members\n" + //
                        "6. Exit");
            try {
                option = input.nextInt();
                if(option == 6)isFinished = true;
                // Add book to the Library
                if(option == 1){
                    System.out.println("Adding book...");
                    System.out.println("Select the type of book: 1-Book 2-EBook");
                    input.nextLine();
                    typeOfBook = input.nextInt();
                    switch (typeOfBook) {
                        case 1:
                            books = Arrays.copyOf(books, books.length+1);
                            books[books.length-1] = Book.addBookParameter(input);
                            break;
                        case 2: 
                            books = Arrays.copyOf(books, books.length+1);
                            books[books.length-1] = EBook.addEBookParameter(input);
                        default:
                            break;
                    }
                }
                if(option == 2){
                    System.out.println("Adding member");
                    System.out.println("Select member status. 1. VIP \t 2. No VIP");
                    input.nextLine();
                    typeOfMember = input.nextInt();
                    switch (typeOfMember) {
                        case 1:
                            members = Arrays.copyOf(members, members.length+1);
                            members[members.length-1] = VIPMember.addVMemberParameter(input);
                            break;
                        case 2:
                            members = Arrays.copyOf(members, members.length+1);
                            members[members.length-1] = Member.addMemberParameter(input);
                            break;
                        default:
                            break;
                    }
                }
                if(option == 3){
                    System.out.println("Borrowing a book...");
                    // Select a member and a book
                    if(books.length == 0 && members.length==0)System.out.println("No books and no members registered");
                    else if(books.length==0)System.out.println("No books registered");
                    else if(members.length==0)System.out.println("No members registered");
                    else{   
                        System.out.println("Enter a member, select from 1 to "+members.length);
                        members[input.nextInt()-1].borrowBook();
                        System.out.println("Enter a book to borrow, select from 1 to :"+books.length);
                        books[input.nextInt()-1].takingBook();
                    }
                }
                if(option == 4){
                    if (books.length == 0) {
                        System.out.println("No books registered");
                    }
                    else{
                        System.out.println("Total books: "+books.length);
                        for(int i =0; i<books.length;i++){
                            if(books[i].availability > 0){
                                books[i].bookInfo();
                            }
                        }
                    }
                }
                if (option == 5) {
                    if (members.length == 0) {
                        System.out.println("No members registered");
                    }
                    else{
                        System.out.println("Total members: "+members.length);
                        for (int i = 0; i < members.length; i++) {
                            members[i].memberInfo();   
                        }
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Incorrect input try again: ");
                input.nextLine();
            }

        } while (!isFinished);
        // END of Do-while cycle for selection

        input.close();

    } // Main


    
    



}