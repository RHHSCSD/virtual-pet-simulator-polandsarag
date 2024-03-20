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
        
        //SCANNER//
        Scanner kb = new Scanner (System.in);
        //CONSTANTS//
        
        //VARIABLES// 
        int startScreenOption = 0;
        int sharkChoice = 0;
                
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
        
        //Start Menu 
        System.out.println("""
                           1. START 
                           2. INSTRUCTIONS 
                           3. END 
                           """);
        startScreenOption = kb.nextInt();
        
        //Determine what to do on start menu
        if (startScreenOption == 1){
            System.out.println("CHOOSE YOUR SHARK: \n HAMMERHEAD SHARK (1) \n BASKING SHARK (2) \n WHALE SHARK (3)");
            sharkChoice = kb.nextInt();
            if ( 1 <= sharkChoice && sharkChoice <= 3){
                switch (sharkChoice){
                    case 1: System.out.println("CONFIRM HAMMERHEAD SHARK"); break;
                    case 2: System.out.println("CONFIRM BASKING SHARK"); break;
                    case 3: System.out.println("CONFIRM WHALE SHARK"); break;
                }
            }
        }
        else if (startScreenOption == 2) {
            System.out.println("INSTRUCTION MENU");
        }
        
        else if (startScreenOption == 3) {
            System.out.println("EXITING...");
            System.exit(0);
        }
        else {
            System.out.println("Invalid input");
            return;
        }
        
    }    
}
