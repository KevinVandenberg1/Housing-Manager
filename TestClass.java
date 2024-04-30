// Name: Kevin Vandenberg
// Assignment: Lab 3 Employee Hierarchy
// Class : CS 145
// Notice: ChatGPT, W3Schools and GeeksForGeeks have been used as reference

import java.util.ArrayList;
import java.util.Scanner;
public class TestClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueMenu = true;
        ArrayList<Housing> houses = new ArrayList<Housing>();

        do {String input = displayAndFixMenu(scanner);
            
            switch (input) {
                case "Q": {continueMenu = false;break;}
                case "A": {
                    Housing house = addHouse(scanner); // Creates the house object
                    if (house != null) {
                        houses.add(house); // Adds the house to the arrayList
                    } else {
                        System.out.println("Object not created due to null reference");
                    } break;
                }
                case "V": { Housing type = null; Housing house = getSpecificHouse(scanner, houses, type);
                    if (house != null) {
                        System.out.println(house.toString());
                    }
                    break;
                }
                case "I": { Rentable type = null; Housing house = getSpecificHouse(scanner, houses, type);
                    if (house != null && (house instanceof Rentable)) {
                        ((Rentable) house).addInfraction();
                    } else if (!(house instanceof Rentable)) {
                        System.out.println("Specified house is not rentable");
                    }
                    break;
                }
                case "R": { Rentable type = null; Housing house = getSpecificHouse(scanner, houses, type);
                    if (house != null && (house instanceof Rentable)) {
                        ((Rentable) house).removeInfraction();
                    } else if (!(house instanceof Rentable)) {
                        System.out.println("Specified house is not rentable");
                    }
                    break;
                }
                default: {System.out.println("Input not recognized, displaying menu again:");}
            }
        } while (continueMenu);
        scanner.close(); // Closes the scanner
    }
    public static <T> Housing getSpecificHouse(Scanner scanner, ArrayList<Housing> houses, T type) {
        if (houses.get(0) == null) {
            System.out.println("Currently, there are no houses is in the housing manager.");
            System.out.println("Add one first before trying to access their data");
            return null;
        } else {
            System.out.println("What is the address of the house you are looking for?");
            System.out.println("List of addresses:");

            for (Housing i : houses) { // Prints out all the addresses of houses currently stored in the system
                if (type instanceof Rentable) {
                    if (i instanceof Rentable) {
                        System.out.println("    " + i.getAddress());
                    }
                } else {
                    System.out.println("    " + i.getAddress());
                }
            }

            String address = scanner.nextLine().trim();

            for (Housing i : houses) {

                if (i.getAddress().equals(address)) {
                    return i;
                }
            }
        }
        System.out.println("Address not found");
        return null; // Default case incase nothing else returned anything
    }

    public static void menu() {
        System.out.printf("MENU: %n%s %n%s %n%s %n%s %n%s %n",
        "   \"A\": Add a new house to the housing manager",
        "   \"V\": View one of the houses stored in the housing manager",
        "   \"I\": Add an infraction to one of the residents",
        "   \"R\": Remove an infraction from one of the residents",
        "   \"Q\": Quit the housing manager"
        );
    }

    public static String displayAndFixMenu(Scanner scanner) {
        menu();
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase().substring(0,1);
            if (input.equals("A") || input.equals("V") || input.equals("I") || 
                input.equals("R") || input.equals("R") || input.equals("Q")) {
                return input;
            } else if (input.equals("M")) {
                menu();
            } else {
                System.out.println("Input a valid menu item. If you need to see the menu again, type \"MENU\"");
            }
        }
    }

    public static Housing addHouse(Scanner scanner) {
        Housing house = createHousing(scanner);
        boolean typeOfHouse = getHousingType(scanner);
        if (typeOfHouse) { // if true then its rentable
            house = rentableHouse(scanner, house);
        } else { // if false then its buyable
            house = buyableHouse(scanner, house);
        }
        return house;
    }
    
    public static Housing createHousing(Scanner scanner) {
        System.out.print("Input the address:");
        String address = scanner.nextLine(); // Gets the address from the user

        System.out.print("Input the number of rooms:"); 
        int rooms = scanner.nextInt(); // Gets the number of rooms from user

        System.out.print("Input the number of bedrooms:");
        double bedrooms = scanner.nextDouble(); // Gets the number of bedrooms from the user

        System.out.print("Input the number of bathrooms:");
        double bathrooms = scanner.nextDouble(); scanner.nextLine(); // Gets the number of bathrooms from the user

        System.out.print("Input the number of people who can live here:");
        int capacity = scanner.nextInt(); scanner.nextLine(); // gets the capacity of the house from the user
        System.out.print("Is there a studio?");
        boolean studio = readBoolean(scanner); // Gets whether or not there is a studio from the user

        Housing baseObject = new Housing(address, rooms, bedrooms, bathrooms, studio, capacity);
        

        return baseObject;
    }

    public static boolean getHousingType(Scanner scanner) {
        System.out.print("Is this house rentable or buyable?");
        while (true) {
            try {
                String input = scanner.nextLine().trim().toUpperCase();
                if (input.startsWith("R")) {
                    return true;
                } else if (input.startsWith("B")) {
                    return false;
                } else {
                    throw new ArithmeticException("Input not recognized");
                }

            } catch (Exception e) {
                System.out.println("Input not recognized, type either \"Rentable\" or \"Buyable\"");
            } 
        }
    }


    // Method for getting the information for a place that is rentable.
    public static Rentable rentableHouse(Scanner scanner, Housing tempObject) {
        String tenant = null;
        double credit = 1;

        System.out.print("Landlord Name: ");
        String lName = scanner.nextLine();

        System.out.print("Base rent Price: ");
        double rent = scanner.nextDouble(); scanner.nextLine();

        System.out.print("Is it for rent? ");
        Boolean status = readBoolean(scanner);
        if (!status) {
            System.out.print("What is the tenants name? ");
            tenant = scanner.nextLine();

            System.out.print("What is their credit? (1 being good, 5 being bad) ");
            credit = scanner.nextDouble(); scanner.nextLine();
        }
        Rentable house = new Rentable(lName, rent, credit, tenant, status, 
                tempObject.getAddress(), tempObject.getRooms(), tempObject.getBedrooms(), 
                tempObject.getBathrooms(), tempObject.hasStudio(), tempObject.getCapacity());
        return house;
    }

    // Method for getting the information for a house that is buyable
    public static Buyable buyableHouse(Scanner scanner, Housing tempObject) { 
        String owner = null;
        System.out.print("Input the price of the location:");
        double price = scanner.nextDouble();
        System.out.print("Is it for sale? ");
        Boolean saleStatus = readBoolean(scanner);
        if (!saleStatus) {
            System.out.print("Who is the owner? ");
            owner = scanner.nextLine();
        }
        Buyable house = new Buyable(tempObject.getAddress(), price, saleStatus, owner, 
            tempObject.getRooms(), tempObject.getBedrooms(), tempObject.getBathrooms(), tempObject.hasStudio(), tempObject.getCapacity());
        return house;
    }

    // Makes sure the users input either true or false, or yes or no to return a boolean. If not, it lets them try again.
    public static boolean readBoolean(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().toLowerCase();
                if (input.startsWith("t") || input.startsWith("y")) {
                    return true;
                } else if (input.startsWith("f") || input.startsWith("n")) {
                    return false;
                } else {
                    System.out.println("Type T or F (true or false)");
                }
            } catch (Exception e) {
                System.out.println("Type T or F (true or false)");
            }
        }
    }

}
