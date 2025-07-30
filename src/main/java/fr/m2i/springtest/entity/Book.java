package fr.m2i.springtest.entity;

public class Book {
    private Long id;
    private String title;
    private String author;
    private boolean isBestSeller;

    public Book() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book(Long id, String title, String author, boolean isBestSeller) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isBestSeller = isBestSeller;
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

    public boolean isBestSeller() {
        return isBestSeller;
    }

    public void setBestSeller(boolean bestSeller) {
        isBestSeller = bestSeller;
    }

    public Book(String title, String author, boolean isBestSeller) {
        this.title = title;
        this.author = author;
        this.isBestSeller = isBestSeller;
    }
}
