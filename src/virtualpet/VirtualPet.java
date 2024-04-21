/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;

import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

/**
 * Program: Virtual Pet Simulator
 *
 * @author Sarah Cui Start date: 03/18/2024
 */
public class VirtualPet {

    //For game 2 
    final static String MATCHINGTILES = "ABCDE";
    final static int TILENUMBER = 10;
    final static int TRIESMAX = 10;
    final static int CORRECTWINS = 5;
    final static int LUMPSMAXTILES = 24;

    //For pet interaction 
    final static int TOYWORTH = 4;
    final static int FOODWORTH = 4;
    final static int GROOMWORTH = 4;

    static int lumps = 0;

    //METHODS//
    //Method to check if correct login 
    public static boolean correctLogin(String username, String password) {
        //Create variables 
        boolean loggedIn = false;
        File f = new File(username + ".txt");
        String realPassword = "";

        try {
            //if file exists, create scanner for file
            if (f.exists() == true) {
                Scanner s = new Scanner(f);

                //Find password in file, determine if password equal
                realPassword = s.nextLine();

                if (password.equals(realPassword)) {
                    loggedIn = true;
                    return loggedIn;
                }

            } else {//Create a new file for user
                String newPassword = "";
                Scanner pass = new Scanner(System.in);
                PrintWriter newUser = new PrintWriter(username + ".txt");;
                newUser.println(password);
                newUser.println(0);
                newUser.println(false);
                newUser.println("");
                for (int nums = 0; nums < 7; nums++) {
                    newUser.println(0);
                }
                newUser.close();
                loggedIn = true;
                return loggedIn;

            }
            return loggedIn;
        } catch (IOException e) {
            System.out.println("Something went wrong. EXITING...");
            System.exit(0);
        }
        return loggedIn;
    }

    //Method to generate a random pet name 
    public static String nameGenerator(int nameLength, int doubleVowelMaxLength, String consonants, String vowels) {
        Random rd = new Random();
        String petName = "";
        char petVowel = ' ';
        for (int nameIndex = 0; nameIndex < (nameLength); nameIndex++) {
            //If pet name hasn't reached full length
            if (petName.length() < nameLength) {
                //Every other letter is consonant 
                if (nameIndex % 2 == 0) {
                    petName = petName + consonants.charAt(rd.nextInt(21));

                    //If first letter in name 
                    if (nameIndex == 0) {
                        petName = petName.toUpperCase();
                    }
                } //Every other letter is vowel 
                else {
                    petVowel = vowels.charAt(rd.nextInt(5));

                    //1 in 5 chance of having double vowels if doesn't exceed name length
                    if (rd.nextInt(4) == 0 && petName.length() <= doubleVowelMaxLength) {
                        petName = petName + petVowel + petVowel;
                    } //Otherwise Single vowel
                    else {
                        petName = petName + petVowel;
                    }
                }
            }
        }
        return petName;
    }

