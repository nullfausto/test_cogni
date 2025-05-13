import java.util.*;

class Player{
    protected String name;
    protected int health;
    protected int attackPower;
    private Random random;
    private boolean activeDefense;

    public Player(){
        this.name = "Unknown";
        this.health = 0;
        this.attackPower = 0;
    }

    public Player(String name, int health, int attackPower){
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    // Getters and setters
    public void takeDamage(int damage){
        this.health -= damage;
    }

    public void defendAttack(int damage){
        this.random = new Random();
        int result = random.nextInt(3) + 1;
        if (result == 1) {
            this.health -= damage;
            System.out.println("Defending wasn't effective. Health: "+this.health);
        }
        else if(result == 2){
            this.health -= (damage/2);
            System.out.println("You received half damage. Health: "+this.health);
        }
        else{
            System.out.println("Attack fully stopped! Health: "+this.health);
        }
    }

    public boolean activateDefense(){
        return this.activeDefense = true;
    }

    public boolean deactivateDefense(){
        return this.activeDefense = false;
    }

    public String getName(){
        return this.name;
    }

    public boolean isDefenseActive(){
        return this.activeDefense;
    }

    public int getAttackPower(){
        return this.attackPower;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public int getHealth(){
        return this.health;
    }

    public void healing(){
        if (this.health <= 90) {
            this.health += 10;
        }
        else{
            System.out.println("Full health, can not heal");
        }
    }

    public void displayInfo(){
        System.out.println("Name: " + this.name +" Health: "+this.health+" Attack Power: "+this.attackPower);
    }

}

// Creating characters classes
class Warrior extends Player{
    private int extraStrength = 10;
    private Random random;

    public Warrior(String name, int health, int attackPower){
        super.name = name;
        super.health = health;
        super.attackPower = attackPower;
    }

    @Override
    public int getAttackPower(){
        return super.getAttackPower() + extraStrength;
    }

    @Override
    public void defendAttack(int damage){
        this.random = new Random();
        int result = random.nextInt(2) + 1;
        if (result == 1) {
            super.health -= (damage/2);
            System.out.println("You received half damage. Health: "+super.health);
        }
        else{
            System.out.println("Attack fully stopped! Health: "+this.health);
        }
    }

    @Override
    public void displayInfo(){
        System.out.println("Warrior increases the Attack Power by "+this.extraStrength+"\nDefending reduces damage by half or can even block the total attack damage");
    }

} // End of warrior class

class Mage extends Player{
    private Random random;

    public Mage(String name, int health, int attackPower){
        super.name = name;
        super.health = health;
        super.attackPower = attackPower;
    }

    @Override
    public void healing(){
        super.health = 100;
        super.attackPower = 15;
    }

    @Override
    public void defendAttack(int damage){
        this.random = new Random();
        int result = random.nextInt(3) + 1;
        if (result == 1) {
            this.health -= damage;
            super.attackPower += 3;
            System.out.println("Defending wasn't effective. Health: "+this.health);
        }
        else if(result == 2){
            this.health -= (damage/2);
            super.attackPower += 1;
            System.out.println("You received half damage. Health: "+this.health);
        }
        else{
            System.out.println("Attack fully stopped! Health: "+this.health);
        }
    }

    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("When receiving damage, if the total damage is received,\n"+//
        "the Attack Power will increase by 3. If only half the damage was received, \n"+//
        "the Attack Power will increase by 1. No damage received means no increase in the Attack Power.\n"+//
        "Healing will get the full health back, but will also restore the Power Attack");
    }

} // End of mage class


class Archer extends Player{
    private Random random;

    public Archer(String name, int health, int attackPower){
        super.name = name;
        super.health = health;
        super.attackPower = attackPower;
    }

    @Override
    public int getAttackPower(){
        random = new Random();
        int arrows = random.nextInt(3)+1;
        if(arrows == 3){
            return super.getAttackPower()*arrows;
        }
        else if(arrows == 2){
            return super.getAttackPower()*arrows;
        }
        else{
            return super.getAttackPower();
        }

    }

    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("When attacking, the Attack Power will increase by the times of arrows.\n"+//
        "E.g. Attack Power = 5, game would randomly choose a number between 1 and 3, \n"+//
        "if 3 was selected, then Attack Power = 5*3 = 15");

    }


}


class Enemy{
    private String type;
    private int health;
    private int attackPower;
    private int x;
    private int y;

