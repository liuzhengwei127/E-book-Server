package cn.liuzhengwei.ebook.domain;

//Book实体定义
public class Book {
    private String name;
    private String author;
    private String ISBN;
    private String newisbn;
    private String outline;
    private Integer stock;
    private Float price;
    private String url;

    public String getNewisbn() {
        return newisbn;
    }

    public void setNewisbn(String newisbn) {
        this.newisbn = newisbn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
