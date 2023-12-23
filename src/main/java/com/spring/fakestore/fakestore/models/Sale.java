package com.spring.fakestore.fakestore.models;

public class Sale {

    private long id;

    private int note;

    private int price;

    private long product_id;

    public Sale() {
    }

    public Sale(long id, int note, int price, long product_id) {
        this.id = id;
        this.note = note;
        this.price = price;
        this.product_id = product_id;
    }

    public long getId() {
        return id;
    }


    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", note=" + note +
                ", price=" + price +
                ", product_id=" + product_id +
                '}';
    }
}
