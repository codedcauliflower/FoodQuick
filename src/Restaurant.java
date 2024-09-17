import java.util.HashMap;
import java.util.Map;

// Class representing a Restaurant
public class Restaurant {
    // Attributes of a restaurant
    private String name;
    private String location;
    private String contactNumber;
    private Map<String, Double> menu;

    // Constructor to initialize a Restaurant object
    public Restaurant(String name, String location, String contactNumber) {
        this.name = name;
        this.location = location;
        this.contactNumber = contactNumber;
        this.menu = new HashMap<>();
    }

    // Getters for Restaurant attributes
    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getContactNumber() { return contactNumber; }
    public Map<String, Double> getMenu() { return menu; }

    // Method to add a menu item to the restaurant
    public void addMenuItem(String item, double price) {
        menu.put(item, price);
    }

    // Method to display the menu with item numbers
    public void displayMenuWithNumbers() {
        System.out.println("Menu:");
        int i = 1;
        for (Map.Entry<String, Double> entry : menu.entrySet()) {
            System.out.println(i + ". " + entry.getKey() + ": R" + entry.getValue());
            i++;
        }
    }

    // Method to get an item by its number from the menu
    public String getItemByNumber(int number) {
        int i = 1;
        for (String item : menu.keySet()) {
            if (i == number) {
                return item;
            }
            i++;
        }
        return null; // Return null if the item number is invalid
    }

    // Method to display restaurant information
    public void displayInfo() {
        System.out.println("Restaurant Information:");
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Menu: ");
        menu.forEach((item, price) -> System.out.println(item + ": R" + price));
    }
}
