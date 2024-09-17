import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize the DeliverySystem and load drivers from the file
        DeliverySystem deliverySystem = new DeliverySystem();
        deliverySystem.loadDrivers("drivers.txt");

        // Create a restaurant and add it to the delivery system
        Restaurant r1 = new Restaurant("Pizza Palace", "Cape Town", "012 386 1894");
        r1.addMenuItem("Margherita Pizza", 8.99);
        r1.addMenuItem("Pepperoni Pizza", 9.99);
        r1.addMenuItem("BBQ Chicken Pizza", 10.99);
        r1.addMenuItem("Veggie Pizza", 7.99);
        deliverySystem.addRestaurant(r1);

        // Initialize scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Main loop to allow continuous ordering until the user decides to exit
        while (true) {
            // Display the menu to the user
            System.out.println("Welcome to FoodQuick Delivery System!");
            System.out.println("Here is the menu:");
            r1.displayMenuWithNumbers();

            // Create a new order; customer will be set later
            Order order = new Order(null, r1);

            // Allow user to select items from the menu
            System.out.println("Please select items from the menu by entering the corresponding number.");
            int itemNumber;
            int quantity;
            while (true) {
                // Get item number from the user
                System.out.print("Item number (or 0 to finish): ");
                itemNumber = Integer.parseInt(scanner.nextLine().trim());
                if (itemNumber == 0) {
                    break; // Exit the item selection loop
                }
                // Get the item name corresponding to the item number
                String itemName = r1.getItemByNumber(itemNumber);
                if (itemName == null) {
                    System.out.println("Invalid item number. Try again.");
                    continue; // Ask for item number again if invalid
                }
                // Get the quantity for the selected item
                System.out.print("Quantity: ");
                quantity = Integer.parseInt(scanner.nextLine().trim());
                // Add item to the order
                order.addItem(itemName, quantity);
            }

            // Capture customer details
            System.out.println("\nEnter Customer Details:");
            System.out.print("Name: ");
            String customerName = scanner.nextLine();
            System.out.print("Contact Number: ");
            String customerContactNumber = scanner.nextLine();
            System.out.print("Address: ");
            String customerAddress = scanner.nextLine();
            System.out.print("Location: ");
            String customerLocation = scanner.nextLine();
            System.out.print("Email: ");
            String customerEmail = scanner.nextLine();

            // Check if there are any drivers available in the customer's location
            Driver driver = deliverySystem.assignDriver(customerLocation);
            if (driver == null) {
                System.out.println("Sorry! Our drivers are too far away from you to be able to deliver to your location.");
                continue; // Skip to the next iteration of the loop to create a new order
            }

            // Create Customer object and add to DeliverySystem
            Customer customer = new Customer(customerName, customerContactNumber, customerAddress, customerLocation, customerEmail);
            deliverySystem.addCustomer(customer);
            order.setCustomer(customer); // Set the customer for the order
            order.assignDriver(driver); // Assign the driver to the order

            // Prompt for special instructions
            System.out.print("Any special instructions? (or 'none'): ");
            String specialInstructions = scanner.nextLine();
            if (!specialInstructions.equalsIgnoreCase("none")) {
                order.setSpecialInstructions(specialInstructions);
            }

            // Display the order summary
            System.out.println("\nOrder Summary:");
            order.displayInfo();

            // Confirm the order
            System.out.print("Confirm order? (yes/no): ");
            String confirmation = scanner.nextLine();
            if (!confirmation.equalsIgnoreCase("yes")) {
                System.out.println("Order cancelled.");
                continue; // Go back to the beginning to create a new order
            }

            // Generate invoice if the order is confirmed
            order.generateInvoice();
            System.out.println("Order confirmed and invoice generated.");

            // Check if the user wants to create another order
            System.out.print("Do you want to create another order? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                break; // Exit the main loop
            }
        }

        // Final thank you message
        System.out.println("Thank you for using the FoodQuick Delivery System!");
    }
}
