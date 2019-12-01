package org;

import org.utils.Database;

import java.util.HashMap;
import java.util.Map;

public class User {
    private Map<String, String> cartWithPrice;
    private Map<String, Integer> cartWithQuantity;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String card;
    private Database db = new Database();

    public User(String username) {
        this.username = username;
        this.password = db.getPassword(username);
        this.firstName = db.getFirstName(username);
        this.lastName = db.getLastName(username);
        this.email = db.getEmail(username);
        this.phoneNumber = db.getPhoneNumber(username);
        this.card = db.getCard(username);
        this.cartWithPrice = new HashMap<>();
        this.cartWithQuantity = new HashMap<>();
    }

    public void addItem(String item, String value) {
        int i = 0;
        if (!cartWithQuantity.isEmpty() && cartWithQuantity.get(item) != null) {
            i = cartWithQuantity.get(item);
            cartWithPrice.put(item, value);
            i++;
            cartWithQuantity.put(item, i);
        } else {
            i++;
            cartWithPrice.put(item, value);
            cartWithQuantity.put(item, i);
        }
    }

    public Map<String, String> getCartWithPrice() {
        return cartWithPrice;
    }

    public Map<String, Integer> getCartWithQuantity() {
        return cartWithQuantity;
    }

    public String getPassword() { return password; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getEmail() { return email; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getUsername() { return username; }

    public String getCard() { return card; }


}
