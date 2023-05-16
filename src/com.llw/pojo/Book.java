package com.llw.pojo;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {
    private Integer id;
    private String name;
    private BigDecimal price;
    private String author;
    private Integer sales;
    private Integer stock;
    private String img_path = "static/img/default.jpg";
    public Book() {
    }
    public Book(Integer id, String name, BigDecimal price, String author, Integer sales, Integer stock, String img_path) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.author = author;
        this.sales = sales;
        this.stock = stock;
        //给定的图书封面路径不能为空
        if (img_path != null && !"".equals(img_path)) {
            this.img_path = img_path;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId()) && getName().equals(book.getName()) && getPrice().equals(book.getPrice()) && getAuthor().equals(book.getAuthor()) && getSales().equals(book.getSales()) && getStock().equals(book.getStock()) && getImg_path().equals(book.getImg_path());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getAuthor(), getSales(), getStock(), getImg_path());
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        if (img_path != null && !"".equals(img_path)) {
            this.img_path = img_path;
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                ", sales=" + sales +
                ", stock=" + stock +
                ", img_path='" + img_path + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


}
