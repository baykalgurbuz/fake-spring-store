package com.spring.fakestore.fakestore.models;

public class Proposal {

    private long id;

    private String note;

    private int price;

    private long user_id;

    private String status;

    private long product_id;

    public Proposal() {
    }

    public Proposal(String note, int price, long user_id, String status, long product_id) {
        this.note = note;
        this.price = price;
        this.user_id = user_id;
        this.status = status;
        this.product_id = product_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", price=" + price +
                ", user_id=" + user_id +
                ", status='" + status + '\'' +
                ", product_id=" + product_id +
                '}';
    }
}
