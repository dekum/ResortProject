package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadGuest {
  ReadGuest() {

    File roomFile = new File("lib/guests.txt");
    Scanner scanner = null;
    try {
      scanner = new Scanner(roomFile);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }
    String currentLine;
    ArrayList<Guest> guests = new ArrayList<>();


    int lineNumber = 0;
    int lineNumberForEmployee=0;
    Boolean passedLine = false;
    Guest createGuest= new Guest();
    while(scanner.hasNextLine())
    {

      currentLine = scanner.nextLine();//read next file line
      lineNumber++;//increments
      //System.out.println( currentLine);
      if(passedLine)
      {
        //System.out.println("Worked!");
        //Do other task after passing the line.
        if(!currentLine.equals("|_End_|"))
        {
          //Read line of data for employee variable


       //   System.out.println(currentLine + "-"+ lineNumber+ "-"+lineNumberForEmployee);
          //passedLine =false;
          if (lineNumberForEmployee==3){
            //guest firstname
           createGuest.setFirstName(currentLine);



          }
          if (lineNumberForEmployee==4){
            //eguest lastname
            createGuest.setLastName(currentLine);

          }
          if (lineNumberForEmployee==1){
            //guest username
            createGuest.setUserName(currentLine);


          }
          if (lineNumberForEmployee==2){
            //guest password
//            createGuest.setDOB(LocalDate.parse(currentLine));
            createGuest.setPassword(currentLine);

          }
          if (lineNumberForEmployee==5){
            //guestdib
            //createGuest.setBothNamesProperty(createGuest.getFirstName() + " "+createGuest.getLastName());
            createGuest.setDOB(LocalDate.parse(currentLine));
          }


        }else {
          //Okay stop, current room data is over
          //System.out.println("End of Employee Data");
          guests.add(createGuest);
          passedLine=false;

        }
        lineNumberForEmployee++;

      }

      if(currentLine.equals("|_Start_|"))
      {
        //Do task
        createGuest = new Guest();
        passedLine = true;
        lineNumberForEmployee=1; //starting from 1
        //Room has 5 fields currently, 1. First Name 2. Last Name 3.Wages (double) 4. DOB (Local Date) 5. BothNamese (StringProperty)
      }



    }
    for (Guest g :guests
    ) {
//      System.out.println(g.getUserName());
//      System.out.println(g.getPassword());
//      System.out.println(g.getFirstName());
//      System.out.println(g.getLastName());
//
//      System.out.println(g.getDOB());

    }

    System.out.println("Guests Read");
    Global.guestList= guests;
  }
}
