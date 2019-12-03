package org.tests;

import junit.framework.Assert;
import org.junit.Test;
import org.utils.Database;

import java.util.List;

public class DatabaseUnitTests {

    private Database db = new Database();
    // This test uses the method getName() to ensure that the correct name of the User
    @Test
    public void testGetUsersName() {
        String username = "jmcbride";

        String dbName = db.getFirstName(username);

        Assert.assertEquals("Josh", dbName);
    }

    // This test ensures that if an item is passed through to checkInventory(), if it exists in the Database, it returns true
    @Test
    public void testCheckItemFromInventory() {
        String item = "Soda";
        boolean checkItem = db.checkInventory(item);

        Assert.assertTrue(checkItem);
    }

    // This test ensures that if an item is requested from the database, it is returned with its value
    @Test
    public void testGetItem() {
        String item = "Soda";
        List<String> itemFromDatabase = db.getItem(item);
        Assert.assertEquals("Soda", itemFromDatabase.get(0));
        Assert.assertEquals("2.00", itemFromDatabase.get(1));
    }
}