    //To play game 2
    public static void matchingGame() {

        //Scanner and random 
        Scanner kb = new Scanner(System.in);
        Random rd = new Random();

        //Variables 
        String tiles = "";
        int tile = 0;
        String shownTiles = "";
        int indexMatch1 = 0;
        int indexMatch2 = 0;
        int randomTile = 0;
        char addedTile = ' ';
        int correctGuess = 0;
        boolean tilesWin = false;
        String matchedTiles = "";
        String matchedTilesCopy = "";
        for (int tileMatch = 0; tileMatch < TILENUMBER; tileMatch++) {
            matchedTiles += "F";
        }

        System.out.println("Welcome to the matching game! You will have 5 pairs, 10 cards to match. Enter two positions from 1-10 to select two pairs. You will have 10 total matches available to make. You will earn 24 lumps for a flawless game (5 rounds), and subtract 4 for each additional round it takes to play.");

        //Set up both displayed tiles and actual hidden tiles
        for (int tileX = 0; tileX < TILENUMBER; tileX++) {
            shownTiles += "X";
        }

        //Create the list of actual tiles needed to guess 
        while (tile < TILENUMBER) {
            //Generate numbers from 0-4, and then pick random vowel using number 
            randomTile = rd.nextInt(TILENUMBER / 2);
            addedTile = MATCHINGTILES.charAt(randomTile);

            //If first & last position of certain letter is the same (meaning either one or no occurences), add the letter
            if (tiles.indexOf(addedTile) == tiles.lastIndexOf(addedTile)) {
                tiles += addedTile;
                tile++;
            }
        }

        //Print out intial shown tiles 
        System.out.println(shownTiles);

        //Set up system for guessing 
        for (int tries = 0; tries < TRIESMAX; tries++) {
            System.out.println("Input your first and second position (starting from index 0), 1 on each line");
            indexMatch1 = kb.nextInt();
            indexMatch2 = kb.nextInt();

            //Remake shown tiles, with both the inputted tiles and tiles that are already found 
            shownTiles = "";
            for (int tileX = 0; tileX < TILENUMBER; tileX++) {
                if ((tileX == indexMatch1) || (tileX == indexMatch2) || (matchedTiles.charAt(tileX) == 'T')) {
                    shownTiles += tiles.charAt(tileX);
                } else {
                    shownTiles += "X";
                }
            }

            //Print shown tiles 
            System.out.println(shownTiles);

            //If the two tiles are the same and has not been found yet 
            if ((tiles.charAt(indexMatch1) == tiles.charAt(indexMatch2)) && (matchedTiles.charAt(indexMatch1) == 'F') && (matchedTiles.charAt(indexMatch2) == 'F')) {
                System.out.println("You've matched 2 tiles!");
                matchedTilesCopy = matchedTiles;
                matchedTiles = "";

                //Create a new matchedTiles which holds whether the tile has been paired or not 
                for (int tileMatch = 0; tileMatch < TILENUMBER; tileMatch++) {
                    if (tileMatch == indexMatch1 || tileMatch == indexMatch2 || matchedTilesCopy.charAt(tileMatch) == 'T') {
                        matchedTiles += "T";
                    } else {
                        matchedTiles += "F";
                    }
                }
                correctGuess++;
            } //If the user already selected the pair 
            else if ((matchedTiles.charAt(indexMatch1) == 'T') && (matchedTiles.charAt(indexMatch2) == 'T')) {
                System.out.println("You've already selected this pair!");
            } else {
                System.out.println("Incorrect!");
            }

            //If the user guesses all the pairs, give them corresponding number of lumps 
            if (correctGuess == CORRECTWINS) {
                lumps += LUMPSMAXTILES - (tries - 5) * 4;
                System.out.println("You win!! You now have " + lumps + " lumps.");
                tilesWin = true;
                break;
            } else {
                System.out.println("You have " + (TRIESMAX - tries) + " guesses left. ");
            }
        }

        //If user runs out of guesses 
        if (tilesWin == false) {
            System.out.println("You lost! The string looked like this: " + tiles);
        }
    }

    //PET INTERACTION METHODS// 
    public static int petPlay(int maxEnergy, int petEnergy) {
        int newEnergy = petEnergy;
        String toyChoice = JOptionPane.showInputDialog(null, "Would you like to buy a toy for 1 lump?");
        if (toyChoice.equalsIgnoreCase("Yes")) {
            lumps -= 1;
            JOptionPane.showMessageDialog(null, "You give a toy to your pet. \n The shark chews up the toy, leaving a pile of scraps left.");
            if ((petEnergy + TOYWORTH) <= maxEnergy) {
                newEnergy += TOYWORTH;
            } else {
                newEnergy = maxEnergy;
            }
            JOptionPane.showMessageDialog(null, "Your pet now has " + newEnergy + " energy!");
        }
        return newEnergy;
    }

    public static int petFeed(int maxFood, int petFood) {
        int newFood = petFood;
        String foodChoice = JOptionPane.showInputDialog(null, "Would you like to buy food for 1 lump?");
        if (foodChoice.equalsIgnoreCase("Yes")) {
            lumps -= 1;
            JOptionPane.showMessageDialog(null, "You give some fish to your pet. \n The shark instantly devours the fish, and appears very content. ");
            if ((petFood + FOODWORTH) <= maxFood) {
                newFood += FOODWORTH;
            } else {
                newFood = maxFood;
            }
            JOptionPane.showMessageDialog(null, "Your pet now has " + newFood + " hunger!");
        }
        return newFood;
    }

