import os

from generators.students import generate_student_df
from generators.staffs import generate_staff_df

if __name__ == "__main__":
    PATH = "C:/Users/o/Desktop/대학 2학년/SC2002/Assignment/SC2002-SCSD-CAMS/SC2002Project/src/files"
    print("Generating files...")

    generate_student_df(PATH)
    generate_staff_df(PATH)

    print("Finished")