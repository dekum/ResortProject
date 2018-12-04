# ResortProject
![Image](https://github.com/dekum/ResortProject/blob/master/src/sample/Pictures/icons8-beach-filled-50.png?raw=true)
Group Project for Software Engineering Fundamentals


# Table of Contents
- [Summary](#Summary)
- [Contributors](#Contributors)
- [How To Install](#How_to_Install)
- [Images](#Images)
- [Demo](#demo)

# Contributors:
Team Name: Aces
* Nicolo Martina - Assisted in both back-end and front-end development and oversaw any changes, njmartina4082@eagle.fgcu.edu
* Philemon Petit-Frere - Lead back-end programmer,  ppetitfrere9554@eagle.fgcu.edu 
* Garret Graber - Lead front-end developer, ggraber7402@eagle.fgcu.edu 
* Brad Larson - Assisted in back-end coding, lead SQA tester, blarson1953@eagle.fgcu.edu 
* Austin Horne - Assisted in program functionality, SQA tester, adhorne0633@eagle.fgcu.edu 


# Summary
<p>This program demonstrate a GUI based program that allows user to login as a guest or a manager for a Resort. A user can create a new guest account, providing username, password and other credentials.  A guest can pick how many days, and what room they wish to book, and purchase said room. They can then be able to view their purchase history. While the Manager is able to edit, add, or move the Rooms, Events and Employees. The information is stored in 5 separate text files and using the FileReader function, the data is stored even if the program is closed.</p>


# How to Install
Needed Programs: Windows Vista or above and Java
Navigate to https://github.com/dekum/ResortProject -> .idea -> artifacts and download the 'Resort_Project.zip' file.
Extract the contents to your choice of location. Ensure that the Resort_Project.jar AND lib folder are in the same directory.
Double click Resort_Project.jar to run.

# Images
Images of Login Screen, Sign up Screen, Guest View, Manager View
<br>
![Gui Picture](https://github.com/dekum/ResortProject/blob/master/images/Loginpage.png)<br>
The login screen, here the user can select the Guest or Manager tab to log in as respective user, the ruby is the signup button and will open the Sign Up Screen<br>
![Gui Picture](https://github.com/dekum/ResortProject/blob/master/images/SignupPage.png)<br>
This is where the user creates a new account, they must enter a valid username and password. The program will highlight the error if the user fills out a textfield incorrectly. The Date of Birth calender automatically sets the date 18 years from the current date because users must be 18 or older to signup.<br>
![Gui Picture](https://github.com/dekum/ResortProject/blob/master/images/GuestViewPage.png)<br>
When the user sucessfully logs in this see this window. In the Guest View the user can select the date In and date Out using the datePicker function of javafx. <br>When the user clicks "View Rooms" the rooms are showned the lower middle gray box. The price per day is displayed also. If the user approves, they can click bookroom and the payment screen shows up. The User enters credit information and the room is successfully purchased. <br>Then the "Purchase History" button appears in the bottom right, when the user can see the room they purchased.<br>
![Gui Picture](https://github.com/dekum/ResortProject/blob/master/images/PurchaseHistoryPage.png)<br>
This window displays the room information the user booked.<br>
![Gui Picture](https://github.com/dekum/ResortProject/blob/master/images/ManagerViewEmployee.png)<br>
If user logins as Manager, they can edit employee info fields via Add,Update and Remove Employee buttons.<br>
![Gui Picture](https://github.com/dekum/ResortProject/blob/master/images/ManagerViewRooms.png)<br>
User can edit room information, the type of room and the price of it. These rooms are connected and displaye to the GuestView.<br>
![Gui Picture](https://github.com/dekum/ResortProject/blob/master/images/ManagerViewEvents.png)<br>
User can edit event information. Events have a name,price, description and date.<br>
![Gui Picture](https://github.com/dekum/ResortProject/blob/master/images/ManagerViewSummary.png)<br>
This is the summary page, it keeps count of all events, rooms and employees. it updates automatically if any room event or employee is added or removed.
# Demo
Gif of prgram wokring

