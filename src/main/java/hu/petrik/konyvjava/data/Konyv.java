package hu.petrik.konyvjava.data;

public class Konyv {
    private int id;
    private String title;
    private String author;

    private int publishYear;
    private int pageCount;

    public Konyv(int id, String title, String author, int publishYear, int pageCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.pageCount = pageCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "\tSzerző: " + author +
                "\n\tCím " + title +
                "\n\tKiadás éve: " + publishYear +
                "\n\tOldalszám: " + pageCount;
    }
}
