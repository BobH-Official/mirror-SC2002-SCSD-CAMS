package CAMS.Data;

public class ManagerEnquiryMS {
  private final String userID;

  public ManagerEnquiryMS(String userID) {
    this.userID = userID;
  }

  public void reply() {
    // 1. check whether the user is Staff or CM

    // 2. if is teacher ask for which camp to view
    //    if is CM, looking up the camp in the database

    // 3. print all enquiry of the camp

    // 4. get index from user input

    // 5. print the enquiry

    // 6. ask for reply msg

    // 7. update the data.

  }

  public void viewEnquiry() {  
    //check to see if its a CM or a staff
    if (Database.finduser() == StudentData){
      //if user is CM then continue with this branch
      if (StudentData.isCommitteeMember()){
        UserData committeeMember = Database.finduser(); 
        //since CM only can view enquiries from attendees for the camp they are in charge of
        //and they can only be CM for one camp, simply get all the enquiries from the camp they 
        //are incharge of       
        String camp = committeeMember.campCommittee();
        //get all the enquiries from the camp that CM is in and print them out
        System.out.println(STR. "These are all messages for the camp: \{ camp }" );
        List<RequestData<EnquiryStatus>> enquiries =
        Database.getCampRequestList(camp, Database.RequestType.ENQUIRY);

        for (int i = 0; i < enquiries.size(); i += 1) {
          System.out.print(STR. "    \{ i }. " );
          System.out.println(
            suggestions.get(i).toString().indent(4).stripLeading());
        }
      }
    }
    //if user is Staff then continue with this branch
    else if (Database.finduser() = StaffData){
      //since staff can manage multiple camp prompt the staff what camp they want to choose to see the enquiries
      System.out.println("\n## VIEW SUGGESTION ##\n");
      Console console = System.console();
      String camp = console.readLine(STR."""
        Which camp's enquiries do you want to view? (leave blank to print all the camp you manage)
        Enter camp name:\s""").strip();

      while (camp.isEmpty()) {
        Database.printCampsForStaff(userID);
        camp = console.readLine(STR."""
        Which camp's enquiries do you want to view? (leave blank to print all the camp you manage)
        Enter camp name:\s""").strip();

        //get the list of all enquiries from the choosen camp and then print them out
        System.out.println(STR. "These are all messages for the camp: \{ camp }" );
        List<RequestData<EnquiryStatus>> enquiries =
        Database.getCampRequestList(camp, Database.RequestType.ENQUIRY);

        for (int i = 0; i < enquiries.size(); i += 1) {
          System.out.print(STR. "    \{ i }. " );
          System.out.println(
            suggestions.get(i).toString().indent(4).stripLeading());
        }
      }
    }
    //return if user is not a cm or staff
    else{
      return;
    }
  }  
}
