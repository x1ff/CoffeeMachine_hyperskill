import java.util.Objects;

class Book {
    public String title;
    public int yearOfPublishing;
    public String[] authors;

    Book(String title, int yearOfPublishing, String[] authors) {
        this.title = title;
        this.yearOfPublishing = yearOfPublishing;
        this.authors = Objects.requireNonNullElseGet(authors, () -> new String[0]);
    }
}