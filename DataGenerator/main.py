import os

from generators.students import generate_student_df
from generators.staffs import generate_staff_df

if __name__ == "__main__":
    PATH = "../datasets"
    print("Generating files...")

    generate_student_df(PATH)
    generate_staff_df(PATH)

    print("Finished")
