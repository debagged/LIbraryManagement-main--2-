package com.mycompany.java_library;
//@author gelo

import java.util.*;
import com.mycompany.java_library.screen_functions.*;
import com.mycompany.java_library.users.UserData;


public class Java_Library{
    static Scanner scan = new Scanner(System.in);
    static UserData user = new UserData();

    public static void main(String[] args) throws Exception{
        mainMenu();
            while(true){ 
                System.out.println("""
                                    Select Action
                                    1. Log in
                                    2. Register
                                    3. Exit""");
                System.out.print("Enter Selected Action: ");
            
                try {
                    int role  = scan.nextInt();
    
                    switch(role){
                        case 1 -> {
                            Functions.clear_screen(3000);
                            user.login_prompt();
                            break;
                        }
                        case 2 -> {
                            Functions.clear_screen(3000);
                            user.register_prompt();
                            break;
                        }
                        case 3 -> {
                            return;
                        }
    
                        default -> {        
                            System.out.println("\nPlease try again!, Input is not in the option");
                            Functions.clear_screen(3000);
                            mainMenu();
                            continue;
                        }
                    }
    
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("\nE: Input is not in the option, Try Again.");
                    scan.nextLine();
                    Functions.clear_screen(3000);
                    mainMenu();
                }
            }
    }
        
        
    public static void menu() throws InterruptedException{
            System.out.println();
            System.out.println("█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█");
            System.out.println("█    WELCOME TO OUR LIBRARY APPLICATION!    █");
            System.out.println("█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█");
            System.out.println();    
            Thread.sleep(3000);
    }
       
    public static void mainMenu() throws InterruptedException{
        Functions.clear_screen(200);
        System.out.println("█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█");
        System.out.println("█    WELCOME TO OUR LIBRARY APPLICATION!    █");
        System.out.println("█▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█");
        System.out.println();    
        Thread.sleep(3000);
    
        blinkingMessageWithStaticScreen();
    }
    public static void blinkingMessageWithStaticScreen() throws InterruptedException {
        String message = """
        *********************************************************************
        **  Admins have their username and password embedded in the system **
        **  Students, please register!                                     **   
        *********************************************************************    
        """;

        String blinkingMessage = """
        *********************************************
        *     Manage books, borrow, and explore!    *
        *       Bringing the library to you.        *
        *********************************************
        """;
    
        for (int i = 0; i < 10; i++) { // Adjust loop count for desired blinking duration
            // Move the cursor to a position after the static content
            System.out.print("\033[" + 5 + ";0H"); // Moves to line 14, column 0
            System.out.flush();
    
            // Print the blinking message or clear it
            if (i % 2 == 0) {
                System.out.println(message);
                System.out.println(blinkingMessage);
            } else {
                // Clear the blinking message area
                System.out.print("\033[J");
            }
    
            // Wait for half a second
            Thread.sleep(300);
        }
    
        // Ensure the message is displayed at the end
        System.out.print("\033[" + 5 + ";0H");
        System.out.flush();
        System.out.println(message);
        System.out.println(blinkingMessage);
    }
        
    
}


 