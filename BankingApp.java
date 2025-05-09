import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.*;

class BankAccount{
    String accountNumber;
    String accountHolder;
    double balance;

    private static Pattern patternAN = Pattern.compile("\\d{10}$");
    private static Pattern patternAH = Pattern.compile("^[A-Za-z]+(?: [A-Za-z]+)?$");

    private static final double MAX_AMOUNT = 5_000_000;

    public BankAccount(){
        this.accountNumber = "1234567890";
        this.accountHolder = "Default User";
        this.balance = 0.0;
    }

    public BankAccount(String an, String ah, double balance){
        this.accountNumber = an;
        this.accountHolder = ah;
        this.balance = balance;
    }

    public void displayAccountInfo(){
        System.out.println("Account number: "+accountNumber+"\nAccount Holder: " +accountHolder+"\nFunds: " + balance);    
    }

    public void displayAccountNumber(){
        System.out.println("Account number:"+accountNumber);
    }


    public static BankAccount addBankAccountInfo(Scanner sc) {
        final int MAX_ATTEMPTS = 5; 
        int attempts = 0;
        String accountNumber;
        String accountName;
        Matcher matcherAN;
        Matcher matcherAH;
    
        sc.nextLine();
        while (attempts < MAX_ATTEMPTS) {
            try {
                System.out.println("Enter the Account Number (10 digits):");
                accountNumber = sc.nextLine();
                System.out.println("Enter the Account Holder Name (alphabetic only):");
                accountName = sc.nextLine();
    
                matcherAN = patternAN.matcher(accountNumber);
                matcherAH = patternAH.matcher(accountName);
    
                if (matcherAN.matches() && matcherAH.matches()) {
                    return new BankAccount(accountNumber, accountName, 0);
                } else {
                    if (!matcherAN.matches()) {
                        System.out.println("Error: Account number must be 10 digits.");
                    }
                    if (!matcherAH.matches()) {
                        System.out.println("Error: Name must contain only letters.");
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
    }

    // Increasing the balance by receiving funds
    public void increaseFunds(double amount) throws AmountFundsExceededException {
        double totalAmount = balance + amount;
        if(totalAmount > MAX_AMOUNT){
            throw new AmountFundsExceededException(
                "Execution not possible. Adding: "+amount+" exceeds the maximum balance. Maximum balance: " + MAX_AMOUNT
            );
        }
        System.out.println("Balance before funding: "+balance);
        balance = totalAmount;
        System.out.println("Balance updated: "+balance);
    }

    public void decreaseFunds(double withdrawal) throws InsufficientFundsException{
        double totalAmount = balance - withdrawal;
        if(totalAmount < 0){
            throw new InsufficientFundsException(
              "Execution not possible. Insufficient funds."  
            );
        }
        System.out.println("Balance before funding: "+balance);
        balance = totalAmount;
        System.out.println("Balance updated: "+balance);
    }

}

class Bank{

    // Holds all the clients

    static HashMap<String,BankAccount> accounts = new HashMap<>();

    // Create an Account
    public static void createAccount(Scanner sc){
        // Validate if it already exists
        BankAccount B = BankAccount.addBankAccountInfo(sc);
        if (!validateIfAccountAlreadyExists(B.accountNumber)) {
            accounts.put(B.accountNumber, B);
        }
        else{
            System.out.println("Account Number already in use");
        }
    }

    public static boolean validateIfAccountAlreadyExists(String accountNumber){
        if(accounts.get(accountNumber) == null){
            return false;
        }
        else{
            return true;
        }
    }

    public static void displayAccount(String accountnumber){
        if (accounts.get(accountnumber)!=null) {
            accounts.get(accountnumber).displayAccountInfo(); 
        }
        else{
            System.out.println("404: Not found. Account does not match any register.");
        }
    }
    
    public static void displayAccountsNumbers(){

        if(accounts.isEmpty()){
            System.out.println("Not Accounts were previously registered.");
        }
        else{
            for(BankAccount b : accounts.values()){
                b.displayAccountNumber();
            }
        } 
    }

    // Check balance

    // Deposit funds
    public static void addFundsToAccount(String ac, double deposit) throws AmountFundsExceededException{
        accounts.get(ac).increaseFunds(deposit);
    }
    
    // Withdrawal funds
    public static void decreaseFundsToAccount(String ac, double withdrawal) throws InsufficientFundsException{
        accounts.get(ac).decreaseFunds(withdrawal);
    }

}


// Custom Exceptions Classes
class AmountFundsExceededException extends Exception {
    public AmountFundsExceededException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception{
    public InvalidAmountException(String message){
        super(message);
    }
}

class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String message){
        super(message);
    }
}


public class BankingApp {

    public static void main(String[] args){

        int selection = 0;
        Scanner input = new Scanner(System.in);
        boolean isFinished = false;
        String accNum;
        double deposit;

        System.out.println("Welcome to the Banking App: ");

        do {
            System.out.println("Select an option:\n" + //
                        "1. Create a New Bank Account\n" + //
                        "2. Display an Account Balance\n" + //
                        "3. Deposit to an Account\n" + //
                        "4. Withdraw Funds\n" + //
                        "5. Display Accounts Registered\n" + //
                        "6. Exit");
            try {
                selection = input.nextInt();
                if(selection == 6){
                    isFinished = true;
                }
                else if(selection == 1){
                    System.out.println("Creating Bank Account");
                    Bank.createAccount(input);
                    clearScreen();
                    System.out.println("Account Created Successfully!");
                }
                else if(selection == 2){
                    System.out.println("Display Account Information");
                    accNum = enterString(input);
                    Bank.displayAccount(accNum);
                }
                else if(selection == 3){
                    System.out.println("Adding funds to an account: ");
                    accNum = enterString(input);
                    System.out.println("Enter the amount: ");
                    deposit = enterDouble(input);
                    Bank.accounts.get(accNum).increaseFunds(deposit);
                }
                else if(selection == 4){
                    System.out.println("Withdrawal from an account: ");
                    accNum = enterString(input);
                    System.out.println("Enter the amount: ");
                    deposit = enterDouble(input);
                    Bank.accounts.get(accNum).decreaseFunds(deposit);;
                }
                else if(selection == 5){
                    System.out.println("Withdrawal from an Account");
                    clearScreen();
                    Bank.displayAccountsNumbers();
                }
            } catch (Exception e) {
                System.out.println(e);
                input.nextLine();
            }
        } while (!isFinished);

        input.close();

    }

    // Validate String Inputs
    public static String enterString(Scanner sc){
        sc.nextLine();
        String auxString;
        while(true){
            try {
                System.out.println("Enter the Account Number: ");
                auxString = sc.nextLine();
                auxString.trim();
                if(auxString.isEmpty()){
                    System.out.println("Empty string, not a valid input");
                }
                else{
                    break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please try again");
                sc.next();
            }
        }
        return auxString;
    }

    public static double enterDouble(Scanner sc) throws InvalidAmountException{
        try {
            double amount = sc.nextDouble();
            if (amount <= 0) {
                throw new InvalidAmountException("Amount must be positive.");
            }
            return amount;
        } catch (InputMismatchException e) {
            sc.nextLine();
            throw new InvalidAmountException("Invalid number format.");
        }
    }


    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    
}