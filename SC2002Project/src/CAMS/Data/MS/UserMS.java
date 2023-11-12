package CAMS.Data.MS;

import CAMS.Data.Database;

public abstract  class UserMS {

  // TODO: should be private, this is only for demonstration
  protected final String userID;

  public UserMS(String id) {
    userID = id;

  }


  public void changePassword( String password) {

  }
}
