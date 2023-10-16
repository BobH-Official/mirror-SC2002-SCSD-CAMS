import os
import pandas as pd

def generate_staff_df(PATH):

    data_path = os.path.join(PATH, "initial_input", "staff_list.xlsx")
    
    # Skip the first row to avoid the original column names from the Excel file
    data = pd.read_excel(data_path, skiprows=1, header=None)

    # Manually assign the column names for clarity
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
    
    # Save CSV without headers, since we are skipping the original headers from Excel
    staff_df.to_csv(os.path.join(PATH, "stafflist.csv"), index=False, header=True)
