package com.mycompany.java_library.users;


import java.io.IOException;
import java.util.Scanner;

import com.mycompany.java_library.library_function.libraryFunctions;
import com.mycompany.java_library.screen_functions.Functions;

public class Admin extends libraryFunctions{
      static libraryFunctions libFunc = new libraryFunctions();
      static Scanner scan = new Scanner(System.in);


      public void admin() throws IOException, InterruptedException{
        //Select option: viewbooks, viewBorredBooks, borrowBooks, exit
        while(true){
          System.out.println("""
                          Select Option
                          1. Add Books
                          2. View Books
                          3. Exit""");
          System.out.print("Enter Selected Option: ");
          int option = scan.nextInt();
          scan.nextLine();

          switch (option) {
              case 1 ->{
                libFunc.addBooks();
                break;
              }
              case 2 ->{
                libFunc.viewBooks();
                break;
              }
              case 3 ->{
                Functions.clear_screen(2000);
                return;
              }
              default -> {
                System.out.println("\nInvalid Input, Please choose from 1-3");
                Functions.clear_screen(2000);


              }
          }
            
        }
    }
}


