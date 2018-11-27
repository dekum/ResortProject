package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadEvents {
  ReadEvents() {

    File roomFile = new File("lib/resortEvents.txt");
    Scanner scanner = null;
    try {
      scanner = new Scanner(roomFile);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }
    String currentLine;
    ArrayList<ResortEvent> events = new ArrayList<>();


    int lineNumber = 0;
    int lineNumberForRoom=0;
    Boolean passedLine = false;
    ResortEvent creatEvent= new ResortEvent();
    while(scanner.hasNextLine())
    {
      currentLine = scanner.nextLine();
      lineNumber++;
      //System.out.println( currentLine);
      if(passedLine)
      {
        //System.out.println("Worked!");
        //Do other task after passing the line.
        if(!currentLine.equals("|_End_|"))
        {
          //Not the end of the room data
          //Ok Print room data


         // System.out.println(currentLine + "-"+ lineNumber+ "-"+lineNumberForRoom);
          //passedLine =false;
          if (lineNumberForRoom==1){
            //Event name
            creatEvent.setName(currentLine);

            creatEvent.setNameproperty(currentLine);


          }
          if (lineNumberForRoom==2){
            //Event Date
            creatEvent.setDate(currentLine);
            creatEvent.setDateproperty(currentLine);

          }



        }else {
          //Okay stop, current room data is over
         // System.out.println("End of Room Data");
          events.add(creatEvent);
          passedLine=false;

        }
        lineNumberForRoom++;

      }

      if(currentLine.equals("|_Start_|"))
      {
        //Do task
        creatEvent = new ResortEvent();
        passedLine = true;
        lineNumberForRoom=1; //starting from 1
        //Room has 4 fields currently, 1.Name(Str)  2.IsAvaiable(bool) 3.Price(double) 4.PcitureURL(str)
      }



    }
    for (ResortEvent r : events
    ) {
//      System.out.println(r.getName());
//      System.out.println(r.getDate());


    }

    System.out.println("Events Read");
    Global.eventList= events;
  }


  }

