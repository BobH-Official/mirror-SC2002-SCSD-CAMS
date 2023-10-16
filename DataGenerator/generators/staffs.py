import os
import pandas as pd

def generate_staff_df(PATH):

    data_path = os.path.join(PATH, "initial_input", "staff_list.xlsx")
    
    # Set header to None to avoid using the first row as column headers
    data = pd.read_excel(data_path, header=None)

    # Assuming that the first row of your Excel contains column names, 
    # we manually assign these column names for clarity
    columns = ["Name", "Email", "Faculty"]
    data.columns = columns

    staff_dict = {
        "userID": [],
        "password": [],
        "name": [],
        "email": [],
        "faculty": []
    }

    for i in range(len(data)):

        row = data.iloc[i]
        name = row["Name"]
        email = row["Email"]
        userID = email.split("@")[0]
        faculty = row["Faculty"]

        staff_dict["userID"].append(userID)
        staff_dict["password"].append("password")
        staff_dict["name"].append(name)
        staff_dict["email"].append(email)
        staff_dict["faculty"].append(faculty)

    # Ensure the columns of the dataframe are ordered correctly
    column_order = ["userID", "password", "name", "email", "faculty"]
    staff_df = pd.DataFrame(staff_dict)[column_order]
    
    staff_df.to_csv(os.path.join(PATH, "stafflist.csv"), index=False, header=False)
