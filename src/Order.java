import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

// Class representing an Order
public class Order {
    // Static counter to generate unique order numbers
    private static int orderCounter = 1;

    // Attributes of an order
    private int orderNumber;
    private Customer customer;
    private Restaurant restaurant;
    private Map<String, Integer> items;
    private String specialInstructions;
    private double totalAmount;
    private Driver assignedDriver;

    // Constructor to initialize an Order object
    public Order(Customer customer, Restaurant restaurant) {
        this.orderNumber = orderCounter++;
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = new HashMap<>();
        this.specialInstructions = "";
        this.totalAmount = 0.0;
    }

    // Getters and setters for Order attributes
    public int getOrderNumber() { return orderNumber; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Restaurant getRestaurant() { return restaurant; }
    public Map<String, Integer> getItems() { return items; }
    public String getSpecialInstructions() { return specialInstructions; }
    public double getTotalAmount() { return totalAmount; }
    public Driver getAssignedDriver() { return assignedDriver; }

    // Method to add an item to the order
    public void addItem(String item, int quantity) {
        items.put(item, quantity);
        double itemPrice = restaurant.getMenu().get(item);
        totalAmount += itemPrice * quantity;
    }

    // Method to set special instructions for the order
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    // Method to assign a driver to the order
    public void assignDriver(Driver driver) {
        this.assignedDriver = driver;
        driver.updateLoad(driver.getCurrentLoad() + 1);
    }

    // Method to generate an invoice for the order
    public void generateInvoice() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("invoice_" + orderNumber + ".txt"))) {
            pw.println("Invoice:");
            pw.println("Order Number: " + orderNumber);
            pw.println("Customer Name: " + customer.getName());
            pw.println("Customer Location: " + customer.getLocation());
            pw.println("Restaurant Name: " + restaurant.getName());
            pw.println("Items:");
            items.forEach((item, quantity) -> pw.println("  " + item + " x " + quantity + " @ R" + restaurant.getMenu().get(item)));
            pw.println("Total Amount: R" + totalAmount);
            pw.println("Special Instructions: " + specialInstructions);
            if (assignedDriver != null) {
                pw.println("Assigned Driver: " + assignedDriver.getName());
            } else {
                pw.println("No driver available.");
            }
            System.out.println("Invoice written to file.");
        } catch (IOException e) {
            System.out.println("Error writing invoice file: " + e.getMessage());
        }
    }

    // Method to display the order details
    public void displayInfo() {
        System.out.println("Order Information:");
        System.out.println("Order Number: " + orderNumber);
        System.out.println("Customer: " + (customer != null ? customer.getName() : "N/A"));
        System.out.println("Restaurant: " + restaurant.getName());
        System.out.println("Items:");
        items.forEach((item, quantity) -> System.out.println("  " + item + " x " + quantity + " @ R" + restaurant.getMenu().get(item)));
        System.out.println("Total Amount: R" + totalAmount);
        System.out.println("Special Instructions: " + specialInstructions);
        if (assignedDriver != null) {
            System.out.println("Assigned Driver: " + assignedDriver.getName());
        } else {
            System.out.println("No driver available.");
        }
    }
}
