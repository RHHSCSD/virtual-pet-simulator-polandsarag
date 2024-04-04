/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;

import java.util.*;

/**
 * Program: Virtual Pet Simulator
 *
 * @author Sarah Cui Start date: 03/18/2024
 *
 */
public class VirtualPet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //SCANNER & RANDOM//
        Scanner kb = new Scanner(System.in);
        Random rd = new Random();

        //CONSTANTS//
        //For login 
        final int LOGINATTEMPTS = 3;
        final String PETUSER = "snoopy";
        final String PETPASSWORD = "toto";

        //For naming 
        final int PETNAMEMAXLENGTH = 8;
        final int DOUBLEVOWELLENGTH = 6;
        final String VOWELS = "aeiou";
        final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";

        //For pet stats 
        final int TOTALPETSTATS = 20;
        final int LUMPSMULTIPLIERGUESS = 3;

        //For game 1 
        final int GUESSMAX = 5;

        //For game 2 
        final String MATCHINGTILES = "ABCDE";
        final int TILENUMBER = 10;
        final int TRIESMAX = 7;

        //VARIABLES// 
        boolean startMenu = false;
        boolean generatePet = false;
        int startScreenOption = 0;
        int sharkChoice = 0;

        //For login 
        String username = "";
        String password = "";

        //For naming
        int naming = 0;
        String petName = "";
        char nameConsonant = ' ';
        char nameVowel = ' ';
        char petVowel = ' ';

        //For stats 
        int petMaxHealth = 0;
        int petMaxFood = 0;
        int petMaxEnergy = 0;
        int lumps = 0;

        //For playing game 1 
        int playGame = 0;
        int randomGuess = 0;
        int guessedNumber = 0;

        //For playing game 2 
        String tiles = "";
        int tile = 0;
        String shownTiles = "";
        int indexMatch1 = 0;
        int indexMatch2 = 0;
        int randomTile = 0;
        char addedTile = ' ';

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
                                           -::...--:::::::-:--:--.:::::::-:::......:-                       
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
        for (int tryNumber = 0; tryNumber < LOGINATTEMPTS; tryNumber++) {
            System.out.println("Enter your username:");
            username = kb.nextLine();
            System.out.println("Enter your password: ");
            password = kb.nextLine();

            //If username and password are correct, open menu
            if (username.equals(PETUSER) && password.equals(PETPASSWORD)) {
                startMenu = true;
                break;
            } //If invalid username / password 
            else {
                System.out.print("Invalid input. ");
                if (tryNumber == 2) {
                    System.out.println("Exiting ...");
                    System.exit(0);
                } else {
                    System.out.println("Try again. ");
                }
            }
        }

        while (startMenu == true) {
            //START MENU//

            //First time start menu 
            if (generatePet == false) {

                System.out.println("""
                           1. START 
                           2. INSTRUCTIONS 
                           3. END 
                           """);
                startScreenOption = kb.nextInt();
            } else {

                System.out.println("""
                           1. PLAY/INTERACT
                           2. INSTRUCTIONS 
                           3. END 
                           """);
                startScreenOption = kb.nextInt();
            }

            //Determine what to do on start menu
            //If pet not generated yet 
            if ((startScreenOption == 1) && (generatePet == false)) {

                //Choose Shark
                System.out.println("CHOOSE YOUR SHARK: \n HAMMERHEAD SHARK (1) \n BASKING SHARK (2) \n WHALE SHARK (3)");
                sharkChoice = kb.nextInt();
                if (1 <= sharkChoice && sharkChoice <= 3) {
                    switch (sharkChoice) {
                        case 1:
                            System.out.println("CONFIRM HAMMERHEAD SHARK");
                            break;
                        case 2:
                            System.out.println("CONFIRM BASKING SHARK");
                            break;
                        case 3:
                            System.out.println("CONFIRM WHALE SHARK");
                            break;
                    }
                }

                //Determine whether to input name or generate random name 
                System.out.println("Would you like to input a name (1) or generate a name (2)?");
                naming = kb.nextInt();

                //Input a pets name 
                if (naming == 1) {
                    System.out.println("Enter your pet's name: ");
                    petName = kb.nextLine();
                    petName = kb.nextLine();
                } //Generating a pets name 
                else if (naming == 2) {
                    for (int nameIndex = 0; nameIndex < (PETNAMEMAXLENGTH - rd.nextInt(4)); nameIndex++) {

                        //If pet name 
                        if (petName.length() < PETNAMEMAXLENGTH) {
                            //Every other letter is consonant 
                            if (nameIndex % 2 == 0) {
                                petName = petName + CONSONANTS.charAt(rd.nextInt(21));

                                //If first letter in name 
                                if (nameIndex == 0) {
                                    petName = petName.toUpperCase();
                                }
                            } //Every other letter is vowel 
                            else {
                                petVowel = VOWELS.charAt(rd.nextInt(5));

                                //1 in 5 chance of having double vowels if doesn't exceed name length
                                if (rd.nextInt(4) == 0 && petName.length() <= DOUBLEVOWELLENGTH) {
                                    petName = petName + petVowel + petVowel;
                                } //Otherwise Single vowel
                                else {
                                    petName = petName + petVowel;
                                }
                            }
                        }
                    }
                } //Invalid input for inputting name 
                else {
                    System.out.println("Invalid input ");
                    System.exit(0);
                }

                //Display pet name 
                System.out.println("Your pet, named " + petName + ", has been born!");

                //Determine pet stats 
                petMaxHealth = rd.nextInt(17) + 1;
                petMaxFood = rd.nextInt((TOTALPETSTATS - petMaxHealth - 1)) + 1;
                petMaxEnergy = TOTALPETSTATS - petMaxHealth - petMaxFood;
                System.out.println("Your max health is " + petMaxHealth + ", your max food is " + petMaxFood + ", and your max energy is " + petMaxEnergy);

                //Allow interaction with pet 
                generatePet = true;

            } //Interact with the pet 
            else if ((startScreenOption == 1) && (generatePet == true)) {

                System.out.println("Would you like to generate money? Guessing Number game(1), Matching game(2), No(3)");
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
                            System.out.println("Congrats, your guess was correct! You now have " + lumps + " lumps :P");
                            lumps += GUESSMAX * LUMPSMULTIPLIERGUESS - guessTimes * LUMPSMULTIPLIERGUESS;
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
                    System.out.println("Welcome to the matching game! You will have 5 pairs, 10 cards to match. Enter two positions from 1-10 to select two pairs. You will have 9 total matches available to make. You will earn 20 lumps for a flawless game (5 rounds), and subtract 4 for each additional round it takes to play.");

                    //Set up both displayed tiles and actual hidden tiles
                    for (int tileX = 0; tileX < TILENUMBER; tileX++) {
                        shownTiles += "X";
                    }
                    while (tile < TILENUMBER) {
                        randomTile = rd.nextInt(TILENUMBER / 2);
                        addedTile = MATCHINGTILES.charAt(randomTile);
                        if (tiles.indexOf(addedTile) == tiles.lastIndexOf(addedTile)) {
                            tiles += addedTile;
                            tile++;
                        }
                    }

                    for (int tries = 0; tries < TRIESMAX; tries++) {
                        System.out.println("Input your first and second index, 1 on each line");
                        indexMatch1 = kb.nextInt();
                        indexMatch2 = kb.nextInt();
                    }

                } else if (playGame == 3) {

                } else {
                    System.out.println("Invalid input.");
                    System.exit(0);
                }

            } //Open the instruction menu 
            else if (startScreenOption == 2) {
                System.out.println("INSTRUCTION MENU");
            } //Exit the program
            else if (startScreenOption == 3) {
                System.out.println("EXITING...");
                System.exit(0);
            } //If invalid input for start menu
            else {
                System.out.println("Invalid input");
                return;
            }
        }

    }
}
