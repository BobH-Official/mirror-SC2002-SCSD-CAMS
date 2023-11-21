package CAMS.Data;

//importing the relevant database java classes

import CAMS.Data.Utils.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Database {

  // Static maps
  private static final HashMap<String, UserData> userMap = new HashMap<>();
  private static final HashMap<String, EnquiryData> enquiryMap =
    new HashMap<>();
  private static final HashMap<String, SuggestionData> suggestionMap =
    new HashMap<>();
  private static final HashMap<String, CampData> campMap = new HashMap<>();

  public static void initialize(CLIArgs args) {
    List<List<List<String>>> csvList;
    if (!args.db().isBlank()) {
      csvList = CSVHelper.readDB(args.db());
    } else {
      csvList = new ArrayList<>();
      if (args.staff().isBlank()) {
        csvList.add(new ArrayList<>());
      } else {
        csvList.add(CSVHelper.readCSV(args.staff()));
      }
      if (args.student().isBlank()) {
        csvList.add(new ArrayList<>());
      } else {
        csvList.add(CSVHelper.readCSV(args.student()));
      }
      if (args.camp().isBlank()) {
        csvList.add(new ArrayList<>());
      } else {
        csvList.add(CSVHelper.readCSV(args.camp()));
      }
      if (args.enquiry().isBlank()) {
        csvList.add(new ArrayList<>());
      } else {
        csvList.add(CSVHelper.readCSV(args.enquiry()));
      }
      if (args.suggestion().isBlank()) {
        csvList.add(new ArrayList<>());
      } else {
        csvList.add(CSVHelper.readCSV(args.suggestion()));
      }
    }

    for (int i = 0; i < csvList.size(); i += 1) {
    }
  }

  private static void staffFromCsv(List<List<String>> list) {
    list.removeFirst();
    for (List<String> li : list) {
      List<String> info = new ArrayList<>();
//      String userID, password, name, email, faculty, camps;
      for (String i : li) {
        info.addLast(i);
      }
      if (li.size() < 6) {
        info.addLast("");
      }
      Database.createStaff(info.get(2), info.get(3), info.get(4), info.get(1),
        List.of(info.get(5).split("&")));
    }
  }

  static void createStaff(String name, String email, String faculty,
                          String password, List<String> camps) {
    // create a new staff data object and then add it into the user hashmap
    StaffData staffData = new StaffData(name, email, faculty, password, camps);
    userMap.put(staffData.id(), staffData);
  }

  private static void studentFromCsv(List<List<String>> list) {
    list.removeFirst();
    for (List<String> li : list) {
      List<String> info = new ArrayList<>();
//      String userID, password, name, email, faculty;
      for (String i : li) {
        info.addLast(i);
      }
      if (li.size() < 6) {
        info.addLast("");
      }
      Database.createStudent(info.get(2), info.get(3), info.get(4),
        info.get(1));
    }

  }

  static void createStudent(String name, String email, String faculty,
                            String password) {
    // create a new student data object and then add it into the user hashmap
    StudentData studentData = new StudentData(name, email, faculty, password);
    userMap.put(studentData.id(), studentData);
  }

  private static void campFromCsv(List<List<String>> list) {

  }

  private static void enquiryFromCsv(List<List<String>> list) {

  }

  private static void suggestionFromCsv(List<List<String>> list) {

  }

  public static void initialize() {
    Database.createStudent(/*name*/"Example Student 1", /*email*/
      "STUDENT001@e.ntu.edu.sg", "SCSE", "password");
    Database.createStudent(/*name*/"Example Student 2", /*email*/
      "STUDENT002@e.ntu.edu.sg", "EEE", "password");
    Database.createStudent(/*name*/"Example Student 3", /*email*/
      "STUDENT003@e.ntu.edu.sg", "SCSE", "password");
    Database.createStudent(/*name*/"Example Student 4", /*email*/
      "STUDENT004@e.ntu.edu.sg", "EEE", "password");
    Database.createStudent(/*name*/"Example Student 5", /*email*/
      "STUDENT005@e.ntu.edu.sg", "SCSE", "password");

    Database.createStaff("Example Staff 1", /*email*/
      "STAFF001@e.ntu.edu.sg", "SCSE", "password",
      List.of(new String[]{"Camp1", "Camp2", "Camp5"}));
    Database.createStaff("Example Staff 2", /*email*/
      "STAFF002@e.ntu.edu.sg", "SCSE", "password",
      List.of(new String[]{"Camp7", "Camp8"}));
    Database.createStaff("Example Staff 3", /*email*/
      "STAFF003@e.ntu.edu.sg", "EEE", "password",
      List.of(new String[]{"Camp3", "Camp4", "Camp6", "Camp9"}));

    Database.createCamp("Camp1", "STAFF001", "NTU", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);

    Database.createCamp("Camp2", "STAFF001", "SCSE", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp3", "STAFF003", "EEE", false, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp4", "STAFF003", "NTU", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp5", "STAFF001", "NTU", false, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp6", "STAFF003", "EEE", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp7", "STAFF002", "SCSE", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp8", "STAFF002", "SCSE", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
    Database.createCamp("Camp9", "STAFF003", "EEE", true, "Camp one one one",
      DateHelper.get(2023, 12, 11), DateHelper.get(2023, 12, 13),
      DateHelper.get(2023, 12, 10), "Block N4", 10, 40);
  }

  static boolean createCamp(String name, String staff, String userGroup,
                            boolean visibility, String description,
                            Date startDate, Date endDate,
                            Date registrationClosingDate, String location,
                            int campTotalSlots, int committeeSlots) {

    if (campMap.containsKey(name)) {
      System.out.println("Camp Already Exists");
      return false;
    }
    // Create a camp data object and then add it into the camp hashmap
    CampData campData =
      new CampData(name, staff, userGroup, visibility, description, startDate,
        endDate, registrationClosingDate, location, campTotalSlots,
        committeeSlots);
    campMap.put(campData.name(), campData);
    return true;
  }

  static String createEnquiry(String sender, String message, String camp) {
    // Create an enquiry data object and then add it into the enquiry hashmap
    EnquiryData enquiryData = new EnquiryData(sender, message, camp);
    enquiryMap.put(enquiryData.id(), enquiryData);
    return enquiryData.id();
  }

  static String createSuggestion(String sender, String message, String camp) {
    // Create a suggestion data object and then add it into the enquiry hashmap
    SuggestionData suggestionData = new SuggestionData(sender, message, camp);
    suggestionMap.put(suggestionData.id(), suggestionData);
    return suggestionData.id();
  }

  public static void storeDB(String path) {
    try (final FileOutputStream fos = new FileOutputStream(path)) {
      ZipOutputStream zipOut = new ZipOutputStream(fos);
//      ZipEntry zipEntry = ;
      zipOut.setLevel(9);
      zipOut.putNextEntry(new ZipEntry("mimetype"));
      zipOut.write("application/camsdb+zip".getBytes(StandardCharsets.UTF_8));
      zipOut.putNextEntry(new ZipEntry("META-INF/container.xml"));
      zipOut.write("""
        <?xml version="1.0" encoding="UTF-8"?>
        <container version="1.0" xmlns="ntu:sc2002:cams:xmlns:container">
          <files>
            <file full-path="camp.csv" media-type="text/csv" />
            <file full-path="enquiry.csv" media-type="text/csv" />
            <file full-path="staff.csv" media-type="text/csv" />
            <file full-path="student.csv" media-type="text/csv" />
            <file full-path="suggestion.csv" media-type="text/csv" />
          </files>
        </container>
        """.strip().getBytes(StandardCharsets.UTF_8));
      String[] filenames =
        {"staff.csv", "student.csv", "camp.csv", "enquiry.csv",
          "suggestion.csv"};
      for (String file : filenames) {
        zipOut.putNextEntry(new ZipEntry(file));
        switch (file) {
          case "staff.csv" -> {
            zipOut.write(
              Database.getCsvStaff().strip().getBytes(StandardCharsets.UTF_8));
          }
          case "student.csv" -> {
            zipOut.write(Database.getCsvStudent().strip()
              .getBytes(StandardCharsets.UTF_8));
          }
          case "camp.csv" -> {
            zipOut.write(
              Database.getCsvCamp().strip().getBytes(StandardCharsets.UTF_8));
          }
          case "enquiry.csv" -> {
            zipOut.write(Database.getCsvEnquiries().strip()
              .getBytes(StandardCharsets.UTF_8));
          }
          case "suggestion.csv" -> {
            zipOut.write(Database.getCsvSuggestions().strip()
              .getBytes(StandardCharsets.UTF_8));
          }
          default -> {
          }
        }
      }
      zipOut.close();
    } catch (IOException e) {
      System.err.println(e.getLocalizedMessage());
    }
  }

  static String getCsvStaff() {
    String str = "userID,password,name,email,faculty,camps\n";
    for (UserData user : userMap.values()) {
      if (Objects.requireNonNull(user) instanceof StaffData staff) {
        str = str.concat(staff.toCsv());
      }
    }
    return str.strip();
  }

  static String getCsvStudent() {
    String str = "userID,password,name,email,faculty\n";
    for (UserData user : userMap.values()) {
      if (Objects.requireNonNull(user) instanceof StudentData student) {
        str = str.concat(student.toCsv());
      }
    }
    return str.strip();
  }

  static String getCsvCamp() {
    String str = "name,staff,visibility,start,end,closingRegistration," +
      "faculty,location,totalSlots,committeeSlots,description,attendee," +
      "committeeMembers,blacklist,enquiries,suggestions\n";
    for (CampData camp : campMap.values()) {
      str = str.concat(camp.toCsv());
    }
    return str.strip();
  }

  static String getCsvEnquiries() {
    String str = "id,sender,camp,status,message,reply\n";
    for (EnquiryData enquiries : enquiryMap.values()) {
      str = str.concat(enquiries.toCsv());
    }
    return str.strip();
  }

  static String getCsvSuggestions() {
    String str = "id,sender,camp,status,message\n";
    for (SuggestionData suggestion : suggestionMap.values()) {
      str = str.concat(suggestion.toCsv());
    }
    return str.strip();
  }

  static String getCsvUsers() {
    String str = "userID,password,name,email,faculty\n";
    for (UserData user : userMap.values()) {
      str = str.concat(user.toCsv());
    }
    return str.strip();
  }

  static void deleteEnquiry(String id) {
    enquiryMap.remove(id);
  }

  static void deleteSuggestion(String id) {
    if (!suggestionMap.containsKey(id)) {
      suggestionMap.remove(id);
    }
  }

  static void deleteCamp(String id) {
    if (campMap.containsKey(id)) {
      if (campMap.get(id).hasMembers()) {
        campMap.remove(id);
      } else {
        PrintHelper.printError(STR. "Error: can not delete the camp \{ id }." +
          "There are members in the camp.");
      }

    }
  }

  static List<String> getCampsList() {
    // Implementation for getting the list of camp names
    // Return the list of camp names
    List<String> list = new ArrayList<>(campMap.keySet());
    Collections.sort(list);
    return list;
  }

  static List<CampData> getCampListForStudent(String id) {
    String faculty = Objects.requireNonNull(Database.findStudent(id)).faculty();

    List<CampData> camps = getCampListByFaculty(faculty);
    camps.addAll(getCampListByFaculty("NTU"));
    camps.sort(Comparator.comparing(CampData::name));

    return camps;
  }

  static StudentData findStudent(String id) {
    if (!userMap.containsKey(id)) {
      return null;
    }

    UserData user = userMap.get(id);

    if (user instanceof StudentData) {
      return (StudentData) user;
    }

    return null;
  }

  // Static methods to get camp data
  static List<CampData> getCampListByFaculty(String faculty) {
    List<CampData> camps = new ArrayList<>();
    List<CampData> camplist = new ArrayList<>(campMap.values());
    camplist.sort(Comparator.comparing(CampData::name));
    for (CampData camp : camplist) {
      if (Objects.equals(camp.information().faculty(), faculty)) {
        camps.add(camp);
      }
    }
    return camps;
  }

  static boolean canManage(String user, String camp) {
    Pair<CAMS.Data.UserType, UserData> data = findUser(user);
    if (data != null) {
      switch (data.first()) {

        case STUDENT -> {
          return ((StudentData) data.second()).isCommitteeMember() &&
            ((StudentData) data.second()).campCommittee().equals(camp);
        }
        case STAFF -> {
          return ((StaffData) data.second()).getCampsUnderManagement()
            .contains(camp);
        }
        default -> {
          return false;
        }
      }
    }
    return false;
  }

  static CAMS.Data.Utils.Pair<CAMS.Data.UserType, UserData> findUser(
    String id) {
    if (!userMap.containsKey(id)) {
      // Return the corresponding UserData object
      System.err.println(STR. "User \{ id } not found in database." );
      return null;
    }

    UserData user = userMap.get(id);

    if (user instanceof StudentData) {
      return new CAMS.Data.Utils.Pair<>(CAMS.Data.UserType.STUDENT, user);
    }

    if (user instanceof StaffData) {
      return new CAMS.Data.Utils.Pair<>(CAMS.Data.UserType.STAFF, user);
    }

    return new CAMS.Data.Utils.Pair<>(CAMS.Data.UserType.NOT_FOUND, user);
  }

  static void printCampsByFaculty(String faculty) {
    // Implementation for printing camps by faculty

    System.out.println("Camps in Faculty:");
    List<CampData> camps = new ArrayList<>(campMap.values());
    camps.sort(Comparator.comparing(CampData::name));
    for (CampData camp : camps) {
      if (Objects.equals(camp.information().faculty(), faculty)) {
        System.out.println("    Camp: " + camp.name());
      }
    }
  }

  static void printCampsForStudent(String id) {
    // Implementation for printing camps by faculty
    System.out.println("Camps:");
    String faculty = Objects.requireNonNull(Database.findStudent(id)).faculty();

    int index = 1;
    System.out.print(STR."    ");

    List<CampData> camps = new ArrayList<>(campMap.values());
    camps.sort(Comparator.comparing(CampData::name));
    for (CampData camp : camps) {
      if ((Objects.equals(camp.information().faculty(), "NTU") ||
        Objects.equals(camp.information().faculty(), faculty)) &&
        camp.isVisible()) {
        if (index % 5 == 0) {
          System.out.print(STR."\n    ");
        }
        System.out.print(STR. "\{ camp.name() } " );
        index += 1;
      }
    }
    System.out.println();
  }

  static <T extends RequestStatus> List<RequestData<T>> getCampRequestList(
    String camp, RequestType type) {
    List<String> requestIds;
    List<RequestData<T>> requests = new ArrayList<>();
    switch (type) {
      case ENQUIRY -> {
        requestIds = Database.findCamp(camp).enquiries().requests();
        for (String id : requestIds) {
          EnquiryData data = Database.findEnquiry(id);
          requests.add((RequestData<T>) data);
        }
      }
      case SUGGESTION -> {
        requestIds = Database.findCamp(camp).suggestions().requests();
        for (String id : requestIds) {
          SuggestionData data = Database.findSuggestion(id);
          requests.add((RequestData<T>) data);
        }
      }
    }
    return requests;
  }

  static CampData findCamp(String id) {
    // Check if the enquiryMap contains the specified id
    // Return the corresponding EnquiryData object
    // Enquiry not found, return null
    if (!campMap.containsKey(id)) {
      System.err.println("Error: Camp not found. ID: " + id);
    }
    return campMap.getOrDefault(id, null);
  }

  static EnquiryData findEnquiry(String id) {
    // Check if the enquiryMap contains the specified id
    // Return the corresponding EnquiryData object
    // Enquiry not found, return null
    if (!enquiryMap.containsKey(id)) {
      System.err.println("Error: Enquiry not found. ID: " + id);
    }
    return enquiryMap.getOrDefault(id, null);
  }

  static SuggestionData findSuggestion(String id) {
    // Check if the suggestionMap contains the specified id
    // Return the corresponding SuggestionData object
    // Suggestion not found, return null
    if (!suggestionMap.containsKey(id)) {
      System.err.println("Error: Suggestion not found. ID: " + id);
    }
    return suggestionMap.getOrDefault(id, null);
  }


  // Static methods to find data from the hashmaps

  static void printCampsForStaff(String id) {
    System.out.println("Camps:");
    List<String> camps =
      Objects.requireNonNull(Database.findStaff(id)).getCampsUnderManagement();
    Collections.sort(camps);
    int index = 1;
    System.out.print(STR."    ");
    for (String camp : camps) {
      if (index % 5 == 0) {
        System.out.print(STR."\n    ");
      }
      System.out.print(STR. "\{ camp } " );
      index += 1;
    }
  }

  static StaffData findStaff(String userID) {
    // Check if the userMap contains the specified userID
    if (userMap.containsKey(userID) &&
      userMap.get(userID) instanceof StaffData) {
      // Return the corresponding StaffData object
      return (StaffData) userMap.get(userID);
    } else {
      // Staff not found or not of the correct type, return null
      return null;
    }
  }

  static String facultyOf(String id) {
    if (userMap.containsKey(id)) {
      UserData data = Database.userMap.get(id);
      return data.faculty();
    }
    if (campMap.containsKey(id)) {
      CampData data = Database.campMap.get(id);
      return data.information().faculty();
    }

    System.err.println("No such object in database: " + id);
    return null;
  }

  static String campOf(String id) {
    if (enquiryMap.containsKey(id)) {
      EnquiryData data = Database.enquiryMap.get(id);
      return data.camp();
    }
    if (suggestionMap.containsKey(id)) {
      SuggestionData data = Database.suggestionMap.get(id);
      return data.camp();
    }
    System.err.println("No such object in database: " + id);
    return null;
  }

  static boolean isNotInDatabase(String id) {
    if (userMap.containsKey(id)) {
      return false;
    }
    if (campMap.containsKey(id)) {
      return false;
    }
    if (enquiryMap.containsKey(id)) {
      return false;
    }
    if (suggestionMap.containsKey(id)) {
      return false;
    }
    System.err.println("No such object in database: " + id);
    return true;
  }

  enum RequestType {
    ENQUIRY, SUGGESTION,

  }
}
