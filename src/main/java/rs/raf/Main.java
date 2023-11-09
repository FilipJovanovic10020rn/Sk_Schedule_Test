package rs.raf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;
        String input;
        int capacity;

        do {
            // Display menu
            System.out.println("Menu:");
            System.out.println("1. Init Schedule");
            System.out.println("2. Add class");
            System.out.println("3. Remove class");
            System.out.println("4. Reschedule class");
            System.out.println("5. Open find classroom menu");
            System.out.println("6. Open find term menu");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            // Get user choice
            choice = scanner.nextInt();

            // Process user choice
            switch (choice) {
                case 1:
                    System.out.println("You selected Option 1");
                    // Add code for Option 1
                    break;
                case 2:
                    System.out.println("You selected Option 2");
                    // Add code for Option 2
                    break;
                case 3:
                    System.out.println("You selected Option 3");
                    // Add code for Option 3
                    break;
                case 4:
                    System.out.println("You selected Option 3");
                    // Add code for Option 3
                    break;
                case 5:
                    // Submenu for Option 5

                    int subChoice1;
                    do {
                        // Display submenu
                        System.out.println("Find classroom menu:");
                        System.out.println("1. Find classrom with capacity and addons");
                        System.out.println("2. Find classrom with addons");
                        System.out.println("3. Find classrom with capacity");
                        System.out.println("4. Back to main menu");
                        System.out.print("Enter your choice: ");

                        // Get user choice for submenu
                        subChoice1 = scanner.nextInt();

                        // Process user choice for submenu
                        switch (subChoice1) {
                            case 1:
                                System.out.println("Please enter capacity and addon, (example: 5,projector,whiteboard");
                                input = scanner.next();
                                String[] parts = input.split(",");
                                capacity = Integer.parseInt(parts[0]);
                                List<String> addonsString = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(parts, 1, parts.length)));

                                ArrayList<AddOns> addOns = new ArrayList<>();

                                for(String s: addonsString){
                                    if(s.equals("projector"))
                                        addOns.add(AddOns.PROJECTOR);
                                    else if (s.equals("whiteboard")) {
                                        addOns.add(AddOns.WHITEBOARD);
                                    }
                                    else if (s.equals("computers")) {
                                        addOns.add(AddOns.COMPUTERS);
                                    }
                                    else if (s.equals("pen")) {
                                        addOns.add(AddOns.PEN);
                                    }
                                }

                                //OVDE MOZES SE POZIVA METODA PROSLEDJUJE SE cpacity i addons
                                findClassrooms(capacity,addOns.toArray(new AddOns[0]));
                                // Add code for Suboption 1
                                break;
                            case 2:
                                System.out.println("You selected Suboption 2");
                                // Add code for Suboption 2
                                break;
                            case 3:
                                System.out.println("You selected Suboption 2");
                                // Add code for Suboption 2
                                break;
                            case 4:
                                System.out.println("Returning to the main menu");
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter a valid option.");
                                break;
                        }

                    } while (subChoice1 != 4);
                    break;
                case 6:
                    // Submenu for Option 5
                    int subChoice2;
                    do {
                        // Display submenu
                        System.out.println("Find term menu:");
                        System.out.println("1. Suboption 1");
                        System.out.println("2. Suboption 2");
                        System.out.println("3. Back to main menu");
                        System.out.print("Enter your choice: ");

                        // Get user choice for submenu
                        subChoice2 = scanner.nextInt();

                        // Process user choice for submenu
                        switch (subChoice2) {
                            case 1:
                                System.out.println("You selected Suboption 1");
                                // Add code for Suboption 1
                                break;
                            case 2:
                                System.out.println("You selected Suboption 2");
                                // Add code for Suboption 2
                                break;
                            case 3:
                                System.out.println("Returning to the main menu");
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter a valid option.");
                                break;
                        }

                    } while (subChoice2 != 3);

                    break;
                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 7);

        // Close the scanner
        scanner.close();
    }
}
