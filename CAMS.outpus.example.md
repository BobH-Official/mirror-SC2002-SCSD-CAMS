## How should we handle error:

1. errors should be print out as soon as it happens, such no `Exception` should be thrown
2. how to infer the caller whether the actions is successful or not.
    1. `return null` if it is a find/get function
    2. `return false` if it is a create/edit function

## The way the `StudentOperator` creates/edit an enquiry.

In the text below, all the functions are named by the class name, not the instance name.

Indent means into the function, or explain what if errors happens.

1. `main` function start and user login. `[print: @1-@8]`
2. `main` functions calls `StudentOperator.doOperetion()`
    1. inside `StudentOperator.doOperation()`
    2. there is a switch case ask for action choice `[print: @9-@14]`
    3. user choose the actions "creates an enquiry", calls `StudentEnquiryMS.createEnquiry()` `{input: "1"@14}`
        1. inside `StudentEnquiryMS.createEnquiry()` `[print: @15-@17]`
        2. ask for camp to send enquiry to `[print: @18-@19]`
            1. Error handling
            2. if input is `blank`, print camp that is visible to user. calls `DATABASE.printCampsForStudent(this.userID)` `{input: ""@19}`
                1. inside `printCampsForStudent()`
                2. get user's faculty by call `DATABASE.findStudent(userID).faculty()`
                3. print Camps under user's faculty and NTU `[print: @20-@22]`
            3. do `step 2.iii.b`(ask for camp) again `[print: @23-@24]`
        3. get user input `{input: "Camp1"@24}`
        4. check whether it is a valid camp for student, calls `DATABASE.getFaculty(id)`
            1. inside `getFaculty()`
            2. check weather id exists in `userMap.containsKey()` and `campMap.containsKey()` (because this function can be used for both user and camp.)
            3. Error Handling:
               1. if there is no such camp, print out error message, `return null` `[print: @76]`
            4. if there is such camp, `return faculty` a String
        5. Error Handling
           1. if get null from DATABASE, `return null`
        6. if get String, compare is with `DATABSE.getFaculty(this.userID)` and "NTU"
            1. if false: print out error message, `return null` `[print: @91]`
            2. if true: we proceed to the creation process
        7. ask for message `[print: @25-@26]`
        8. check whether input is blank,
            1. Error Handling: if blank do `step 2.iii.j` again
        9. create enquiry, calls `DATABASE.createEnquiry(this.userID, camp, message)` `[print: @27]`
        10. Error Handling:
            1. if it is null, output error message: "Unable to create enquiry.", `return null`.
        11. `DATABASE.createEnquiry()` should return a `String`, which is the Enquiry ID, name it `enquiryID`
        12. print success message `[print: @27]`
        13. output the result, calls `DATABSE.findEnquiry(enquiryID).printSelf()`
        14. `return enquiryID`
    4. Error Handling:
        1. if we get `null` from `StudentEnquiryMS.createEnquiry()`, exit the `doOperation()` to the main function, ie `return;`
    5. if we get `String`, name it `enquiryID`.
    6. add the enquiryID to `StudentData`, calls `StudentMS.addEnquiry(enquiryID)`
        1. inside `StudentMS.addEnquiry(enquiryID)`
        2. calls `DATABSE.findStudent(this.userID).addEnquiry(enquiryID)` // we assume user is always found.
            1. Error Handling:
                1. if `StudentData.addEnquiry()` got error, inside `StudentData.addEnquiry()` will print the error message, and returns false
        3. return the result of `DATABSE.findStudent(this.userID).addEnquiry(enquiryID)`
    7. Error Handling:
        1. if got `false` from `StudentMS.addEnquiry(enquiryID)`
        2. call `DATABASE.deleleEnquiry(enquiryID)`
        3. exit to the `main` function
    8. if got `true`, call `StudentCampMS.addEnquiry(enquiryID)`
        1. inside `StudentCampMS.addEnquiry()`
        2. call `DATABSE.findCampForRequet(enquiryID).addEnquiry(enquiryID)`
            1. inside `DATABSE.findCampForRequet()`
            2. search in `enquiryMap` and `suggestionMap`, for the `enquiry`
            3. get `campID` // we can assume there is always such an enquiry
            4. `return this.campMap.get(campID)`
            5. Error Handling: if `CampData.addEnquiry()` got error, inside `CampData.addEnquiry()` will print the error message, and returns false
        3. return the result of `DATABSE.findStudent(this.userID).addEnquiry(enquiryID)`
    9. Error Handling:
        1. if got `false` from `this.userMS.addEnquiry(enquiryID)`
        2. call `DATABASE.deleleEnquiry(enquiryID)`
        3. call `StudentUserMS.deleteEnquiry(enquiryID)`
        4. exit to the main function
    10. if got `true`, exit to the main function
3. /* you may ignore
4. if `operator` is `StudentOperator`,
    1. check whether he became a Committee Member or not, call `StudentMS.isCommitteeMember()`
        1. inside `StudentMS.isCommitteeMember()`
        2. `return DATABASE.findStudent(this.userID).isCommitteeMember()`
    2. if he become a CM, call `operator = LoginMS.becomeCM(userID);`
5. re execute `step 2` */

