package org;

import org.utils.Database;

import java.util.HashMap;
import java.util.Map;

public class User {
    private Map<String, String> cart;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String card;
    private Database db = new Database();

    public User(String username) {
        this.firstName = db.getFirstName(username);
        this.lastName = db.getLastName(username);
        this.email = db.getEmail(username);
        this.address = db.getAddress(username);
        this.card = db.getCard(username);
        this.cart = new HashMap<>();
    }

    public void addItem(String item, String value) {
        cart.put(item, value);
    }

    public Map<String, String> getCart() {
        return cart;
    }
}
