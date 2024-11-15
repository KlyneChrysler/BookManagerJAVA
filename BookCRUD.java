import java.util.ArrayList;

public class BookCRUD{
    private ArrayList<Book> books;

    public BookCRUD(){
        books = new ArrayList<Book>();
    }
    
    public ArrayList<Book> getAllBooks(){
        return books;
    }
        
    public void createBook(Book book){
        boolean flag = true;
        for(Book record : books){
            try{
                if(record.getId() == book.getId()){
                    throw new ErrorHandler("DUPLICATE ID ERROR.");
                }
            }
            catch(ErrorHandler e){
                flag = false;
                System.out.println(e.getMessage());
            }
        }
        if(flag){
            books.add(book);
        }
    }

    public Book retrieveBook(int id){
        Book record = new Book();
        for(Book book : books){
            if(book.getId() == id){
                record.setId(book.getId());
                record.setTitle(book.getTitle());
                record.setAuthor(book.getAuthor());
                record.setYear(book.getYear());
                return record;
            }
        }
        return null;
    }

    public void updateBook(Book record){ 
        for(Book book : books){
            if(book.getId() == record.getId()){
                books.set(books.indexOf(book), record);
                break;
            }
        }
    }

    public void deleteBook(int id, String title){
        for(Book book : books){
            if(book.getId() == id && book.getTitle().equals(title)){
                books.remove(book);
                break;
            }
        }
    }

    public void clearRecord(){
        books.clear();
    }

    public int arrayListSize(){
        return books.size();
    }
}