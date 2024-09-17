// Class representing a Customer
public class Customer {
    // Attributes of a customer
    private String name;
    private String contactNumber;
    private String address;
    private String location;
    private String email;

    // Constructor to initialize a Customer object
    public Customer(String name, String contactNumber, String address, String location, String email) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
        this.location = location;
        this.email = email;
    }

    // Getters for Customer attributes
    public String getName() { return name; }
    public String getContactNumber() { return contactNumber; }
    public String getAddress() { return address; }
    public String getLocation() { return location; }
    public String getEmail() { return email; }

    // Method to display customer information
    public void displayInfo() {
        System.out.println("Customer Information:");
        System.out.println("Name: " + name);
        System.out.println("Contact Number: " + contactNumber);
        System.out.println("Address: " + address);
        System.out.println("Location: " + location);
        System.out.println("Email: " + email);
    }
}