    public int getX(){ 
        return x; 
    }

    public int getY(){ 
        return y; 
    }

    public void setX(int x){ 
        this.x = x; 
    }

    public void setY(int y){ 
        this.y = y; 
    }

    public Enemy(String type, int health, int attackPower){
        this.type = type;
        this.health = health;
        this.attackPower = attackPower;
    }

    public void takeDamage(int damage){
        this.health -= damage;
    }

    public String getType(){
        return this.type;
    }

    public int getAttackPower(){
        return this.attackPower;
    }

    public int getHealth(){
        return this.health;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public void enemyHealing(){
        this.health +=5;
    }

}


class CombatSystem{
    private Player player;
    private Enemy enemy;
    private Scanner scanner;
    private Random random;

    public CombatSystem(Player player, Enemy enemy){
        this.player = player;
        this.enemy = enemy;
        this.scanner = new Scanner(System.in);
    }

    public void startCombat(){
        System.out.println("STARTING COMBAT");
        System.out.println(player.getName() + " vs " +enemy.getType());

        while (player.isAlive() && enemy.isAlive()) {
            playerTurn();
            if(!enemy.isAlive()) break;
            enemyTurn();
            if(!player.isAlive()) break;
        }

        if(player.isAlive()){
            System.out.println(player.getName() + " defeated the " + enemy.getType() + "!");
        } else {
            System.out.println(player.getName() + " was defeated... Game Over!");
            System.exit(0);
        }

    }

    private void playerTurn(){
        System.out.println("Player turn: ");
        System.out.println("1: Attack \n2: Defend \n3: Heal");
        System.out.println("Choose action");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                int damage = player.getAttackPower();
                enemy.takeDamage(damage);
                System.out.println("Hit the "+enemy.getType() + " for "+damage+" damage");
                break;
            case 2:
                player.activateDefense();
                System.out.println("Preparing for attack...");
                break;
            case 3:
                player.healing();
                System.out.println("Current health: "+player.getHealth());
                break;
            default:
                System.out.println("Invalid choice, hesitation is defeat...");
        }
        System.out.println(enemy.getType() + " health: "+enemy.getHealth());
    }

    private void enemyTurn(){
        System.out.println("\n"+enemy.getType() + "'s turn");

        // Goblin's decision
        random = new Random();
        int decision = random.nextInt(3) + 1;
        System.out.println(decision);
        switch(decision){
            case 1:
                System.out.println("Goblin has decided to attack! ");
                int damage = enemy.getAttackPower();
                if(player.isDefenseActive()){
                    player.defendAttack(damage);
                    player.deactivateDefense();
                }
                else{
                    player.takeDamage(damage);
                }
                System.out.println(enemy.getType() + " hits you for " + damage + " damage!");
                System.out.println("Your health: " + player.getHealth());
                break;
            case 2:
                System.out.println("Goblin has decided to heal...");
                enemy.enemyHealing();
                System.out.println("Goblin's health: "+enemy.getHealth());
                break;
            case 3:
                System.out.println("Goblin does nothing... ._.");
                break;
            default:
                break;
        }
    }



} // End of Enemy's class

class InvalidActionException extends Exception{
    public InvalidActionException(String message){
        super(message);
    }
}


public class game2{

    private int gridSize;
    private int playerX;
    private int playerY;
    private char[][] grid;
    private int enemyX;
    private int enemyY;
    private Random random;
    private Player player;
    private Enemy enemy;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    // Constructors
    public game2(int size){
        this.gridSize = size;
        this.grid = new char[size][size];
        this.random = new Random();
        // Initializing the player at the center of the grid
        this.playerX = size/2;
        this.playerY = size/2;
        // this.player = new Player("Human", 100, 15);
        this.player = new Warrior("Warrior", 100, 10);
        this.enemy = new Enemy("Goblin", 50, 5);
        do {
            this.enemyX = random.nextInt(size);
            this.enemyY = random.nextInt(size);
        } while (enemyX == playerX && enemyY == playerY);
        initializeGrid();
    }

    public game2(int size, Player player) {
        this.gridSize = size;
        this.grid = new char[size][size];
        this.random = new Random();
        this.playerX = size / 2;
        this.playerY = size / 2;
        this.player = player;
        initializeEnemies();
    
        for (Enemy enemy : enemies) {
            int enemyX, enemyY;
            do {
                enemyX = random.nextInt(size);
                enemyY = random.nextInt(size);
            } while (enemyX == playerX && enemyY == playerY);
            
            enemy.setX(enemyX);
            enemy.setY(enemyY);
        }
        initializeGrid();
    }

