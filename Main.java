import java.util.Scanner;
import java.util.Random;

public class Main {
  private static int GRID_SIZE = 4;
  private static char EMPTY_CELL = '-';
  public static char PLAYER_SYMBOL = 'P';
  private static char MONSTER_SYMBOL = 'M';

  public String LINE_2 = "----------------------------------------------------------------------------------";

  private char[][] grid;
  private Character player;
  private Monster monster;
  private Scanner scanner;
  public String gameOver = "                               ____                 \n" +
      "                              / __ \\                \n" +
      "  __ _  __ _ _ __ ___   ___  | |  | |_   _____ _ __ \n" +
      " / _` |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__|\n" +
      "| (_| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |   \n" +
      " \\__, |\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|  \n" +
      "  __/ |                                             \n" +
      " |___/                                              \n";

  public Main() {
    grid = new char[GRID_SIZE][GRID_SIZE];
    scanner = new Scanner(System.in);
  }

  // method to get input from User, set attributes for Character/player
  private void initializeGame() {
    String attributes = "";
    String className = "";
    int choice;
    
    // 1.a Get User Name
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter character's name: ");
    String name = scanner.nextLine();

    // Call the chooseClass method from the Character class
    player = new Character(0, 0, 0, 0, 0, 0, 0, 0);
    player.chooseClass(scanner);
    Armory armory = new Armory(1000); // Initial gold in the armory

    // 1.D - Logic to character buying weapon, head, body, feet gear with 1000 gold
    System.out
        .println("\nFor this epic battle, let's get you some gear to fight the Monster. You have " + armory.getGold()
            + " gold.");
    System.out.println(
        "Let's put it to good use and buy a weapon, head gear, body gear, and feet gear that will increase your bonus attributes.");
    System.out
        .println("\nBased on the character you picked choose wisely so you can maximize the bonus attributes.\n\n");

    // Pick Weapons gear
    System.out.println("\n******* Available Gold: " + armory.getGold()
        + ". You can pick one of the following Weapons: *******");
    System.out.println(LINE_2);
    System.out
        .println("1. Great Item - Elvish sword.\t\t\t\t\t\t\tCost: 600 Gold");
    System.out.println(
        "2. Good Item  - Gimli's Axe.\t\t\t\t\t\t\tCost: 300 Gold");
    System.out.println(
        "3. Okay Item  - Legolas's Bow.\t\t\t\t\t\t\tCost: 200 Gold");
    System.out.println(LINE_2);
    System.out.print("Choose a weapon (1-3): ");
    choice = scanner.nextInt();

    armory.selectWeapon(player, choice);

    // Pick Head gear
    System.out.println(
        "\n******* Available Gold: " + armory.getGold() + ". You can pick one of the following Head Gear: *******");
    System.out.println(LINE_2);
    System.out.println("1. Great Item - Helm of Dragonlord.\t\t\t\t\t\tCost: 200 Gold");
    System.out.println("2. Good Item  - Wizard's Hat of Mastery.\t\t\t\tCost: 100 Gold");
    System.out.println("3. Okay Item  - Bandit's Mask of Stealth.\t\t\t\tCost:  50 Gold");
    System.out.println(LINE_2);
    System.out.print("Choose head gear (1-3): ");
    choice = scanner.nextInt();

    armory.selectHeadGear(player, choice); // Select the second headgear option

    // Pick Body gear
    System.out.println(
        "\n******* Available Gold: " + armory.getGold() + ". You can pick one of the following Body Gear: *******");
    System.out.println(LINE_2);
    System.out.println("1. Great Item - Plate Armor of the Titan.\t\t\t\tCost: 250 Gold");
    System.out.println("2. Good Item  - Robe of the Archmage.\t\t\t\t\tCost: 100 Gold");
    System.out.println("3. Okay Item  - Leather Vest of the Agile Hunter.\t\tCost:  50 Gold");
    System.out.println(LINE_2);
    System.out.print("Choose body gear (1-3): ");
    choice = scanner.nextInt();

    armory.selectBodyGear(player, choice); // Select the third body gear option

    // Pick Feet gear
    System.out.println(
        "\n******* Available Gold: " + armory.getGold() + ". You can pick one of the following Feet Gear: *******");
    System.out.println(LINE_2);
    System.out.println("1. Great Item - Boots of the Swift Wind.\t\t\t\tCost: 200 Gold");
    System.out.println("2. Good Item  - Greaves of the Guardian.\t\t\t\tCost: 100 Gold");
    System.out.println("3. Okay Item  - Traveler's Sandals.\t\t\t\t\t\tCost:  50 Gold");
    System.out.println(LINE_2);
    System.out.print("Choose feet gear (1-3): ");
    choice = scanner.nextInt();

    armory.selectFeetGear(player, choice); // Select the first feet gear option
    
    // Display remaining gold
    System.out.println("Remaining Gold: " + armory.getGold());

    if (armory.getGold() >= 0) {
      System.out.println("\nCongratulations! You have purchased the items and received bonus attributes:\n");
      
      System.out.println("New Character Attributes after your purchase:");
      System.out.println("Strength:\t\t" + player.getStrength());
      System.out.println("Agility:\t\t" + player.getAgility());
      System.out.println("Stamina:\t\t" + player.getStamina());
      System.out.println("Intelligence:\t" + player.getIntelligence());
      System.out.println("Spirit:\t\t\t" + player.getSpirit());

      System.out.println("\nRemaining Gold: " + armory.getGold());
    } else {
      System.out.println("Insufficient gold to purchase the items!");
    }

    // create an instance of the Character class, initialize the player object
    player = new Character(0, 0, player.getHealth(), player.getStamina(), player.getStrength(), player.getAgility(),
        player.getIntelligence(), player.getSpirit());
  }

