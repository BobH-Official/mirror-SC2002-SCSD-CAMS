package CAMS.Data;

abstract class UserData {
    
    private  String name;
    private String email;
    private  String userID;
    private  String password;
    private  String faculty;

    void User(String userName, String userEmail, String faculty){
        this.name = userName;
        this.email = userEmail;
        this.faculty = faculty;
    
    }

    boolean isPasswordCorrect(String passwd){
        return this.password;
    }
    void setPassword(String password){
        this.password = password;
    }
    String getUserID(){
        return this.userID;
    }
    String getFaculty(){
        return this.faculty;
    }
    String getEmail(){
        return this.email;
    }
    String getName(){
        return this.name;
    }
    
    void printSelf() {
        System.out.println("STUDENT  : ");
        System.out.println("  ID     : " + this.userID);
        System.out.println("  PASSWD : " + this.password);
    }
    

    
}