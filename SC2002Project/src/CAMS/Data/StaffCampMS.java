package CAMS.Data;

import java.util.Scanner;
import CAMS.Data.Utils.DateHelper;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class StaffCampMS {
  private final String userID;

  public StaffCampMS(String userID) {
    this.userID = userID;
  }

  public void createCamp() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter camp name: ");
    String name = scanner.nextLine();

    if (Database.findCamp(name) != null) {
        System.out.println("Camp with the same name already exists. Please choose a different name.");
        return;
    }

    System.out.print("Enter staff ID: ");
    String staffID = scanner.nextLine();

    System.out.print("Enter user group: ");
    String userGroup = scanner.nextLine();

    System.out.print("Enter visibility (true/false): ");
    boolean visibility = scanner.nextBoolean();
    scanner.nextLine(); // Consume the newline character

    System.out.print("Enter camp description: ");
    String description = scanner.nextLine();

    Date startDate = null, endDate = null, registrationClosingDate = null;
    String[] dateLabels = {"start", "end", "registration closing"};

    for (String label : dateLabels) {
        System.out.printf("Enter %s date (YYYY-MM-DD): ", label);
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        switch (label) {
            case "start" -> startDate = DateHelper.get(year, month, day);
            case "end" -> endDate = DateHelper.get(year, month, day);
            case "registration closing" -> registrationClosingDate = DateHelper.get(year, month, day);
        }
    }

    System.out.print("Enter location: ");
    String location = scanner.nextLine();

    System.out.print("Enter total slots and committee slots: ");
    int totalSlots = scanner.nextInt();
    int committeeSlots = scanner.nextInt();

    boolean created = Database.createCamp(name, staffID, userGroup, visibility, description,
            startDate, endDate, registrationClosingDate, location, totalSlots, committeeSlots);

    if (created) {
        System.out.println("Camp created successfully!");
    } else {
        System.out.println("Failed to create the camp. Please check the input and try again.");
    }
}

  public void viewCamp() {
    // 1. ask for filter, default alphabetically
    // (the get camps funcs in Database returns alphabetically)
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter filter : ");
    String filter = scanner.nextLine();

    // 2. get the camp list from database, and sort the list
    // for student,
    // call Database.getCampListForStudent(String userID)
    // for sorting, use switch to match Comparator, default to use name.
    // camps.sort(Comparator.comparing(CampData::name));
    List<CampData> camps;
    if ("student".equalsIgnoreCase(filter)) {
        camps = Database.getCampListForStudent(userID);
    } else {
        camps = Database.getCampsList();
    }

    Collections.sort(camps, Comparator.comparing(CampData::name));

    // 3. print the names
    System.out.println("Available Camps:");
    for (int i = 0; i < camps.size(); i++) {
        System.out.println((i + 1) + ". " + camps.get(i).name());
    }
    

    // 4. ask for the index
    System.out.println("Enter the index of the camp to view (e/q to exit): ");
    String userInput=scanner.nextLine();

    // 5. if is out of index, or input e/q, to exit the function
    if(userInput=="e"||userInput=="q"){
      return;
    }

    int index = Integer.parseInt(userInput) - 1;

    // 6. print the camp details
    if (index >= 0 && index < camps.size()) {
      CampData selectedCamp = camps.get(index);
      System.out.println("Camp Details:");
      System.out.println(selectedCamp);
  } else {
      System.out.println("Invalid index. Please try again.");
  }

    // 7. return to step 3, unless step 5. is true
    viewCamp();
  }


public void editCamp() {
  // 1. ask for camp name
  Scanner scanner = new Scanner(System.in);
  System.out.print("Enter camp name to edit: ");
  String campName = scanner.nextLine();

  // 2. check if the camp exists
  CampData campToEdit = Database.findCamp(campName);
  if (campToEdit == null) {
      System.out.println("Camp not found. Please enter a valid camp name.");
      return;
  }

  // 3. ask for which field to edit
  System.out.println("Select field to edit:");
  System.out.println("1. Description");
  System.out.println("2. Location");

  // 4. get the new input
  int choice;
  do {
      System.out.print("Enter your choice (1-2): ");
      while (!scanner.hasNextInt()) {
          System.out.println("Invalid input. Please enter a number.");
          scanner.next(); // Consume the invalid input
      }
      choice = scanner.nextInt();
      scanner.nextLine(); // Consume the newline character
  } while (choice < 1 || choice > 2);

  // 5. update the camp data using pattern match switch
  String newValue;
  switch (choice) {
      case 1 -> {
          System.out.print("Enter new camp description: ");
          newValue = scanner.nextLine();
          campToEdit.setDescription(newValue);
      }
      case 2 -> {
          System.out.print("Enter new location: ");
          newValue = scanner.nextLine();
          campToEdit.setLocation(newValue);
      }
      default -> System.out.println("Invalid choice. Please enter 1 or 2.");
  }

  // 6. print success message
  System.out.println("Camp data updated successfully!");
}


// Helper method to get integer input
private int getIntInput(String prompt) {
  Scanner scanner = new Scanner(System.in);
  int input;
  while (true) {
      try {
          System.out.print(prompt);
          input = Integer.parseInt(scanner.nextLine());
          break;
      } catch (NumberFormatException e) {
          System.out.println("Invalid input. Please enter a valid number.");
      }
  }
  return input;
}


public void deleteCamp() {
  // 1. ask for camp name
  Scanner scanner = new Scanner(System.in);
  System.out.print("Enter camp name to delete: ");
  String campName = scanner.nextLine();

  // 2. check if the camp exists
  CampData campToDelete = Database.findCamp(campName);
  if (campToDelete == null) {
      System.out.println("Camp not found. Please enter a valid camp name.");
      return;
  }

  // 3. delete the camp
  Database.deleteCamp(campName);
  System.out.println("Camp deleted successfully!");
}
}