  public void start() {
    initializeGame();
    initializeGrid();
    placeMonster(); // place monster and assign monster health and damage

    while (true) {
      printGrid();
      System.out.println("Player Health: " + player.getHealth() + ", Stamina: " + player.getStamina() + ", Strength: "
          + player.getStrength());
      System.out.println("Monster Health: " + monster.getHealth() + ", Damage: " + monster.getStrength());

      System.out.println("Choose an action:");
      System.out.println("1. Attack");
      System.out.println("2. Dodge");
      System.out.println("3. Cast Spell");
      System.out.println("4. Run");
      System.out.println("5. Move");
      System.out.println("6. Exit Game");

      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          attack();
          break;
        case 2:
          dodge();
          break;
        case 3:
          castSpell();
          break;
        case 4:
          run();
          break;
        case 5:
          movePlayer();
          if (!monster.isDead()) {
            monsterMove();
          }
          break;
        case 6:
          System.exit(0);
        default:
          System.out.println("Invalid choice. Please try again.");
      }

      if (player.isDead()) {
        System.out.println("Player is defeated. Game over!");
        System.out.println(gameOver);
        break;
      }

      // Logic to check if player made it to pos[4,4] && monster(isdead)
      if (monster.isDead() && (grid[GRID_SIZE - 1][GRID_SIZE - 1] == PLAYER_SYMBOL)) {
        System.out.println("Player walked out. Game over!");
        System.out.println(gameOver);
        break;
      }

      if (monster.isDead() && MONSTER_SYMBOL != 'X') {
        System.out.println("Monster is defeated. Player can walk out of the castle.");
        MONSTER_SYMBOL = 'X';
      }
    }
  }

  private void initializeGrid() {
    // Set the 4x4 grid and fill it with "-"
    for (int i = 0; i < GRID_SIZE; i++) {
      for (int j = 0; j < GRID_SIZE; j++) {
        grid[i][j] = EMPTY_CELL;
      }
    }
  }

  private void printGrid() {
    // print the castle grid and show character and monster
    grid[player.getRow()][player.getCol()] = player.getSymbol();

    if (!monster.isDead()) {
      grid[monster.getRow()][monster.getCol()] = MONSTER_SYMBOL;
    }

    String castleHeads = "\n\n    /\\                 /\\ \n" +
        "   /  \\               /  \\ \n" +
        "  /    \\   CASTLE    /    \\\n" +
        " /______\\___________/______\\";
    System.out.println(castleHeads);
    System.out.println("-----------------------------");

    for (int row = 0; row < GRID_SIZE; row++) {
      for (int col = 0; col < GRID_SIZE; col++) {
        if (grid[row][col] == 0) {
          System.out.print(" |     ");
        } else {
          System.out.print(" |  " + grid[row][col] + "  ");
        }
      }
      System.out.println("|");
      System.out.println("-----------------------------");
    }
    System.out.println("\n\n");
  }

  private void placeMonster() {
    int row = GRID_SIZE - 1;
    int col = GRID_SIZE - 1;
    String selectedMonster;
    int MonsterHealth;
    int MonsterDamage;

    Random random = new Random();
    int randomNumber = random.nextInt(5) + 1;

    switch (randomNumber) {
      case 1:
        // Weak Monster
        selectedMonster = "Weak Monster";
        MonsterHealth = 50 + new Random().nextInt(20);
        MonsterDamage = 5 + new Random().nextInt(5);
        break;
      case 2:
        // Common Monster
        selectedMonster = "Common Monster";
        MonsterHealth = 70 + new Random().nextInt(30);
        MonsterDamage = 5 + new Random().nextInt(10);
        break;
      case 3:
        // Strong Monster
        selectedMonster = "Strong Monster";
        MonsterHealth = 90 + new Random().nextInt(40);
        MonsterDamage = 10 + new Random().nextInt(15);
        break;
      case 4:
        // Rare Monster
        selectedMonster = "Rare Monster";
        MonsterHealth = 110 + new Random().nextInt(50);
        MonsterDamage = 15 + new Random().nextInt(20);
        break;
      case 5:
        // Boss Monster
        selectedMonster = "Boss Monster";
        MonsterHealth = 150 + new Random().nextInt(100);
        MonsterDamage = 20 + new Random().nextInt(30);
        break;
      default:
        // Boss Monster
        selectedMonster = "Boss Monster";
        MonsterHealth = 150 + new Random().nextInt(100);
        MonsterDamage = 20 + new Random().nextInt(30);
        break;
    }

    // assign Monster's strength and damage based on its Type.
    // create instance of the monster class, initialize the monster object.
    monster = new Monster(row, col, selectedMonster, MonsterHealth, MonsterDamage);

    System.out.println("\nGenerating Monster (Weak, Common, Strong, Rare, Boss Monster)...");

    // create a few seconds delay to simulate monster generation
    for (int i = 0; i < 5; i++) {
      try {
        Thread.sleep(1000); // 1 second delay
        System.out.print(".");
      } catch (InterruptedException e) {
      }
    }

    System.out.println("Monster generated successfully!");
    System.out.println("Selected Monster: " + selectedMonster);
    System.out.println("Monster's Health: " + monster.getHealth());
    System.out.println("Monster's Damage: " + monster.getStrength());

    // Place Monster at the last row/col
    grid[monster.getRow()][monster.getCol()] = MONSTER_SYMBOL;
  }

  private void attack() {
    int playerDamage = player.getStrength();
    // 2a. Monster attacks should not be the same damage for every attack, but be
    // random within a certain range
    int monsterDamage = monster.getStrength() + new Random().nextInt(10);

    System.out.println(
        "Player inflicts " + playerDamage + " damage. Monster remaining health: " + monster.takeDamage(playerDamage));
    System.out.println(
        "Monster inflicts " + monsterDamage + " damage. Player remaining health: " + player.takeDamage(monsterDamage));

    // Reduce player's attributes
    player.reduceAttributes(monsterDamage);
  }

  private void dodge() {
    int dodgeChance = player.getAgility() * 30;
    int random = (int) (Math.random() * 100);
    // 2a. Monster attacks should not be the same damage for every attack, but be
    // random within a certain range
    int monsterDamage = monster.getStrength() + new Random().nextInt(10);

    // make the dodging based on player's agility
    if (random < dodgeChance) {
      int playerDamage = player.getAgility();
      System.out.println("Player dodges the monster's attack!");

      System.out.println(
          "Player inflicts " + playerDamage + " damage. Monster remaining health: " + monster.takeDamage(playerDamage));
      // Reduce monster's health
      monster.reduceHealth(monsterDamage);
    } else {
      System.out.println("Monster inflicts " + monsterDamage + " damage. Player remaining health: "
          + player.takeDamage(monsterDamage));
      // Reduce player's attributes
      player.reduceAttributes(monsterDamage);
    }
  }

  private void castSpell() {
    int spellChance = player.getIntelligence() * 30;
    int random = (int) (Math.random() * 100);
    // 2a. Monster attacks should not be the same damage for every attack, but be
    // random within a certain range
    int monsterDamage = monster.getStrength() + new Random().nextInt(10);

    // make the casting of spell based on player's intelligence/mana
    if (random < spellChance) {
      int playerDamage = player.getIntelligence();
      System.out.println("Player casts spell on the monster!");
      System.out.println(
          "Player inflicts " + playerDamage + " damage. Monster remaining health: " + monster.takeDamage(playerDamage));
      // Reduce player's attributes
      player.reduceAttributes(monsterDamage);
    } else {
      System.out.println("Player's spell failed!");
    }
  }

  private void run() {
    int runChance = player.getAgility() * 30;
    int random = (int) (Math.random() * 100);
    // 2a. Monster attacks should not be the same damage for every attack, but be
    // random within a certain range
    int monsterDamage = monster.getStrength() + new Random().nextInt(10);
    // make the running spell based on player's agility
    if (random < runChance) {
      int playerDamage = player.getAgility();
      System.out.println("Player successfully runs away from Monster attack!");

      System.out.println(
          "Player inflicts " + playerDamage + " damage. Monster remaining health: " + monster.takeDamage(playerDamage));

      int randomRowDirection = (int) (Math.random() * 4);
      int randomColDirection = (int) (Math.random() * 4);

      grid[player.getRow()][player.getCol()] = 0;
      player.setRow(randomRowDirection);
      player.setCol(randomColDirection);

      grid[randomRowDirection][randomColDirection] = player.getSymbol();
      printGrid();

      // Check for player and monster in the same cell. If so, you are dead!
      if ((player.getRow() == monster.getRow() && player.getCol() == monster.getCol()) && !monster.isDead()) {
        System.out.println("You encountered a monster! Game over.");
        System.out.println(gameOver);
        System.exit(0);
      }

    } else {
      System.out.println("Monster inflicts " + monsterDamage + " damage. Player remaining health: "
          + player.takeDamage(monsterDamage));

      // Reduce player's attributes
      player.reduceAttributes(monsterDamage);

      // Reduce monster's health
      monster.reduceHealth(monsterDamage);
    }
  }

  private void movePlayer() {
    Scanner scanner = new Scanner(System.in);
    System.out.print(
        "\nChoose direction to move (N:North / U: Up, S: South / D: Down, W: West / L: Left, E: East / R: Right):");
    String direction = scanner.nextLine().toUpperCase();

    if (!(direction.equals("U") || direction.equals("N") || direction.equals("D") || direction.equals("S")
        || direction.equals("L") || direction.equals("E") || direction.equals("R") || direction.equals("W"))) {
      System.out.println("Invalid direction! Please choose again.");
    }

    int playerRow = player.getRow();
    int playerCol = player.getCol();

    switch (direction) {
      case "U", "N": // up, north
        playerRow--;
        break;
      case "D", "S": // down, south
        playerRow++;
        break;
      case "L", "W": // left, west
        playerCol--;
        break;
      case "R", "E": // right, east
        playerCol++;
        break;
    }

    if (playerRow < 0 || playerRow >= GRID_SIZE || playerCol < 0 || playerCol >= GRID_SIZE) {
      System.out.println("Cannot move in that direction! Please choose again.");
    } else {
      // Update grid with new player position
      grid[player.getRow()][player.getCol()] = 0;
      player.move(direction);

      // check if player makes it to the gate. game over.
      if (monster.isDead() && (grid[player.getRow()][player.getCol()] == grid[GRID_SIZE - 1][GRID_SIZE - 1])) {
        grid[GRID_SIZE - 1][GRID_SIZE - 1] = PLAYER_SYMBOL;
        System.out.println("Player walked out. Game over!");
        System.out.println(gameOver);
        printGrid();
        System.exit(0);
      }
      grid[player.getRow()][player.getCol()] = player.getSymbol();

      // Check if player encounters the monster
      if ((player.getRow() == monster.getRow() && player.getCol() == monster.getCol()) && !monster.isDead()) {
        System.out.println("You encountered a monster! Game over.");
        System.out.println(gameOver);
        System.exit(0);
      } else {
        System.out.println("Player Moves:");
        printGrid();
      }
    }
  }

  private boolean isValidMove(int row, int col) {
    return row >= 0 && row < GRID_SIZE && col >= 0 && col < GRID_SIZE;
  }

  private void monsterMove() {
    System.out.println("Monster Moves:");
    int newRow = monster.getRow();
    int newCol = monster.getCol();

    Random random;
    random = new Random();

    // Generate a random number between 0 and 3 to randomize monster move
    int direction = random.nextInt(4);

    switch (direction) {
      case 0: // Move up
        newRow--;
        break;
      case 1: // Move down
        newRow++;
        break;
      case 2: // Move left
        newCol--;
        break;
      case 3: // Move right
        newCol++;
        break;
    }

    // Check if the new position is valid
    if (isValidMove(newRow, newCol)) {
      grid[monster.getRow()][monster.getCol()] = '-';
      monster.setRow(newRow);
      monster.setCol(newCol);
      grid[newRow][newCol] = 'M';
    }
  }

  public static void main(String[] args) {
    Main game = new Main();
    game.start();
  }
}

