package rs.raf;

import rs.raf.classes.Classroom;
import rs.raf.classes.Schedule;
import rs.raf.classes.Term;
import rs.raf.enums.AddOns;
import rs.raf.schedule_management.ClassSchedule;
import rs.raf.schedule_management.ScheduleManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String className = "rs.raf.Implementation1";

        try {
            Class.forName(className);
        }
        catch (Exception e){

        }
        ClassSchedule classSchedule = ScheduleManager.getClassScheduler();

        List<Classroom> classrooms = new ArrayList<>();
        //TODO promeniti da ako je null prodje
        classrooms.add(classSchedule.createClassroom(classrooms,"Ucionica",2,AddOns.PEN));

        System.out.println(classrooms.get(0).getName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Schedule schedule = null;
        try {
            Date startDate = dateFormat.parse("01.10.2023");
            Date endDate = dateFormat.parse("01.12.2023");

            // Assuming classSchedule is an instance of the class where initializeSchedule is defined
            schedule = classSchedule.initializeSchedule("kita", classrooms, startDate, endDate, 12, 20);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the parsing exception appropriately
        }
        if(schedule==null){
            System.out.println("pukli smo ko picke");
        }

        try {
            Date startDate = dateFormat.parse("01.10.2023");
//            classSchedule.createClass(schedule, 13, 3, "Ucionica", "SK", "Surla", startDate, startDate);
            // OVO PROLAZI
//            classSchedule.createClass(schedule, 13, 1, "Ucionica", "SK", "Surla", startDate, null);

            List<Term> termList = classSchedule.findTerms(schedule,startDate,2,true,"Ucionica");

            for(Term term : termList){
                System.out.println(term.getClassroom());
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
//        try {
//            List<Classroom> classroomList = classSchedule.findClassrooms(schedule,AddOns.COMPUTERS);
//            //                                classSchedule.findClassrooms(capacity,addOns.toArray(new AddOns[0]));
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        System.out.println(classroomList.get(0).getName());
        System.out.println("KURCINA");

    }

    public static void main1(String[] args) {

        String className = "rs.raf.Implementation1";
        try {
            Class.forName(className);
        }
        catch (Exception e){

        }
        ClassSchedule classSchedule = ScheduleManager.getClassScheduler();


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
//                                classSchedule.findClassrooms(schedule,capacity,addOns.toArray(new AddOns[0]));
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
