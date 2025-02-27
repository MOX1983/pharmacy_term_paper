package com.example.pharmacy;

import java.util.Date;

public class Pills {
    private int idUser;
    private String name;
    private String description;
    private int quantity;
    private Date expiratioDate;

    public Pills( String name, String description, int quantity, Date expiratioDate) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.expiratioDate = expiratioDate;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpiratioDate() {
        return expiratioDate;
    }

    public void setExpiratioDate(Date expiratioDate) {
        this.expiratioDate = expiratioDate;
    }

    @Override
    public String toString() {
        return "Pills{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", expiratio Date=" + expiratioDate +
                '}';
    }
}