// Character Class
class Character {
  private int row;
  private int col;
  private int health;
  private int stamina;
  private int strength;
  private int agility;
  private int intelligence;
  private int spirit;
  private char symbol;
  private String className;
  private String attributes; // Add the attributes variable

  private String LINE_2 = "----------------------------------------------------------------------------------";

  private int bonusStrength = 0;
  private int bonusAgility = 0;
  private int bonusStamina = 0;
  private int bonusIntelligence = 0;
  private int bonusSpirit = 0;

  // Constructor to assign the character the initial position in the grid and all
  // the corresponding attributes (health, stamina, .... etc.)
  public Character(int row, int col, int health, int stamina, int strength, int agility, int intelligence, int spirit) {
    this.row = row;
    this.col = col;
    this.health = health;
    this.stamina = stamina;
    this.strength = strength;
    this.agility = agility;
    this.intelligence = intelligence;
    this.spirit = spirit;
    this.symbol = 'P';
    this.className = "";
  }

  // Getter methods for the Character
  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public int getHealth() {
    return health;
  }

  public int getStamina() {
    return stamina;
  }

  public int getStrength() {
    return strength;
  }

  public int getAgility() {
    return agility;
  }

  public int getIntelligence() {
    return intelligence;
  }

  public int getSpirit() {
    return spirit;
  }

