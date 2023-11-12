package rs.raf;

import rs.raf.classes.ClassLecture;
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

        String impl = args.length>0 ? args[0] : "single";
        String className = impl.equals("reacuring") ? "rs.raf.Implementation2" : "rs.raf.Implementation1";


        try {
            Class.forName(className);
        } catch (Exception e) {

        }
        ClassSchedule classSchedule = ScheduleManager.getClassScheduler();
        Schedule schedule = null;

        List<Classroom> classrooms = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        Scanner scanner = new Scanner(System.in);
        int choice;
        String input;
        int capacity;
        String[] info = new String[10];
        int startTime;
        int newstartTime;
        int endTime;
        Date fromDate;
        Date toDate = null;
        Date newfromDate;
        Date newtoDate;
        int duration;
        boolean free;
        ArrayList<AddOns> addOns = new ArrayList<>();
        List<Classroom> classroomsToPrint = new ArrayList<>();


        do {
            // Display menu
            System.out.println("Menu:");
            System.out.println("1. Init Schedule");
            System.out.println("2. Add classroom");
            System.out.println("3. Add class");
            System.out.println("4. Remove class");
            System.out.println("5. Reschedule class");
            System.out.println("6. Open find classroom menu");
            System.out.println("7. Open find term menu");
            System.out.println("8. Open export/import menu");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            // Get user choice
            choice = scanner.nextInt();

            try {
            // Process user choice
            switch (choice) {
                case 1:
                    System.out.println("Please enter info to init schedule, example: name,start-date,to-date,start-hours,to-hours  date format = \"dd.MM.yyyy\"");
                    input = scanner.next();
                    Arrays.fill(info, null);
                    info = input.split(",");

                    try {
                        fromDate = dateFormat.parse(info[1]);
                        toDate = dateFormat.parse(info[2]);
                        startTime = Integer.parseInt(info[3]);
                        endTime = Integer.parseInt(info[4]);

                        schedule = classSchedule.initializeSchedule(info[0], classrooms, fromDate, toDate, startTime, endTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 2:
                    System.out.println("Please enter info to add classroom, example: name,4(capacity),computers,whiteborad,projector,pen");
                    input = scanner.next();
                    Arrays.fill(info, null);
                    info = input.split(",");

                    List<String> addonsString = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(info, 2, info.length)));

                    addOns = new ArrayList<>();

                    for (String s : addonsString) {
                        if (s.equals("projector"))
                            addOns.add(AddOns.PROJECTOR);
                        else if (s.equals("whiteboard")) {
                            addOns.add(AddOns.WHITEBOARD);
                        } else if (s.equals("computers")) {
                            addOns.add(AddOns.COMPUTERS);
                        } else if (s.equals("pen")) {
                            addOns.add(AddOns.PEN);
                        }
                    }
                    capacity = Integer.parseInt(info[1]);

                    try {
                        classSchedule.createClassroom(classrooms, info[0], capacity, addOns.toArray(new AddOns[0]));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Please enter info to add class, example: start-time,duration,classroom-name,lecture-name,profesor,fromDate,toDate (same date for single class)");
                    input = scanner.next();
                    Arrays.fill(info, null);
                    info = input.split(",");


                    try {
                        fromDate = dateFormat.parse(info[5]);
                        toDate = dateFormat.parse(info[6]);
                        startTime = Integer.parseInt(info[0]);
                        duration = Integer.parseInt(info[1]);
//                        System.out.println(info[6]);
//                        System.out.println(toDate);
                            classSchedule.createClass(schedule, startTime, duration, info[2], info[3], info[4], fromDate, toDate);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    System.out.println("Please enter info to remove class, example: from-date,to-date,start-time,classroom-name,leacture-name");
                    input = scanner.next();
                    Arrays.fill(info, null);
                    info = input.split(",");

                    try {
                        fromDate = dateFormat.parse(info[0]);
                        toDate = dateFormat.parse(info[1]);
                        startTime = Integer.parseInt(info[2]);
                        classSchedule.removeClass(schedule, fromDate, toDate, startTime, info[3], info[4]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    System.out.println("Please enter info to reschedule a class, example: old-from-date,old-to-date,old-start-time,");
                    System.out.println("old-classroom-name,lecture-name,new-from-date,new-to-date,new-start-time,new-classroom-name");
                    input = scanner.next();
                    Arrays.fill(info, null);
                    info = input.split(",");

                    try {
                        fromDate = dateFormat.parse(info[0]);
                        toDate = dateFormat.parse(info[1]);
                        startTime = Integer.parseInt(info[2]);
                        newfromDate = dateFormat.parse(info[5]);
                        newtoDate = dateFormat.parse(info[6]);
                        newstartTime = Integer.parseInt(info[7]);
                        classSchedule.rescheduleClass(schedule, fromDate, toDate, startTime, info[3], info[4], newfromDate, newtoDate, newstartTime, info[8]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
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
                                System.out.println("Please enter capacity and addons, example: 5,projector,whiteboard,pen,computers");
                                input = scanner.next();
                                Arrays.fill(info, null);
                                info = input.split(",");
                                capacity = Integer.parseInt(info[0]);
                                addonsString = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(info, 1, info.length)));

                                for (String s : addonsString) {
                                    if (s.equals("projector"))
                                        addOns.add(AddOns.PROJECTOR);
                                    else if (s.equals("whiteboard")) {
                                        addOns.add(AddOns.WHITEBOARD);
                                    } else if (s.equals("computers")) {
                                        addOns.add(AddOns.COMPUTERS);
                                    } else if (s.equals("pen")) {
                                        addOns.add(AddOns.PEN);
                                    }
                                }

                                try {
                                    classroomsToPrint = classSchedule.findClassrooms(schedule, capacity, addOns.toArray(new AddOns[0]));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                for(Classroom c : classroomsToPrint){
                                    System.out.println(c.toString());
                                }
                                classroomsToPrint.clear();
                                addOns.clear();
                                break;
                            case 2:
                                System.out.println("Please enter addons: example:projector,whiteboard,pen,computers");
                                input = scanner.next();
                                Arrays.fill(info, null);
                                info = input.split(",");

                                addonsString = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(info, 0, info.length)));

                                addOns = new ArrayList<>();
                                for (String s : addonsString) {
                                    if (s.equals("projector"))
                                        addOns.add(AddOns.PROJECTOR);
                                    else if (s.equals("whiteboard")) {
                                        addOns.add(AddOns.WHITEBOARD);
                                    } else if (s.equals("computers")) {
                                        addOns.add(AddOns.COMPUTERS);
                                    } else if (s.equals("pen")) {
                                        addOns.add(AddOns.PEN);
                                    }
                                }

                                try {
                                    classroomsToPrint = classSchedule.findClassrooms(schedule, addOns.toArray(new AddOns[0]));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                for(Classroom c : classroomsToPrint){
                                    System.out.println(c.toString());
                                }
                                classroomsToPrint.clear();
                                addOns.clear();
                                // Add code for Suboption 2
                                break;
                            case 3:
                                System.out.println("Please enter capacity, example:5");
                                input = scanner.next();
                                Arrays.fill(info, null);
                                info = input.split(",");
                                capacity = Integer.parseInt(info[0]);

                                try {
                                    classroomsToPrint = classSchedule.findClassrooms(schedule, capacity);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                for(Classroom c : classroomsToPrint){
                                    System.out.println(c.toString());
                                }
                                classroomsToPrint.clear();

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
                case 7:
                    // Submenu for Option 5
                    int subChoice2;
                    do {
                        // Display submenu
                        System.out.println("Find term menu:");
                        System.out.println("1. Find terms with date and duration");
                        System.out.println("2. Find terms with date, duration, free/taken and classroom");
                        System.out.println("3. Find terms with date, duration, free/taken, capacity and addons");
                        System.out.println("4. Find terms with date, duration, free/taken amd capacity");
                        System.out.println("5. Find terms with date, duration, free/taken and addons");
                        System.out.println("6. Find all terms by date for professor");
                        System.out.println("7. Find all terms by class name");
                        System.out.println("8. Find lecture info for date and time");
                        System.out.println("9. To return to the main menu");
                        System.out.print("Enter your choice: ");

                        // Get user choice for submenu
                        subChoice2 = scanner.nextInt();

                        // Process user choice for submenu
                        switch (subChoice2) {
                            case 1:
                                System.out.println("Please enter the date and duration, example: 01.10.2023,2");
                                input = scanner.next();
                                Arrays.fill(info, null);
                                info = input.split(",");

                                try {
                                    fromDate = dateFormat.parse(info[0]);
                                    duration = Integer.parseInt(info[1]);

                                    List<Term> termList = classSchedule.findTerms(schedule,fromDate,duration);
                                    Collections.sort(termList, Comparator
                                            .comparing(Term::getDate)
                                            .thenComparing(Term::getStartTime));

                                    System.out.println("The free terms for " +fromDate+ " with duration of "+ duration+ " are:");
                                    for(Term term : termList){
                                        System.out.println("Classroom: " + term.getClassroom().getName() +
                                                ", StartTime: " + term.getStartTime() +
                                                ", Date: " + term.getDate());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                break;
                            case 2:
                                System.out.println("Please enter the date, duration, free/notfree, classroom: example: 01.10.2023,2,free,classroom1");
                                input = scanner.next();
                                Arrays.fill(info, null);
                                info = input.split(",");

                                try {
                                    fromDate = dateFormat.parse(info[0]);
                                    duration = Integer.parseInt(info[1]);
                                    if(info[2].equals("free"))
                                        free = true;
                                    else
                                        free = false;
                                    List<Term> termList = classSchedule.findTerms(schedule,fromDate,duration,free,info[3]);
                                    Collections.sort(termList, Comparator
                                            .comparing(Term::getDate)
                                            .thenComparing(Term::getStartTime));

                                    if(free)
                                        System.out.println("The free terms for classroom "+ info[3] +" on " +fromDate+ " with duration of "+ duration+ " are:");
                                    else
                                        System.out.println("The taken terms for classroom "+ info[3] +" on " +fromDate+ " with duration of "+ duration+ " are:");

                                    for(Term term : termList){
                                        System.out.println("Classroom: " + term.getClassroom().getName() +
                                                ", StartTime: " + term.getStartTime() +
                                                ", Date: " + term.getDate());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                break;
                            case 3:
                                System.out.println("Please enter the date, duration, free/notfree, capacity and addons: ");
                                System.out.println("example: 01.10.2023,2,free,5,computers");
                                input = scanner.next();
                                Arrays.fill(info, null);
                                info = input.split(",");

                                try {
                                    fromDate = dateFormat.parse(info[0]);
                                    duration = Integer.parseInt(info[1]);
                                    if(info[2].equals("free"))
                                        free = true;
                                    else
                                        free = false;
                                    capacity = Integer.parseInt(info[3]);

                                    addonsString = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(info, 4, info.length)));

                                    addOns = new ArrayList<>();
                                    for (String s : addonsString) {
                                        if (s.equals("projector"))
                                            addOns.add(AddOns.PROJECTOR);
                                        else if (s.equals("whiteboard")) {
                                            addOns.add(AddOns.WHITEBOARD);
                                        } else if (s.equals("computers")) {
                                            addOns.add(AddOns.COMPUTERS);
                                        } else if (s.equals("pen")) {
                                            addOns.add(AddOns.PEN);
                                        }
                                    }


                                    List<Term> termList = classSchedule.findTerms(schedule,fromDate,duration,free,capacity,addOns.toArray(new AddOns[0]));
                                    Collections.sort(termList, Comparator
                                            .comparing(Term::getDate)
                                            .thenComparing(Term::getStartTime));

                                    if(free)
                                        System.out.println("The free terms with capacity "+ capacity +" and "+ addonsString +" on " +fromDate);
                                    else
                                        System.out.println("The taken terms with capacity "+ capacity +" and "+ addonsString +" on " +fromDate);
                                    for(Term term : termList){
                                        System.out.println("Classroom: " + term.getClassroom().getName() +
                                                ", StartTime: " + term.getStartTime() +
                                                ", Date: " + term.getDate());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                System.out.println("Please enter the date, duration, free/notfree and capacity,");
                                System.out.println("example: 01.10.2023,2,free,5");

                                input = scanner.next();
                                Arrays.fill(info, null);
                                info = input.split(",");

                                try {
                                    fromDate = dateFormat.parse(info[0]);
                                    duration = Integer.parseInt(info[1]);
                                    if(info[2].equals("free"))
                                        free = true;
                                    else
                                        free = false;
                                    capacity = Integer.parseInt(info[3]);

                                    List<Term> termList = classSchedule.findTerms(schedule,fromDate,duration,free,capacity);
                                    Collections.sort(termList, Comparator
                                            .comparing(Term::getDate)
                                            .thenComparing(Term::getStartTime));

                                    if(free)
                                        System.out.println("The free terms for " +fromDate+ " with duration of "+ duration+ " and capacity of "+capacity+":");
                                    else
                                        System.out.println("The taken terms for " +fromDate+ " with duration of "+ duration+ " and capacity of "+capacity+":");
                                    for(Term term : termList){
                                        System.out.println("Classroom: " + term.getClassroom().getName() +
                                                ", StartTime: " + term.getStartTime() +
                                                ", Date: " + term.getDate());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 5:
                                System.out.println("Please enter the date, duration, free/notfree and addons,");
                                System.out.println("example: 01.10.2023,2,free,computer,projector");

                                input = scanner.next();
                                Arrays.fill(info, null);
                                info = input.split(",");

                                try {
                                    fromDate = dateFormat.parse(info[0]);
                                    duration = Integer.parseInt(info[1]);
                                    if(info[2].equals("free"))
                                        free = true;
                                    else
                                        free = false;

                                    addonsString = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(info, 3, info.length)));

                                    addOns = new ArrayList<>();
                                    for (String s : addonsString) {
                                        if (s.equals("projector"))
                                            addOns.add(AddOns.PROJECTOR);
                                        else if (s.equals("whiteboard")) {
                                            addOns.add(AddOns.WHITEBOARD);
                                        } else if (s.equals("computers")) {
                                            addOns.add(AddOns.COMPUTERS);
                                        } else if (s.equals("pen")) {
                                            addOns.add(AddOns.PEN);
                                        }
                                    }

                                    List<Term> termList = classSchedule.findTerms(schedule,fromDate,duration,free,addOns.toArray(new AddOns[0]));
                                    Collections.sort(termList, Comparator
                                            .comparing(Term::getDate)
                                            .thenComparing(Term::getStartTime));

                                    if(free)
                                        System.out.println("The free terms with duration "+ duration +" and "+ addonsString +" on " +fromDate);
                                    else
                                        System.out.println("The taken terms with duration "+ duration +" and "+ addonsString +" on " +fromDate);
                                    for(Term term : termList){
                                        System.out.println("Classroom: " + term.getClassroom().getName() +
                                                ", StartTime: " + term.getStartTime() +
                                                ", Date: " + term.getDate());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 6:
                                System.out.println("Please enter the date,to-date, profesore name and free/notfree");
                                System.out.println("example: 01.10.2023,01.12.2023,Surla,free");
                                input = scanner.next();
                                Arrays.fill(info, null);
                                info = input.split(",");

                                try {
                                    fromDate = dateFormat.parse(info[0]);
                                    toDate = dateFormat.parse(info[1]);

                                    if(info[2].equals("free"))
                                        free = true;
                                    else
                                        free = false;


                                    List<Term> termList = classSchedule.findTerms(schedule,fromDate,toDate,info[2],free);
                                    Collections.sort(termList, Comparator.comparingInt(Term::getStartTime));
                                    if(free)
                                        System.out.println("Term when profesor: " + info[2] + " doesn't have classes");
                                    else
                                        System.out.println("Terms when profesor: " + info[2] + " has classes");
                                    for(Term term : termList){
                                        System.out.println("Classroom: " + term.getClassroom().getName() +
                                                ", StartTime: " + term.getStartTime() +
                                                ", Date: " + term.getDate());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 7:
                                System.out.println("Please enter the class name");
                                System.out.println("example: sk");
                                input = scanner.next();
                                Arrays.fill(info, null);
                                info = input.split(",");

                                try {

                                    List<Term> termList = classSchedule.findTerms(schedule,info[0]);

                                    Collections.sort(termList, Comparator
                                            .comparing(Term::getDate)
                                            .thenComparing(Term::getStartTime)
                                    );

                                    System.out.println("Terms when you can listen to " + info[0]+ ":");

                                    for(Term term : termList){
                                        System.out.println("Classroom: " + term.getClassroom().getName() +
                                                ", StartTime: " + term.getStartTime() +
                                                ", Date: " + term.getDate());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 8:
                                System.out.println("Please enter the date and time, example: 01.10.2023,13");
                                input = scanner.next();
                                Arrays.fill(info, null);
                                info = input.split(",");
                                try {
                                    fromDate = dateFormat.parse(info[0]);
                                    startTime = Integer.parseInt(info[1]);

                                    ClassLecture classLecture = classSchedule.findClassForTerm(schedule,fromDate,startTime);
                                    System.out.println("Class name: "+ classLecture.getClassName());
                                    System.out.println("Profesor: "+ classLecture.getProfessor());


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 9:
                                System.out.println("Returning to the main menu");
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter a valid option.");
                                break;
                        }

                    }while (subChoice2 != 9);
                    break;
                case 8:
                    int subChoice3;
                    do {
                        // Display submenu
                        System.out.println("Import/Export menu:");
                        System.out.println("1. Export CSV");
                        System.out.println("2. Import CSV");
                        System.out.println("3. Export JSON");
                        System.out.println("4. Import JSON");
                        System.out.println("5. Export PDF");
                        System.out.println("6. Import PDF");
                        System.out.println("7. Back to main menu");
                        System.out.print("Enter your choice: ");

                        // Get user choice for submenu
                        subChoice3 = scanner.nextInt();

                        // Process user choice for submenu
                        switch (subChoice3) {
                            case 1:
                                System.out.println("Please enter file path to export CSV to:");
                                input = scanner.next();
                                try {
                                    classSchedule.exportCSV(schedule,input);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2:
                                System.out.println("Please enter file path for CSV import:");
                                input = scanner.next();
                                try {
                                    classSchedule.importCSV(schedule,input);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3:
                                System.out.println("Please enter file path to export JSON to:");
                                input = scanner.next();
                                try {
                                    classSchedule.exportJSON(schedule,input);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                System.out.println("Please enter file path for JSON import:");
                                input = scanner.next();
                                try {
                                    classSchedule.importJSON(schedule,input);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 5:
                                System.out.println("Please enter file path to export PDF to:");
                                input = scanner.next();
                                try {
                                    classSchedule.exportPDF(schedule,input);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 6:
                                System.out.println("Please enter file path for PDF import:");
                                input = scanner.next();
                                try {
                                    classSchedule.importPDF(schedule,input);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 7:
                                System.out.println("Returning to the main menu");
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter a valid option.");
                                break;
                        }

                    } while (subChoice3 != 7);
                    break;
                case 9:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception or log it as needed
            }

        } while (choice != 9);

        // Close the scanner
        scanner.close();
    }
}
