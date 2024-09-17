import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Class representing the Delivery System
public class DeliverySystem {
    // Lists to store customers, restaurants, drivers, and orders
    private List<Customer> customers;
    private List<Restaurant> restaurants;
    private List<Driver> drivers;
    private List<Order> orders;

    // Constructor to initialize the DeliverySystem
    public DeliverySystem() {
        customers = new ArrayList<>();
        restaurants = new ArrayList<>();
        drivers = new ArrayList<>();
        orders = new ArrayList<>();
    }

    // Method to load drivers from a file
    public void loadDrivers(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String location = parts[1].trim();
                    int load = Integer.parseInt(parts[2].trim());
                    drivers.add(new Driver(name, location, load));
                } else {
                    System.out.println("Incorrect driver data format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading drivers file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing driver load: " + e.getMessage());
        }
    }

    // Method to add a customer to the system
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Method to add a restaurant to the system
    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    // Method to assign a driver based on location
    public Driver assignDriver(String location) {
        Driver assignedDriver = null;
        for (Driver driver : drivers) {
            if (driver.getLocation().equalsIgnoreCase(location)) {
                if (assignedDriver == null || driver.getCurrentLoad() < assignedDriver.getCurrentLoad()) {
                    assignedDriver = driver;
                }
            }
        }
        return assignedDriver;
    }
}