  public char getSymbol() {
    return symbol;
  }

  public String getClassName() {
    return className;
  }

  // Setter methods for Character
  public void setRow(int row) {
    this.row = row;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void setStamina(int stamina) {
    this.stamina = stamina;
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

  public void setAgility(int agility) {
    this.agility = agility;
  }

  public void setIntelligence(int intelligence) {
    this.intelligence = intelligence;
  }

  public void setSpirit(int spirit) {
    this.spirit = spirit;
  }

  // function for player to move and get the row/col position
  public void move(String direction) {
    switch (direction) {
      case "U", "N": // up, north
        row--;
        break;
      case "D", "S": // down, south
        row++;
        break;
      case "L", "W": // left, west
        col--;
        break;
      case "R", "E": // right, east
        col++;
        break;
    }
  }

  // method for player to take damage and subtract it from health
  public int takeDamage(int damage) {
    health -= damage;
    if (health < 0) {
      health = 0;
    }
    return health;
  }

  // Reduce Players attributes like stamina, strength, agility, intelligence, and
  // spirit
  public void reduceAttributes(int monsterDamage) {
    stamina -= Math.round(monsterDamage / 6);
    strength -= Math.round(monsterDamage / 6);
    agility -= Math.round(monsterDamage / 4);
    intelligence -= Math.round(monsterDamage / 4);
    spirit -= Math.round(monsterDamage / 4);

    // make sure attribute values are not negative
    if (stamina < 0) {
      stamina = 0;
    }

    if (strength < 0) {
      strength = 0;
    }

    if (agility < 0) {
      agility = 0;
    }

    if (intelligence < 0) {
      intelligence = 0;
    }

    if (spirit < 0) {
      spirit = 0;
    }
  }

  // check if player is dead
  public boolean isDead() {
    return health <= 0;
  }

  public void chooseClass(Scanner scanner) {
    System.out.println("Class\t\tBonus Attributes");
    System.out.println(LINE_2);
    System.out.println("Warrior\t\tStrength: 3X, Agility: 2X, Stamina: 3X, Intelligence: 1X, Spirit: 1X");
    System.out.println("Druid\t\tStrength: 1X, Agility: 2X, Stamina: 1X, Intelligence: 3X, Spirit: 3X");
    System.out.println("Rogue\t\tStrength: 2X, Agility: 3X, Stamina: 2X, Intelligence: 1X, Spirit: 2X");
    System.out.println(LINE_2);

    System.out.print("\nChoose a class (1. W-Warrior, 2. D-Druid, 3. R-Rogue): ");
    String classChoice = scanner.nextLine();

    switch (classChoice.toUpperCase()) {
      case "1", "W":
        className = "Warrior";
        attributes = "Strength: 3X, Agility: 2X, Stamina: 3X, Intelligence: 1X, Spirit: 1X";
        bonusStrength = 3;
        bonusAgility = 2;
        bonusStamina = 3;
        bonusIntelligence = 1;
        bonusSpirit = 1;
        break;
      case "2", "D":
        className = "Druid";
        attributes = "Strength: 1X, Agility: 2X, Stamina: 1X, Intelligence: 3X, Spirit: 3X";
        bonusStrength = 1;
        bonusAgility = 2;
        bonusStamina = 1;
        bonusIntelligence = 3;
        bonusSpirit = 3;
        break;
      case "3", "R":
        className = "Rogue";
        attributes = "Strength: 2X, Agility: 3X, Stamina: 2X, Intelligence: 1X, Spirit: 2X";
        bonusStrength = 2;
        bonusAgility = 3;
        bonusStamina = 2;
        bonusIntelligence = 1;
        bonusSpirit = 2;
        break;
      default:
        System.out.println("Invalid choice. Starting as a Warrior by default.");
        className = "Warrior";
        attributes = "Strength: 3X, Agility: 2X, Stamina: 3X, Intelligence: 1X, Spirit: 1X";
        bonusStrength = 3;
        bonusAgility = 2;
        bonusStamina = 3;
        bonusIntelligence = 1;
        bonusSpirit = 1;
        break;
    }

    System.out.println("\n\nYou have chosen " + className + ".");
    System.out.println(className + "s'Attributes: " + attributes);
    System.out.println("Your choice is wise. Prepare to face the formidable monster!\n\n");

    int talentPoints = 25;
    System.out.println("Assign talent points to your " + className + "'s " + "attributes. Can't exceed 25 points.");

    System.out.print("Available Talent Points: " + talentPoints + ". Enter Strength (0-10):\t\t");
    int strengthPoints = scanner.nextInt();
    talentPoints -= strengthPoints;

    System.out.print("Available Talent Points: " + talentPoints + ". Enter Agility (0-10):\t\t");
    int agilityPoints = scanner.nextInt();
    talentPoints -= agilityPoints;

    System.out.print("Available Talent Points: " + talentPoints + ". Enter Stamina (0-10):\t\t");
    int staminaPoints = scanner.nextInt();
    talentPoints -= staminaPoints;

    System.out.print("Available Talent Points: " + talentPoints + ". Enter Intelligence (0-10):\t");
    int intelligencePoints = scanner.nextInt();
    talentPoints -= intelligencePoints;

    System.out.print("Available Talent Points: " + talentPoints + ". Enter Spirit (0-10):\t\t");
    int spiritPoints = scanner.nextInt();
    talentPoints -= spiritPoints;

    if (talentPoints < 0) {
      System.out.println("Total talent points can't exceed 25. Please start over");
      System.exit(0);
    }

    // Set the calculated attributes using the setter methods
    setStrength(strengthPoints * bonusStrength);
    setAgility(agilityPoints * bonusAgility);
    setStamina(staminaPoints * bonusStamina);
    setIntelligence(intelligencePoints * bonusIntelligence);
    setSpirit(spiritPoints * bonusSpirit);

    setHealth(getStrength() + getStamina());

    System.out.println("\nCharacter Attributes:");
    System.out.println("Strength:\t\t" + getStrength());
    System.out.println("Agility:\t\t" + getAgility());
    System.out.println("Stamina:\t\t" + getStamina());
    System.out.println("Intelligence:\t" + getIntelligence());
    System.out.println("Spirit:\t\t\t" + getSpirit());

    System.out
        .println("\nYour choice is a wise one against the formidable monster! Good Luck! We are rooting for you.\n");

  }

}

// Monster Class
class Monster {
  private int row;
  private int col;
  private String selectedMonster;
  private int health;
  private int strength;

  // constructor to assign monsters initial position and its health and damage
  public Monster(int row, int col, String selectedMonster, int health, int strength) {
    this.row = row;
    this.col = col;
    this.selectedMonster = selectedMonster;
    this.health = health;
    this.strength = strength;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public String selectedMonster() {
    return selectedMonster;
  }

  public int getHealth() {
    return health;
  }

  public int getStrength() {
    return strength;
  }

  // function to take Monster's health from the damage it takes
  public int takeDamage(int damage) {
    health -= damage;
    if (health < 0) {
      health = 0;
    }
    return health;
  }

  // function to reduce Monster's health by 10
  public void reduceHealth(int damage) {
    health -= damage;
    if (health < 0) {
      health = 0;
    }
  }

  public void setRow(int row) {
    this.row = row;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public void setSelectedMonster(String selectedMonster) {
    this.selectedMonster = selectedMonster;
  }

  // check to see if monster is dead
  public boolean isDead() {
    return health <= 0;
  }
}

// Armory Class to select gear and check Gold
class Armory {
  private int gold;

  public Armory(int initialGold) {
    this.gold = initialGold;
  }

  public int getGold() {
    return gold;
  }

  public void setGold(int gold) {
    this.gold = gold;
  }

  public void selectWeapon(Character character, int choice) {
    int cost;
    String gearName;

    switch (choice) {
      case 1:
        cost = 600;
        gearName = "Great Item - Elvish sword";
        switch (character.getClassName()) {
          case "Warrior":
            character.setStrength(character.getStrength() + 10);
            break;
          case "Druid":
            character.setIntelligence(character.getIntelligence() + 5);
            character.setSpirit(character.getSpirit() + 5);
            break;
          case "Rogue":
            character.setAgility(character.getAgility() + 10);
            break;
        }
        break;
      case 2:
        cost = 300;
        gearName = "Good Item - Gimli's Axe";
        switch (character.getClassName()) {
          case "Warrior":
            character.setStrength(character.getStrength() + 5);
            break;
          case "Druid":
            character.setIntelligence(character.getIntelligence() + 3);
            character.setSpirit(character.getSpirit() + 2);
            break;
          case "Rogue":
            character.setAgility(character.getAgility() + 5);
            break;
        }
        break;
      case 3:
      default:
        cost = 200;
        gearName = "Okay Item - Legolas's Bow";
        switch (character.getClassName()) {
          case "Warrior":
            character.setStrength(character.getStrength() + 2);
            break;
          case "Druid":
            character.setIntelligence(character.getIntelligence() + 1);
            character.setSpirit(character.getSpirit() + 1);
            break;
          case "Rogue":
            character.setAgility(character.getAgility() + 2);
            break;
        }
        break;
    }

    System.out.println("You have chosen the " + gearName + ". Cost: " + cost + " Gold");
    gold -= cost;
  }

  public void selectHeadGear(Character character, int choice) {
    int cost;
    String gearName;

    switch (choice) {
      case 1:
        cost = 200;
        gearName = "Great Item - Helm of Dragonlord";
        switch (character.getClassName()) {
          case "Warrior":
            character.setStrength(character.getStrength() + 10);
            break;
          case "Druid":
            character.setIntelligence(character.getIntelligence() + 5);
            character.setSpirit(character.getSpirit() + 5);
            break;
          case "Rogue":
            character.setAgility(character.getAgility() + 10);
            break;
        }
        break;
      case 2:
        cost = 100;
        gearName = "Good Item - Wizard's Hat of Mastery";
        switch (character.getClassName()) {
          case "Warrior":
            character.setStrength(character.getStrength() + 5);
            break;
          case "Druid":
            character.setIntelligence(character.getIntelligence() + 3);
            character.setSpirit(character.getSpirit() + 2);
            break;
          case "Rogue":
            character.setAgility(character.getAgility() + 5);
            break;
        }
        break;
      case 3:
      default:
        cost = 50;
        gearName = "Okay Item - Bandit's Mask of Stealth";
        switch (character.getClassName()) {
          case "Warrior":
            character.setStrength(character.getStrength() + 2);
            break;
          case "Druid":
            character.setIntelligence(character.getIntelligence() + 1);
            character.setSpirit(character.getSpirit() + 1);
            break;
          case "Rogue":
            character.setAgility(character.getAgility() + 2);
            break;
        }
        break;
    }

    System.out.println("You have chosen the " + gearName + ". Cost: " + cost + " Gold");
    gold -= cost;
  }

  public void selectBodyGear(Character character, int choice) {
    int cost;
    String gearName;

    switch (choice) {
      case 1:
        cost = 250;
        gearName = "Great Item - Plate Armor of the Titan";
        switch (character.getClassName()) {
          case "Warrior":
            character.setStrength(character.getStrength() + 10);
            break;
          case "Druid":
            character.setIntelligence(character.getIntelligence() + 5);
            character.setSpirit(character.getSpirit() + 5);
            break;
          case "Rogue":
            character.setAgility(character.getAgility() + 10);
            break;
        }
        break;
      case 2:
        cost = 100;
        gearName = "Good Item - Robe of the Archmage";
        switch (character.getClassName()) {
          case "Warrior":
            character.setStrength(character.getStrength() + 5);
            break;
          case "Druid":
            character.setIntelligence(character.getIntelligence() + 3);
            character.setSpirit(character.getSpirit() + 2);
            break;
          case "Rogue":
            character.setAgility(character.getAgility() + 5);
            break;
        }
        break;
      case 3:
      default:
        cost = 50;
        gearName = "Okay Item - Leather Vest of the Agile Hunter";
        switch (character.getClassName()) {
          case "Warrior":
            character.setStrength(character.getStrength() + 2);
            break;
          case "Druid":
            character.setIntelligence(character.getIntelligence() + 1);
            character.setSpirit(character.getSpirit() + 1);
            break;
          case "Rogue":
            character.setAgility(character.getAgility() + 2);
            break;
        }
        break;
    }

    System.out.println("You have chosen the " + gearName + ". Cost: " + cost + " Gold");
    gold -= cost;
  }

  public void selectFeetGear(Character character, int choice) {
    int cost;
    String gearName;

    switch (choice) {
      case 1:
        cost = 200;
        gearName = "Great Item - Boots of the Swift Wind";
        switch (character.getClassName()) {
          case "Warrior":
            character.setStrength(character.getStrength() + 10);
            break;
          case "Druid":
            character.setIntelligence(character.getIntelligence() + 5);
            character.setSpirit(character.getSpirit() + 5);
            break;
          case "Rogue":
            character.setAgility(character.getAgility() + 10);
            break;
        }
        break;
      case 2:
        cost = 100;
        gearName = "Good Item - Greaves of the Guardian";
        switch (character.getClassName()) {
          case "Warrior":
            character.setStrength(character.getStrength() + 5);
            break;
          case "Druid":
            character.setIntelligence(character.getIntelligence() + 3);
            character.setSpirit(character.getSpirit() + 2);
            break;
          case "Rogue":
            character.setAgility(character.getAgility() + 5);
            break;
        }
        break;
      case 3:
      default:
        cost = 50;
        gearName = "Okay Item - Boots of the Silent Walker";
        switch (character.getClassName()) {
          case "Warrior":
            character.setStrength(character.getStrength() + 2);
            break;
          case "Druid":
            character.setIntelligence(character.getIntelligence() + 1);
            character.setSpirit(character.getSpirit() + 1);
            break;
          case "Rogue":
            character.setAgility(character.getAgility() + 2);
            break;
        }
        break;
    }

    System.out.println("You have chosen the " + gearName + ". Cost: " + cost + " Gold");
    gold -= cost;
  }
}
