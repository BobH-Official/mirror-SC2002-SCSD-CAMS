import os
import pandas as pd

def generate_student_df(PATH):

    data_path = os.path.join(PATH, "initial_input", "student_list.xlsx")
    
    # Set header to None to avoid using the first row as column headers
    data = pd.read_excel(data_path, header=None)

    # Assuming that the first row of your Excel contains column names, 
    # we manually assign these column names for clarity
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

    student_df = pd.DataFrame(student_dict)
    student_df.to_csv(os.path.join(
        PATH, "studentlist.csv"), index=False, header=False)
