
package app;

import problemdomain.*;
import java.io.*;
import java.util.*;

/**
 * AppDriver class for managing appliances in a store.
 * This class provides functionalities to load appliances from a file,
 * purchase an appliance, search appliances by brand, display appliances by type,
 * display random appliances, and save the appliance list to a file.
 */
public class AppDriver {
    // List to store all appliances
    private static List<Appliance> appliances = new ArrayList<>();

    public static void main(String[] args) {
        // Load appliances from file
        loadAppliances();
        Scanner scanner = new Scanner(System.in);
        int choice;

        // Main menu loop
        do {
            System.out.println("Welcome to Modern Appliances!");
            System.out.println("How May We Assist You?");
            System.out.println("1 – Check out appliance");
            System.out.println("2 – Find appliances by brand");
            System.out.println("3 – Display appliances by type");
            System.out.println("4 – Produce random appliance list");
            System.out.println("5 – Save & exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            // Handle user choice
            switch (choice) {
                case 1:
                    purchaseAppliance(scanner);
                    break;
                case 2:
                    searchByBrand(scanner);
                    break;
                case 3:
                    displayAppliancesByType(scanner);
                    break;
                case 4:
                    displayRandomAppliances(scanner);
                    break;
                case 5:
                    saveAppliances();
                    System.out.println("Changes saved. Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    /**
     * Load appliances from a file.
     */
    private static void loadAppliances() {
        try (BufferedReader br = new BufferedReader(new FileReader("res/appliances.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                long itemNumber = Long.parseLong(data[0]);
                String brand = data[1];
                double quantity = Double.parseDouble(data[2]);
                double wattage = Double.parseDouble(data[3]);
                String color = data[4];
                double price = Double.parseDouble(data[5]);

                // Determine type by the first digit of the item number
                int type = Integer.parseInt(data[0].substring(0, 1));

                // Create appliance object based on type
                switch (type) {
                    case 1: // Refrigerator
                        double numberOfDoors = Double.parseDouble(data[6]);
                        double height = Double.parseDouble(data[7]);
                        double width = Double.parseDouble(data[8]);
                        appliances.add(new Refrigerator(itemNumber, brand, quantity, wattage, color, price, numberOfDoors, height, width));
                        break;
                    case 2: // Vacuum
                        String grade = data[6];
                        double batteryVoltage = Double.parseDouble(data[7]);
                        appliances.add(new Vacuum(itemNumber, brand, quantity, wattage, color, price, grade, batteryVoltage));
                        break;
                    case 3: // Microwave
                        float capacity = Float.parseFloat(data[6]);
                        String roomType = data[7];
                        appliances.add(new Microwave(itemNumber, brand, quantity, wattage, color, price, capacity, roomType));
                        break;
                    case 4: // Dishwasher
                    case 5: // Dishwasher
                        String feature = data[6];
                        String soundRating = data[7];
                        appliances.add(new Dishwasher(itemNumber, brand, quantity, wattage, color, price, feature, soundRating));
                        break;
                    default:
                        System.out.println("Unknown appliance type for item number: " + itemNumber);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading appliances: " + e.getMessage());
        }
    }

    /**
     * Purchase an appliance by reducing its quantity by 1.
     * @param scanner Scanner object for user input
     */
    private static void purchaseAppliance(Scanner scanner) {
        System.out.print("Enter the item number of the appliance to purchase: ");
        long itemNumber = scanner.nextLong();
        for (Appliance appliance : appliances) {
            if (appliance.getItemNumber() == itemNumber) {
                if (appliance.getQuantity() > 0) {
                    appliance.setQuantity(appliance.getQuantity() - 1);
                    System.out.println("Purchase successful.");
                    saveAppliances(); // Save changes after purchase
                } else {
                    System.out.println("The appliance is not available to be checked out.");
                }
                return;
            }
        }
        System.out.println("No appliances found with that item number.");
    }

    /**
     * Search appliances by brand.
     * @param scanner Scanner object for user input
     */
    private static void searchByBrand(Scanner scanner) {
        System.out.print("Enter the brand to search for: ");
        String brand = scanner.next();
        System.out.println("Matching Appliances");
        for (Appliance appliance : appliances) {
            if (appliance.getBrand().equalsIgnoreCase(brand)) {
                System.out.println(appliance);
            }
        }
    }

    /**
     * Display appliances by type.
     * @param scanner Scanner object for user input
     */
    private static void displayAppliancesByType(Scanner scanner) {
        System.out.println("Appliance Types");
        System.out.println("1 – Refrigerators");
        System.out.println("2 – Vacuums");
        System.out.println("3 – Microwaves");
        System.out.println("4 – Dishwashers");
        System.out.print("Enter type of appliance: ");
        int type = scanner.nextInt();

        switch (type) {
            case 1:
                System.out.print("Enter number of doors: 2 (double door), 3 (three doors) or 4 (four doors): ");
                int doors = scanner.nextInt();
                System.out.println("Matching Refrigerators");
                for (Appliance appliance : appliances) {
                    if (appliance instanceof Refrigerator && ((Refrigerator) appliance).getNumberOfDoors() == doors) {
                        System.out.println(appliance);
                    }
                }
                break;
            case 2:
                System.out.print("Enter battery voltage value. 18 V (low) or 24 V (high): ");
                int voltage = scanner.nextInt();
                System.out.println("Matching Vacuums");
                for (Appliance appliance : appliances) {
                    if (appliance instanceof Vacuum && ((Vacuum) appliance).getBatteryVoltage() == voltage) {
                        System.out.println(appliance);
                    }
                }
                break;
            case 3:
                System.out.print("Room where the microwave will be installed: K (kitchen) or W (work site): ");
                String roomType = scanner.next();
                System.out.println("Matching Microwaves");
                for (Appliance appliance : appliances) {
                    if (appliance instanceof Microwave && ((Microwave) appliance).getRoomType().equalsIgnoreCase(roomType)) {
                        System.out.println(appliance);
                    }
                }
                break;
            case 4:
                System.out.print("Enter the sound rating of the dishwasher: Qt (Quietest), Qr (Quieter), Qu (Quiet) or M (Moderate): ");
                String soundRating = scanner.next();
                System.out.println("Matching Dishwashers");
                for (Appliance appliance : appliances) {
                    if (appliance instanceof Dishwasher && ((Dishwasher) appliance).getSoundRating().equalsIgnoreCase(soundRating)) {
                        System.out.println(appliance);
                    }
                }
                break;
            default:
                System.out.println("Invalid type. Please try again.");
        }
    }

    /**
     * Display a random list of appliances.
     * @param scanner Scanner object for user input
     */
    private static void displayRandomAppliances(Scanner scanner) {
        System.out.print("Enter the number of random appliances to display: ");
        int count = scanner.nextInt();
        System.out.println("Random Appliances");
        Collections.shuffle(appliances);
        for (int i = 0; i < count && i < appliances.size(); i++) {
            System.out.println(appliances.get(i));
        }
    }

    /**
     * Save the list of appliances to a file.
     */
    private static void saveAppliances() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("res/appliances.txt"))) {
            for (Appliance appliance : appliances) {
                if (appliance instanceof Refrigerator) {
                    Refrigerator r = (Refrigerator) appliance;
                    bw.write(r.getItemNumber() + ";" + r.getBrand() + ";" + r.getQuantity() + ";" +
                            r.getWattage() + ";" + r.getColor() + ";" + r.getPrice() + ";" + r.getNumberOfDoors() + ";" +
                            r.getHeight() + ";" + r.getWidth());
                } else if (appliance instanceof Vacuum) {
                    Vacuum v = (Vacuum) appliance;
                    bw.write(v.getItemNumber() + ";" + v.getBrand() + ";" + v.getQuantity() + ";" +
                            v.getWattage() + ";" + v.getColor() + ";" + v.getPrice() + ";" + v.getGrade() + ";" +
                            v.getBatteryVoltage());
                } else if (appliance instanceof Microwave) {
                    Microwave m = (Microwave) appliance;
                    bw.write(m.getItemNumber() + ";" + m.getBrand() + ";" + m.getQuantity() + ";" +
                            m.getWattage() + ";" + m.getColor() + ";" + m.getPrice() + ";" + m.getCapacity() + ";" +
                            m.getRoomType());
                } else if (appliance instanceof Dishwasher) {
                    Dishwasher d = (Dishwasher) appliance;
                    bw.write(d.getItemNumber() + ";" + d.getBrand() + ";" + d.getQuantity() + ";" +
                            d.getWattage() + ";" + d.getColor() + ";" + d.getPrice() + ";" + d.getFeature() + ";" +
                            d.getSoundRating());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving appliances: " + e.getMessage());
        }
    }
}
