import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static Scanner scan = new Scanner ( System.in );
    public static BookCRUD bookManager = new BookCRUD();
    public static void main ( String[] args ) {

        boolean flag = true;
        int id = 0, choice = 0,  year = 0;
        String title = "", author = "";

        while ( flag ) {
            System.out.println("========================================");
            System.out.println("ENTER NUMBER TO PERFORM ACTION:");
            System.out.print("1 - ADD BOOK\2 - RETRIEVE BOOK\n3 - UPDATE BOOK\n4 - DELETE BOOK\n5 - CLEAR RECORDS\n6 - EXIT PROGRAM\n> ");
            try {
                choice = Integer.parseInt(scan.nextLine());
                switch ( choice ) { 
                    case 1: 
                        System.out.println("--------------- ADD BOOK ---------------");
                        System.out.print("ENTER BOOK ID: ");
                        id = Integer.parseInt(scan.nextLine());
                        System.out.print("ENTER BOOK TITLE: ");
                        title = scan.nextLine();
                        if ( title.isBlank() ) {
                            throw new ErrorHandler("TITLE CANNOT BE EMPTY.");
                        }
                        System.out.print("Enter book author: ");
                        author = scan.nextLine();
                        if ( author.isBlank() ) {
                            throw new ErrorHandler("AUTHOR CANNOT BE EMPTY.");
                        }
                        System.out.print("Enter book year: ");
                        year = Integer.parseInt(scan.nextLine());
                        createBook(id, title, author, year);
                        break;

                    case 2:
                        System.out.println("--------------- RETRIEVE BOOK ---------------");
                        System.out.println("--------------------------------------------");
                        displayBooks();
                        if ( bookManager.arrayListSize() == 0 ) {
                            System.out.println("NO BOOKS AVAILABLE.");
                            break;
                        }
                        System.out.print("ENTER BOOK ID: ");
                        id = Integer.parseInt(scan.nextLine());
                        if ( retrieveBook(id) == null ) {
                            throw new ErrorHandler("BOOK ID " + id + " DOES NOT EXIST!");
                        }
                        break;
                        
                    case 3: 
                        System.out.println("--------------- UPDATE BOOK ---------------");
                        displayBooks();
                        System.out.print("ENTER BOOK ID: ");
                        id = Integer.parseInt(scan.nextLine());

                        Book record = retrieveBook(id);
                        if ( record == null ) {
                            throw new ErrorHandler("BOOK ID " + id + " DOES NOT EXIST!");
                        }
                        System.out.print("ENTER NEW BOOK TITLE: ");
                        title = scan.nextLine();
                        if ( title.isEmpty() ) {
                            throw new ErrorHandler("TITLE CANNOT BE EMPTY.");
                        }
                        System.out.print("ENTER NEW BOOK AUTHOR: ");
                        author = scan.nextLine();
                        if ( author.isEmpty() ) {
                            throw new ErrorHandler("BOOK AUTHOR CANNOT BE EMPTY.");
                        }
                        System.out.print("ENTER NEW BOOK YEAR: ");
                        year = Integer.parseInt(scan.nextLine());
                        
                        updateBook(record, title, author, year);
                        break;

                    case 4:
                        System.out.println("--------------- DELETE BOOK ----------------");
                        System.out.println("--------------------------------------------");
                        displayBooks();
                        System.out.print("ENTER BOOK ID: ");
                        id = Integer.parseInt(scan.nextLine());
                        System.out.print("ENTER BOOK TITLE: ");
                        title = scan.nextLine();
                        deleteBook(id, title);
                        break;

                    case 5:
                        bookManager.clearRecord();
                        System.out.println("--------------- RECORD CLEARED ---------------");
                        break;

                    case 6:
                        flag = false;
                        break;
                    

                    default:
                        throw new ErrorHandler("PLEASE ENTER NO. 1-6 ONLY.");                  
                }
            }   
            catch(ErrorHandler e){
                System.out.println(e.getMessage());
            }
            catch(Exception e){
                System.out.println("PLEASE ENTER ONLY NUMBERS.");
                flag = looper();
            }
        }
    }

    public static boolean looper(){
        char choice;
        System.out.println("DO YOU WANT TO CONTINUE? Y/N");
        try {
            choice = scan.nextLine().toUpperCase().charAt(0);
        } catch ( Exception err ) {
            System.out.println("VALUE MUST BE Y OR N ONLY OR MUST NOT BE EMPTY");
            System.out.println("PROGRAM WILL STOP.");
            return false;
        }

        if ( choice == 'Y' ) {
            return true;
        } else {
            return false;
        }
    }
    public static void displayBooks(){
        ArrayList<Book> books = bookManager.getAllBooks();
        int count = 0;
        System.out.println("--------------- BOOK RECORDS ---------------");
        for ( Book book : books ) {
            System.out.println("============================================");
            System.out.println("INDEX: " + count + "\n| BOOK ID:      " + book.getId() + "\n| BOOK TITLE:   " + book.getTitle() 
            + "\n| BOOK AUTHOR:  " + book.getAuthor() + "\n| BOOK YEAR:    " + book.getYear());
            System.out.println("============================================");
            count++;
        }
        System.out.println("--------------------------------------------");
    }

    public static void createBook(int id, String title, String author, int year){
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(author);
        book.setYear(year);
        bookManager.createBook(book);
    }

    public static Book retrieveBook(int id){
        Book record = bookManager.retrieveBook(id);
        if ( record == null ) {
            return record;
        }
        System.out.println("RETRIEVED BOOK RECORD WITH ID: " + id);
        System.out.println("BOOK ID:      " + record.getId() + "\nBOOK TITLE:   " + record.getTitle() 
            + "\nBOOK AUTHOR:  " + record.getAuthor() + "\nBOOK YEAR:    " + record.getYear());
        System.out.println("--------------------------------------------");
        return record;
    }

    public static void updateBook(Book book, String title, String author, int year) {
        book.setTitle(title);
        book.setAuthor(author);
        book.setYear(year);
        bookManager.updateBook(book);
        System.out.println("UPDATED BOOK RECORD WITH ID: " + book.getId());
    }

    public static void deleteBook(int id, String title) {
        bookManager.deleteBook(id, title);
        System.out.println("DELETED BOOK RECORD WITH ID AND TITLE: " + id + " " + title);
    }
}