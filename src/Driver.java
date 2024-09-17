// Class representing a Driver
public class Driver {
    // Attributes of a driver
    private String name;
    private String location;
    private int currentLoad;

    // Constructor to initialize a Driver object
    public Driver(String name, String location, int currentLoad) {
        this.name = name;
        this.location = location;
        this.currentLoad = currentLoad;
    }

    // Getters for Driver attributes
    public String getName() { return name; }
    public String getLocation() { return location; }
    public int getCurrentLoad() { return currentLoad; }

    // Method to update the current load of the driver
    public void updateLoad(int load) {
        this.currentLoad = load;
    }

    // Method to display driver information
    public void displayInfo() {
        System.out.println("Driver Information:");
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Current Load: " + currentLoad);
    }
}
