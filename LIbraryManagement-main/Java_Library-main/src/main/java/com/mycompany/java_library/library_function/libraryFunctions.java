package com.mycompany.java_library.library_function;
import java.util.*;
import java.io.*;

import com.mycompany.java_library.screen_functions.Functions;

    public class libraryFunctions {
    
    static Scanner scan = new Scanner(System.in);
    private static File book_file, borrowedBooksFile;
    
    protected String student_name = "",
                     student_ID = "",
                     book_title = "",
                     book_author = "", 
                     book_isbn = "";
    
    ///////////////
    ///  BOOKS  ///
    ///////////////
    public void writeHeader_Books() throws IOException {
        try (FileWriter fileWriter = new FileWriter(book_file, true)) {
            fileWriter.write(String.format("%-35s | %-35s | %-20s | %n", 
                            "Book Author", "Book Title", "Book Number"));
                 // Write footer divider
            fileWriter.append(String.format("%-35s | %-35s | %-20s | %n", 
                 "===================================", "===================================", "===================="));
        }
    }
    public void fileWriter_Books(String book_title, String book_author, String book_isbn) throws IOException {
        if (book_file.length() == 0) {
            writeHeader_Books(); // Make sure to call the correct header method
        }
        
        try (FileWriter fileWrite = new FileWriter(book_file, true)) { 
            fileWrite.append(String.format("%-35s | %-35s | %-20s | %n", 
                        book_author, book_title, book_isbn));
        }
    }

    // Library Books Functions
    public void addBooks() throws IOException, InterruptedException { 
        Functions.clear_screen(2000);

        // Create the file
        book_file = new File("Books.txt");

        System.out.println("///////////////////////");
        System.out.println("///    ADD BOOKS    ///");
        System.out.println("///////////////////////");

        System.out.print("Enter Book Title: ");
        this.book_title = scan.nextLine();

        System.out.print("Enter Book Author: ");
        this.book_author = scan.nextLine();

        System.out.print("Enter ISBN: ");
        this.book_isbn = scan.nextLine();

        fileWriter_Books(book_title, book_author, book_isbn);

        Functions.clear_screen(2000);
    }
    public void viewBooks()throws InterruptedException{
        try{
            Functions.clear_screen(2000);

            book_file = new File("Books.txt");
            Scanner fileReader = new Scanner(book_file);

            System.out.println("///////////////////////");
            System.out.println("///   BOOKS LIST    ///");
            System.out.println("///////////////////////");

            System.out.println("===================================================================================================");
            while(fileReader.hasNextLine()){
                String data = fileReader.nextLine();
                System.out.println(data);
            }
            fileReader.close();
            System.out.println("===================================================================================================");

        
        }catch (FileNotFoundException | InterruptedException e){
            System.out.println("File Not Found!!");
            System.out.println();
            Functions.clear_screen(2000);
        }
    }

    //////////////////////
    ///  BORROW BOOKS  ///
    //////////////////////
    public void writeHeader_Borrowers() throws IOException {
        try (FileWriter fileWriter = new FileWriter(borrowedBooksFile, true)) {
            fileWriter.write(String.format("%-35s | %-15s | %-35s | %-35s | %-20s | %n", 
                             "Student Name", "Student ID", "Book Title", "Book Author", "Book Number"));
            fileWriter.write(String.format("%-35s | %-15s | %-35s | %-35s | %-20s | %n", 
            "===================================", "===============","===================================", "===================================","===================="));
        }
    }
    public void fileWriter_Borrowers(String student_name, String student_ID, String book_title, String book_author, String book_isbn) throws IOException {
        // Check if the file is empty, if so, write the header
        if (borrowedBooksFile.length() == 0) {
            writeHeader_Borrowers();
        }
        try (FileWriter fileWriter = new FileWriter(borrowedBooksFile, true)) {
            fileWriter.append(String.format("%-35s | %-15s | %-35s | %-35s | %-20s | %n", 
                            student_name, student_ID, book_title, book_author, book_isbn));
        }
    }

    //Library Functions
    public void borrowBooks() throws IOException, InterruptedException{
        Functions.clear_screen(2000);
        
        // Ensure the borrowed books file exists
        borrowedBooksFile = new File("Borrowed_Books.txt");
        if (!borrowedBooksFile.exists()) {
            borrowedBooksFile.createNewFile();
        }
        
        viewBooks();

        System.out.print("Enter Student Name: ");
        student_name = scan.nextLine();

        System.out.print("Enter Student ID: ");
        student_ID = scan.nextLine();

        System.out.print("Enter ISBN or Book Title: ");
        String searchBook = scan.nextLine();
     

        boolean bookFound = false;
        boolean is_borrowed = false;
        try (BufferedReader br = new BufferedReader(new FileReader("Books.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                //extracted info from splits, creates new fields
                String  foundAuthor = parts[0].trim(),
                        foundTitle = parts[1].trim(),
                        foundISBN = parts[2].trim();

                if ((searchBook.equalsIgnoreCase(foundTitle) || searchBook.equalsIgnoreCase(foundISBN)) && (!line.startsWith("Borrowed by"))) {
                    
                    try(BufferedReader reader = new BufferedReader(new FileReader("Borrowed_Books.txt"))){
                        String borrowedLine;

                        while((borrowedLine = reader.readLine()) != null){
                            if(borrowedLine.contains(foundISBN) || borrowedLine.contains(foundTitle)){
                                is_borrowed = true;
                                break;
                            }
                        }
                        if(is_borrowed){
                            System.out.println("This Book has already been borrowed");
                            Functions.clear_screen(2000);
                            return;
                        }
                    }
                    bookFound = true;
                    System.out.println("Book found!");
                    // return info to rightful fields
                    book_author = foundAuthor;
                    book_title = foundTitle;
                    book_isbn = foundISBN;

                    fileWriter_Borrowers(student_name, student_ID, book_title, book_author, book_isbn);

                    Functions.clear_screen(2000);
                }
                
            }
            if(!bookFound){
                System.out.println("Book Not Found or Already Borrowed");
                System.out.println();
                Functions.clear_screen(2000);
            }
        }

    }
    public void viewBorrowedBooks() throws InterruptedException {
        try {
            Functions.clear_screen(2000);
            borrowedBooksFile = new File("Borrowed_Books.txt");
            Scanner fileReader = new Scanner(borrowedBooksFile);
            
            if(borrowedBooksFile.exists()){
                System.out.println("Borrowed Books:");

                while(fileReader.hasNextLine()){
                    String dataBorrowBooks = fileReader.nextLine();
                    System.out.println(dataBorrowBooks);
                }
                fileReader.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            Functions.clear_screen(2000);
        }
    }
}