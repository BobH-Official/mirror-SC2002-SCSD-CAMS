package CAMS.Data;

public class ManagerEnquiryMS {
  private final String userID;

  public ManagerEnquiryMS(String userID) {
    this.userID = userID;
  }

  public void replyEnquiry() {
    // 1. check whether the user is Staff or CM 
    Console console = System.console();

    if (Database.finduser() == StudentData && Database.finduser().isCommitteeMember){
       //    if is CM, get the camp they are committee member in
    
        UserData committeeMember = Database.finduser(); 
        //since CM only can view enquiries from attendees for the camp they are in charge of
        //and they can only be CM for one camp, simply get all the enquiries from the camp they 
        //are incharge of       
        String camp = committeeMember.campCommittee();
    
    }
      // 2. if is staff ask for which camp to view
    else if (Database.finduser() == StaffData){
      //since staff can manage multiple camp prompt the staff what camp they want to choose to see the enquiries
      System.out.println("\n## VIEW ENQUIRY ##\n");
      String camp = console.readLine(STR."""
        Which camp's enquiries do you want to view? (leave blank to print all the camp you manage)
        Enter camp name:\s""").strip();

      while (camp.isEmpty()) {
        Database.printCampsForStaff(userID);
        camp = console.readLine(STR."""
        Which camp's enquiries do you want to view? (leave blank to print all the camp you manage)
        Enter camp name:\s""").strip();
      }
      break; 
    }
    else {
      return; 
    }

  

    //get the list of all enquiries from the choosen camp and then print them out
        System.out.println(STR. "These are all messages for the camp: \{ camp }" );
        List<RequestData<EnquiryStatus>> enquiries =
        Database.getCampRequestList(camp, Database.RequestType.ENQUIRY);

        for (int i = 0; i < enquiries.size(); i += 1) {
          System.out.print(STR. "    \{ i }. " );
          System.out.println(
            enquiries.get(i).toString().indent(4).stripLeading());
        }
      }

    // 4. get index from user input

    Console console = System.console();
    String enquiryID =
      console.readLine("Enter the ID of your enquiry to reply to: ").strip();
    if (enquiryID.isEmpty()) {
      System.err.println("Enquiry ID cannot be empty.");
      return;
    }
    //get the enquiry using the enquiry id inputed by user

    EnquiryData enquiry = Database.findEnquiry(enquiryID);
    if (enquiry == null || !enquiry.sender().equals(this.userID)) {
      System.err.println("Enquiry not found or access denied.");
      return;
    }

    // 5. print the enquiry
    System.out.println(enquiry.message());

    // 6. ask for reply msg
    System.out.print("Enter your reply message: ");
    String replyMessage = System.console().readLine();

    // 7. update the data.
    enquiry.reply(replyMessage); 

    //if manager is a cm then increment their point by 1
    if (committeeMember){
      committeeMember.increasePointsForReplyingEnquiry(); 
    }
  }

  public void viewEnquiry() {  
    //check to see if its a CM or a staff
    if (Database.finduser() == StudentData && StudentData.isCommitteeMember(){
      //if user is CM then continue with this branch
        UserData committeeMember = Database.finduser(); 
        //since CM only can view enquiries from attendees for the camp they are in charge of
        //and they can only be CM for one camp, simply get all the enquiries from the camp they 
        //are incharge of       
        String camp = committeeMember.campCommittee();
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
      }
    }
    //return if user is not a cm or staff
    else{
      return;
    }
    //get the list of all enquiries from the choosen camp and then print them out
      System.out.println(STR. "These are all enquiries for the camp: \{ camp }" );
      List<RequestData<EnquiryStatus>> enquiries =
      Database.getCampRequestList(camp, Database.RequestType.ENQUIRY);

      for (int i = 0; i < enquiries.size(); i += 1) {
        System.out.print(STR. "    \{ i }. " );
        System.out.println(
          enquiries.get(i).toString().indent(4).stripLeading());
      }
  }  
}