    public void initializeEnemies(){
        random = new Random();
        int totalEnemies = random.nextInt(5)+1;
        for (int i = 0; i < totalEnemies; i++) {
            Enemy E = new Enemy("Goblin", 50, 5);
            this.enemies.add(E);
        }
    }


    public void initializeGrid(){
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = '.';
            }
        }
        grid[playerY][playerX] = 'H';
        
        // Place ALL enemies
        for (Enemy enemy : enemies) {
            grid[enemy.getX()][enemy.getY()] = 'E';
        }
    }

    public void printGrid(){
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(grid[i][j]+" ");
            }
            System.out.println();
        }
    }

    public boolean movePlayer(String direction){
        // Clear current position
        grid[playerY][playerX] = '.';
        
        // Calculate new position
        int newX = playerX;
        int newY = playerY;
        
        direction = direction.toLowerCase();
        switch (direction) {
            case "w": // W for north
            case "north":
                newY--;
                break;
            case "s": // S for south
            case "south":
                newY++;
                break;
            case "d": // D for east or right
            case "east":
                newX++;
                break;
            case "a": // A for west or left
            case "west":
                newX--;
                break;
            default:
                System.out.println("Wrong Input! Use W/A/S/D or North/South/East/West");
                grid[playerY][playerX] = 'H'; // Restore player position
                return false;
        }
        
        // Check boundaries
        if (newX < 0 || newX >= gridSize || newY < 0 || newY >= gridSize) {
            System.out.println("Can't move there - out of bounds!");
            grid[playerY][playerX] = 'H'; 
            return false;
        }

        if (newX == enemyX && newY == enemyY) {
            // Combat logic
            CombatSystem combat = new CombatSystem(player, enemy);
            combat.startCombat();

            if (enemy.isAlive()) {
                // Enemy still alive, can't move there
                grid[playerY][playerX] = 'H'; // Restore player position
                return false;
            } else {
                // Defeated the enemy, restoring player's position
                grid[enemyY][enemyX] = '.';
                playerX = newX;
                playerY = newY;
                grid[playerY][playerX] = 'H';
                return true;
            }
        }
        
        // Update position
        playerX = newX;
        playerY = newY;
        grid[playerY][playerX] = 'H';
        return true;
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    public static int enterInteger(Scanner sc) throws InvalidActionException {
        try {
            int option = sc.nextInt();
            if (option < 1 || option > 3) {
                throw new InvalidActionException("Option must be between 1 and 3.");
            }
            return option;
        } catch (InputMismatchException e) {
            sc.nextLine(); 
            throw new InvalidActionException("Invalid format. Enter a number.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = 10;
        int aux = 0;
        int decision = 0;
        Player p1 = new Warrior("Warrior",100,10);
        Player p2 = new Mage("Mage", 100, 10);
        Player p3 = new Archer("Archer", 100, 100);

        // Creating an array for storing the available classes
        // this.player = new Player("Human", 100, 15);
        ArrayList<Player> playerClasses = new ArrayList<>();
        playerClasses.add(p1);
        playerClasses.add(p2);
        playerClasses.add(p3);
            
        
        System.out.println("Welcome to the game: Fantasy Battle Simulator \n");
        System.out.println("Available classes: \n");
        for (int index = 0; index < playerClasses.size(); index++) {
            aux += 1;
            System.out.println(aux+". "+playerClasses.get(index).getName());
        }

        System.out.println("\nSelect a class to start (1-3): ");
        try {
            decision = enterInteger(scanner);
        } catch (Exception e) {
            scanner.nextLine();
        }

        decision -= 1;
        playerClasses.get(decision).displayInfo();

        game2 game = new game2(size,playerClasses.get(decision));

        while (true) {
            System.out.println("\nCurrent grid:");
            game.printGrid();
            
            System.out.print("\nEnter direction (W/S/D/A) or Q to quit: ");
            String input = scanner.next();
            
            if (input.equalsIgnoreCase("q")) {
                System.out.println("Game over!");
                break;
            }
            clearScreen();
            game.movePlayer(input);
        }
        
        scanner.close();
    }
}