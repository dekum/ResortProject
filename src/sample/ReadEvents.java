package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class used to read event info from text file
 * Utilizes scanner to iterate through text file
 * Finds "Start" String and reads each line and sets to corresponding object value
 * Adds new Event to Global empList ArrayList of object 'Event'
 */
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


    int lineNumberForRoom=0;
    String eventDes ="";
    Boolean startEventDes = false;
    Boolean passedLine = false;
    ResortEvent creatEvent= new ResortEvent();
    while(scanner.hasNextLine())
    {
      currentLine = scanner.nextLine();
      if(passedLine)
      {
        if(!currentLine.equals("|_End_|"))
        {
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
          if (lineNumberForRoom==3){
            //Event Price
            creatEvent.setPrice(Double.parseDouble(currentLine));

          }
          if (startEventDes){
            eventDes +="\n" +currentLine;

          }
          if (lineNumberForRoom==4){
            //Event Description

            creatEvent.setEventDescription(currentLine);
            startEventDes= true;
            eventDes += currentLine;
          }




        }else {
          creatEvent.setEventDescription(eventDes);
          events.add(creatEvent);
          eventDes ="";//clearing
          passedLine=false;
          startEventDes=false;

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
    Global.eventList= events;
  }


  }

