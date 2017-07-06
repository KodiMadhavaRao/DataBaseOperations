package com.example.madhava.databaseoperations;

/**
 * Created by madhava on 7/6/2017.
 */

/**
 * Getter setters
 * We have used custom this objet in array list to store values
 */
public class data
{

    int id;
    String itemName;
    int price;

    public data(int id, String itemName, int price) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
    }

    public data() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
