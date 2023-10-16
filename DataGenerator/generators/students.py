import os
import pandas as pd

def generate_student_df(PATH):

    data_path = os.path.join(PATH, "initial_input", "student_list.xlsx")
    
    # Skip the first row to avoid the original column names from the Excel file
    data = pd.read_excel(data_path, skiprows=1, header=None)

    # Manually assign the column names for clarity
    columns = ["Name", "Email", "Faculty"]
    data.columns = columns

    student_dict = {
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

        student_dict["userID"].append(userID)
        student_dict["password"].append("password")
        student_dict["name"].append(name)
        student_dict["email"].append(email)
        student_dict["faculty"].append(faculty)

    # Ensure the columns of the dataframe are ordered and named correctly
    column_order = ["userID", "password", "name", "email", "faculty"]
    student_df = pd.DataFrame(student_dict, columns=column_order)
    
    student_df.to_csv(os.path.join(PATH, "studentlist.csv"), index=False, header=True)