```
@1 WELCOME to CAMS!
@2 Do you want to login [Y/n]: Y
@3 userID: STUDENT001
@4 password: ****
@5 ## WELCOME User Name(email) at faculty! ##
@6
@7 ## MAIN APP ##
@8
@9 Choose your action:
@10 1. create an enquiry.
@11 2. edit an enquiry.
@12 3. ....
@13 ...
@14 Type in choice: 1
@15
@16 ## ENQUIRY CREATTION ##
@17
@18 Which camp do you want to send enquiry to? (leave blank to print all the camp for your faculty)
@19 Camp:
@20 This is all the camps you can send enquiry to:
@21 1. Camp1 2. Camp2 3. Camp3 4. Camp4
@22 5. Camp5 6. Camp6
@23 Which camp do you want to send enquiry to? (leave blank to print all the camp for your faculty)
@24 Camp: Camp1
@25 What message do you want to send to Camp1?
@26 message: Hello, Camp1!
@27 Enquiry created: ID: 1d27897435b76679bc262be5ec91aa87edddd5d120a1d21a1c5622c6105659ba
@28 ENQUIRY:
@29 sender: STUDENT001
@30 camp: Camp1
@31 Status: PENDING
@32 message: Hello, Camp1!
@33 There is no reply.
@34
@35 ## MAIN APP ##
@36
@37 Choose your action:
@38 1. create an enquiry.
@39 2. edit an enquiry.
@40 3. ....
@41 ...
@42 Type in choice: 2
@43
@44 ## ENQUIRY EDITING ##
@45
@46 Which enquiry do you want to edit?
@47 1. Camp: Camp1
@48 message: Hello, Camp1!
@49 Type in index: 1
@50 EDITING no. 1:
@51 Which field do you want to edit? [c(amp)/m(essage)/e(xit)]: m
@52 OLD Message: Hello, Camp1!
@53 NEW Message: Hello, new Camp1!
@54 Which field do you want to edit? [c(amp)/m(essage)/e(xit)]: e
@55 Enquiry updated successfully!
@56 ENQUIRY:
@57 sender: STUDENT001
@58 camp: Camp1
@59 Status: PENDING
@60 message: Hello, new Camp1!
@61 There is no reply.
@62
@63 ## MAIN APP ##
@64
@65 Choose your action:
@66 1. create an enquiry.
@67 2. edit an enquiry.
@68 3. ....
@69 ...
@70 Type in choice: 1
@71
@72 ## ENQUIRY CREATTION ##
@73
@74 Which camp do you want to send enquiry to? (leave blank to print all the camp for your faculty)
@75 Camp: ccc1
@76 ERROR: ccc1 NOT FOUND.
@77
@78 ## MAIN APP ##
@79
@80 Choose your action:
@81 1. create an enquiry.
@82 2. edit an enquiry.
@83 3. ....
@84 ...
@85 Type in choice: 1
@86
@87 ## ENQUIRY CREATTION ##
@88
@89 Which camp do you want to send enquiry to? (leave blank to print all the camp for your faculty)
@90 Camp: Camp7
@91 ERROR: you do not have access to Camp7.
@92
@93 ## MAIN APP ##
@94
@95 Choose your action:
@96 1. create an enquiry.
@97 2. edit an enquiry.
@98 3. ....
@99 ...
@100 Type in choice: 1
```

you may ignore the text below.

```
WELCOME to CAMS!
Do you want to login [Y/n]: Y
userID: STUDENT001
password: ****

## WELCOME User Name(email) at faculty! ##

## MAIN APP ##

Choose your action:

1. create an enquiry.
2. edit an enquiry.
3. ....
   ...
   Type in choice: 1

## ENQUIRY CREATTION ##

Which camp do you want to send enquiry to? (leave blank to print all the camp for your faculty)
Camp:
This is all the camps you can send enquiry to:

1. Camp1 2. Camp2 3. Camp3 4. Camp4
5. Camp5 6. Camp6
   Which camp do you want to send enquiry to? (leave blank to print all the camp for your faculty)
   Camp: Camp1
   What message do you want to send to Camp1?
   message: Hello, Camp1!
   Enquiry created:
   ENQUIRY:
   sender: STUDENT001
   camp: Camp1
   Status: PENDING
   message: Hello, Camp1!
   There is no reply.

## MAIN APP ##

Choose your action:

1. create an enquiry.
2. edit an enquiry.
3. ....
   ...
   Type in choice: 2

## ENQUIRY EDITING ##

Which enquiry do you want to edit?

1. Camp: Camp1
   message: Hello, Camp1!
   Type in index: 1
   EDITING no. 1:
   Which field do you want to edit? [c(amp)/m(essage)/e(xit)]: m
   OLD Message: Hello, Camp1!
   NEW Message: Hello, new Camp1!
   Which field do you want to edit? [c(amp)/m(essage)/e(xit)]: e
   Enquiry updated successfully!
   ENQUIRY:
   sender: STUDENT001
   camp: Camp1
   Status: PENDING
   message: Hello, new Camp1!
   There is no reply.

## MAIN APP ##

Choose your action:

1. create an enquiry.
2. edit an enquiry.
3. ....
   ...
   Type in choice: 1

## ENQUIRY CREATTION ##

Which camp do you want to send enquiry to? (leave blank to print all the camp for your faculty)
Camp: ccc1
ERROR: ccc1 NOT FOUND.

## MAIN APP ##

Choose your action:

1. create an enquiry.
2. edit an enquiry.
3. ....
   ...
   Type in choice: 1

## ENQUIRY CREATTION ##

Which camp do you want to send enquiry to? (leave blank to print all the camp for your faculty)
Camp: Camp7
ERROR: you do not have access to Camp7.

## MAIN APP ##

Choose your action:

1. create an enquiry.
2. edit an enquiry.
3. ....
   ...
   Type in choice: 1
```
