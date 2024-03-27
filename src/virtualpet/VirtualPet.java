/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpet;
import java.util.*;
/**
 * Program: Virtual Pet Simulator 
 * @author Sarah Cui 
 * Start date: 03/18/2024 
 * 
 */
public class VirtualPet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //SCANNER & RANDOM//
        Scanner kb = new Scanner (System.in);
        Random rd = new Random (); 
        
        //CONSTANTS//
        
        //For login 
        final String petUser = "snoopy";
        final String petPassword = "toto";
        
        //For naming 
        final int PETNAMEMAXLENGTH = 8; 
        final int DOUBLEVOWELLENGTH = 6;
        final String VOWELS = "aeiou";
        final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";
        
        //For pet stats 
        final int totalPetStats = 20; 
        
        //VARIABLES// 
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
        System.out.println("Enter your username:");
        username = kb.nextLine(); 
        System.out.println("Enter your password: ");
        password = kb.nextLine(); 
        
        
        //If username and password are correct, open menu
        if (username.equals(petUser) && password.equals(petPassword)) {

        //Start Menu 
        System.out.println("""
                           1. START 
                           2. INSTRUCTIONS 
                           3. END 
                           """);
        startScreenOption = kb.nextInt();
        
        //Determine what to do on start menu
        if (startScreenOption == 1){
            
            //Choose Shark
            System.out.println("CHOOSE YOUR SHARK: \n HAMMERHEAD SHARK (1) \n BASKING SHARK (2) \n WHALE SHARK (3)");
            sharkChoice = kb.nextInt();
            if ( 1 <= sharkChoice && sharkChoice <= 3){
                switch (sharkChoice){
                    case 1: System.out.println("CONFIRM HAMMERHEAD SHARK"); break;
                    case 2: System.out.println("CONFIRM BASKING SHARK"); break;
                    case 3: System.out.println("CONFIRM WHALE SHARK"); break;
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
            }
            
            //Generating a pets name 
            else if (naming == 2) {
                for (int nameIndex=0; nameIndex<(PETNAMEMAXLENGTH - rd.nextInt(4)); nameIndex++) {
                    
                    //If pet name 
                    if (petName.length()<PETNAMEMAXLENGTH) {
                    //Every other letter is consonant 
                    if (nameIndex %2 == 0){
                        petName = petName + CONSONANTS.charAt(rd.nextInt(21));
                        
                        //If first letter in name 
                        if (nameIndex == 0) {
                            petName = petName.toUpperCase(); 
                        }
                    }
                    
                    //Every other letter is vowel 
                    else {
                        petVowel = VOWELS.charAt(rd.nextInt(5));
                        
                        //1 in 5 chance of having double vowels if doesn't exceed name length
                        if (rd.nextInt(4)==0 && petName.length()<=DOUBLEVOWELLENGTH) { 
                            petName = petName + petVowel + petVowel;
                        }
                        
                        //Otherwise Single vowel
                        else {
                        petName = petName + petVowel;
                        }
                    }    
                    }
                }
            }
            
            //Invalid input for inputting name 
            else {
                System.out.println("Invalid input ");
                System.exit(0);
            }
            
            //Display pet name 
            System.out.println("Your pet, named "+petName+", has been born!");
            
            
            //Determine pet stats 
            petMaxHealth = rd.nextInt(17)+1;
            petMaxFood = rd.nextInt((totalPetStats-petMaxHealth))+1;
            petMaxEnergy = totalPetStats - petMaxHealth - petMaxFood;        
            System.out.println("Your max health is "+petMaxHealth+", your max food is "+petMaxFood+", and your max energy is "+petMaxEnergy);
        }
        
        //Open the instruction menu 
        else if (startScreenOption == 2) {
            System.out.println("INSTRUCTION MENU");
        }
        
        //Exit the program
        else if (startScreenOption == 3) {
            System.out.println("EXITING...");
            System.exit(0);
        }
        
        //If invalid input for start menu
        else {
            System.out.println("Invalid input");
            return;
        }
        }
        
        //If invalid username / password 
        else {
            System.out.println("Invalid input");
            System.exit(0);
        }
    }    
}
