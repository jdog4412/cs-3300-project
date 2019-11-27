package org;

import org.utils.Database;

import java.util.HashMap;
import java.util.Map;

public class User {
    private Map<String, String> cart;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String card;
    private Database db = new Database();

    public User(String username) {
        this.username = username;
        this.firstName = db.getFirstName(username);
        this.lastName = db.getLastName(username);
        this.email = db.getEmail(username);
        this.phoneNumber = db.getPhoneNumber(username);
        this.card = db.getCard(username);
        this.cart = new HashMap<>();
    }

    public void addItem(String item, String value) {
        cart.put(item, value);
    }

    public Map<String, String> getCart() {
        return cart;
    }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getUsername() { return username; }


}