    public static int petGroom(int maxHealth, int petHealth) {
        int newHealth = petHealth;
        String toyChoice = JOptionPane.showInputDialog(null, "Would you like to pet your shark?");
        if (toyChoice.equalsIgnoreCase("Yes")) {
            JOptionPane.showMessageDialog(null, "You stroke the back of your shark. It begins swimming in laps, evidently happy with being pet. ");
            if ((petHealth + GROOMWORTH) <= maxHealth) {
                newHealth += GROOMWORTH;
            } else {
                newHealth = maxHealth;
            }
            JOptionPane.showMessageDialog(null, "Your pet now has " + newHealth + " health!");
        }
        return newHealth;
    }

    //MAIN METHOD// 
    public static void main(String[] args) {

        //SCANNER & RANDOM//
        Scanner kb = new Scanner(System.in);
        Random rd = new Random();

        //CONSTANTS//
        //For naming 
        final int PETNAMEMAXLENGTH = 8;
        final int DOUBLEVOWELLENGTH = 6;
        final String VOWELS = "aeiou";
        final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";

        //For pet stats 
        final int TOTALPETSTATS = 20;
        final int STATNUM = 3;

        //For game 1 
        final int GUESSMAX = 5;
        final int LUMPSMULTIPLIERGUESS = 3;
        //VARIABLES// 

        //Read from file 
        int sharkChoice = 0;
        boolean generatePet = false;
        String petName = "";

        //For login 
        boolean startMenu = false;
        String username = "";
        String password = "";

        //Which screen on menu
        int startScreenOption = 0;

        //For naming
        int naming = 0;

        //For playing game 1 
        int playGame = 0;
        int randomGuess = 0;
        int guessedNumber = 0;

        //For Pet Interaction 
        int[] maxStats = new int[3];
        int[] currentStats = new int[3];
        int petAction = 0;

        // Start screen setup 
        System.out.println("""
                                                                                                            
                                                                -+++                                        
                                                               =#**+*+                       **             
                                                             #******=*+                   ***+--            
                                                           #****+*****++               +*#*++*=-            
                                                         *#*****+#***++*              =****+++=:+           
                                               ---++++****##**********+++*--        --+***++**+-:-          
                                        +=:=++**++=+===****+*++*******++++++**++==+==+*+++-=++-:=           
                                       +-------=---===++++********+=++********+***+===+=+-------            
                                       ----=--------=--==-=+**++++++++**+++**+==---=---=+---==-:-           
                                       -----:--:-:###-----==+**+++*+=+++++=::+*=-=--.:---=---=-.-           
                                       -::::--:-:####+++---=-===+==+**=-=+++*----::-:..::------..           
                                        =-..::-:::--:+++:=-:-=-===+:-+:*-=-:==+-::.-----::---=-.            
                                         =:  ^^ __::.::::.:::-:::::::::::==---:.:....-: .  ....             
                                           -::...--:::::::-:--:--.:::::::-:::......-X                       
                                            =-:::....::.:-:.:::::::.::::::::......-                         
                                          ##+#*-:...::..::..:::..:...........-= --                          
                                   #######*#**##  =.....:. ....::..... ... ..=                              
                                  ###############*   +++:.:::::::-:.:--.  . .-                              
                                   ################    =-:-====        --::--=+                             
                                    #############**   ***+*+*+*#*    *=###*##+#+                            
                                      ###########     *#########*    #=########*=                           
                                       *#######       *#########=    #+#########-                           
                                                      *#########*    ++#*#######+                           
                                                     ###########*    ==+#*######*                           
                                                    ############*   #*##*#######+                           
                                                    #########++     +==#######*+                            
                                                                                                            """);

        //Login Menu 
        while (startMenu == false) {
            username = JOptionPane.showInputDialog(null, "Enter your username:");
            File temporary = new File(username + ".txt");

            if (temporary.exists()) {
                password = JOptionPane.showInputDialog(null, "Enter your password:");
            } else {
                password = JOptionPane.showInputDialog(null, "Welcome new user! Enter a new password: ");
            }
            startMenu = correctLogin(username, password);

            //If invalid username / password 
            if (startMenu == false) {
                JOptionPane.showMessageDialog(null, "Invalid input. Try again.");
            }
        }

        //Variables taken from file
        File pet = new File(username + ".txt");

        try {
            Scanner sc = new Scanner(pet);
            sc.nextLine(); // to skip password
            sharkChoice = sc.nextInt();
            sc.nextLine(); //to clear cache, move onto next line
            generatePet = sc.nextBoolean();
            sc.nextLine();
            petName = sc.nextLine();

            //bring in max health, food, energy respectively
            for (int statNum = 0; statNum < STATNUM; statNum++) {
                maxStats[statNum] = sc.nextInt();
                sc.nextLine();
            }
            //bring in current health, food, energy respectively
            for (int statNum = 0; statNum < STATNUM; statNum++) {
                currentStats[statNum] = sc.nextInt();
                sc.nextLine();
            }
            lumps = sc.nextInt();
            sc.close();

        } catch (IOException e) { // If error occurs while reading
            System.out.println("Could not successfully read user file. Exiting...");
            System.exit(0);
        }

        JOptionPane.showMessageDialog(null, "Successful login! Please select an option from the menu: ");

        //START MENU//
        while (startMenu == true) {

            //First time start menu 
            if (generatePet == false) {
                startScreenOption = Integer.parseInt(JOptionPane.showInputDialog(null, """
                           1. START 
                           2. INSTRUCTIONS 
                           3. END 
                           """));
            } else {
                startScreenOption = Integer.parseInt(JOptionPane.showInputDialog(null, """
                           1. PLAY/INTERACT
                           2. INSTRUCTIONS 
                           3. END 
                           """));
            }

            //Determine what to do on start menu
            //If pet not generated yet 
            if ((startScreenOption == 1) && (generatePet == false)) {

                //Choose Shark
                sharkChoice = Integer.parseInt(JOptionPane.showInputDialog(null, "CHOOSE YOUR SHARK: \n HAMMERHEAD SHARK (1) \n BASKING SHARK (2) \n WHALE SHARK (3)"));
                if (1 <= sharkChoice && sharkChoice <= 3) {
                    switch (sharkChoice) {
                        case 1:
                            JOptionPane.showMessageDialog(null, "CONFIRM HAMMERHEAD SHARK");
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null, "CONFIRM BASKING SHARK");
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, "CONFIRM WHALE SHARK");
                            break;
                    }
                } else {
                    System.out.println("Invalid selection. Returning to menu...");
                    continue;
                }

                //Determine whether to input name or generate random name 
                System.out.println("Would you like to input a name (1) or generate a name (2)?");
                naming = Integer.parseInt(JOptionPane.showInputDialog(null, "Would you like to input a name (1) or generate a name (2)?"));

                //Input a pets name 
                if (naming == 1) {
                    System.out.println("Enter your pet's name: ");
                    kb.nextLine(); //To clear cache
                    petName = kb.nextLine(); //Actual input of name
                } //Generating a pets name 
                else if (naming == 2) {
                    petName = nameGenerator((PETNAMEMAXLENGTH - (rd.nextInt(4))), DOUBLEVOWELLENGTH, CONSONANTS, VOWELS);
                } //Invalid input for inputting name 
                else {
                    System.out.println("Invalid input. Returning to menu...");
                    continue;
                }

                //Display pet name 
                System.out.println("Your pet, named " + petName + ", has been born!");

                //Determine pet stats 
                maxStats[0] = rd.nextInt(17) + 1; // pet max health
                maxStats[1] = rd.nextInt((TOTALPETSTATS - maxStats[0] - 1)) + 1; // pet max food 
                maxStats[2] = TOTALPETSTATS - maxStats[0] - maxStats[1]; // pet max energy 
                System.out.println("Your max health is " + maxStats[0] + ", your max food is " + maxStats[1] + ", and your max energy is " + maxStats[2]);
                currentStats[0] = maxStats[0];
                currentStats[1] = maxStats[1];
                currentStats[2] = maxStats[2];

                //Allow interaction with pet 
                generatePet = true;

            } //Interact with the pet 
            else if ((startScreenOption == 1) && (generatePet == true)) {

                System.out.println("What would you like to do? Guessing Number game(1), Matching game(2), Interact(3)");
                playGame = kb.nextInt();

                //Play guessing number game 
                if (playGame == 1) {
                    System.out.println("Welcome to the number guessing game! You will have 5 guesses to guess a number between 1 and 100. You will get hints if you are too low or too high. You will earn 15 lumps at the beginning, and each guess is 3 lumps off. Good luck! ");
                    randomGuess = rd.nextInt(100) + 1;

                    //Obtain guess 5 times 
                    for (int guessTimes = 0; guessTimes < GUESSMAX; guessTimes += 1) {
                        System.out.println("Guess a number!");
                        guessedNumber = kb.nextInt();

                        //If guess is correct
                        if (guessedNumber == randomGuess) {
                            lumps += GUESSMAX * LUMPSMULTIPLIERGUESS - guessTimes * LUMPSMULTIPLIERGUESS;
                            System.out.println("Congrats, your guess was correct! You now have " + lumps + " lumps :P");
                            break;
                        }

                        //If guess is too low 
                        if (guessedNumber < randomGuess) {
                            System.out.println("Too low.");
                        } //If guess is too high 
                        else if (guessedNumber > randomGuess) {
                            System.out.println("Too high.");
                        }

                        //If number of guesses runs out 
                        if (guessTimes == GUESSMAX - 1) {
                            System.out.println("You ran out of guesses! The number was " + randomGuess + ". ");
                        }
                    }

                    //Play the Matching Game 
                } else if (playGame == 2) {
                    matchingGame();

                } else if (playGame == 3) {
                    petAction = Integer.parseInt(JOptionPane.showInputDialog(null, "Would you like to play with your pet (1), feed your pet (2), groom your pet(3), or exit out of interaction (4)?"));
                    switch (petAction) {
                        case 1:
                            if (currentStats[2] < maxStats[2]) {
                                petPlay(maxStats[2], currentStats[2]);
                            } else {
                                System.out.println("Pet energy already maxed!");
                            }
                            break;
                        case 2:
                            if (currentStats[1] < maxStats[1]) {
                                petFeed(maxStats[1], currentStats[1]);
                            } else {
                                System.out.println("Pet hunger already maxed!");
                            }
                            break;
                        case 3:
                            if (currentStats[0] < maxStats[0]) {
                                petGroom(maxStats[0], currentStats[0]);
                            } else {
                                System.out.println("Pet health already maxed!");
                            }
                            break;
                        case 4:
                            System.out.println("Leaving interaction menu...");
                            break;
                        default:
                            System.out.println("Invalid input.");
                            break;
                    }

                } else {
                    System.out.println("Invalid input.");
                }

            } //Open the instruction menu 
            else if (startScreenOption == 2) {
                System.out.println("INSTRUCTION MENU");
            } //Exit the program after saving all user data
            else if (startScreenOption == 3) {
                System.out.println("EXITING...");

                try {
                    File fl = new File(username + ".txt");
                    PrintWriter pr = new PrintWriter(fl);
                    pr.println(password);
                    pr.println(sharkChoice);
                    pr.println(generatePet);
                    pr.println(petName);
                    pr.println(maxStats[0]);
                    pr.println(maxStats[1]);
                    pr.println(maxStats[2]);
                    pr.println(currentStats[0]);
                    pr.println(currentStats[1]);
                    pr.println(currentStats[2]);
                    pr.println(lumps);
                    pr.close();

                    System.exit(0);
                } catch (IOException e) {
                    System.out.println("An error has occured while saving your file. Data is unable to be saved.");
                    System.exit(0);
                }
            } //If invalid input for start menu
            else {
                System.out.println("Invalid input. Please try again.");
            }
        }

    }
}
