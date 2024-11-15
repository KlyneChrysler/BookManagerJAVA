public class Book {
    private String title, author;
    private int id, year;

    public void setTitle( String title ) {
        this.title = title;
    }

    public void setAuthor( String author ) {
        this.author = author;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public void setYear( int year ) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor(){
        return author; 
    }

    public int getId(){
        return id;
    }

    public int getYear(){
        return year;
    }
}