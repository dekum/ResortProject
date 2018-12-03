package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import sample.Global;
import sample.Room;

/**
 * Class used to read event info from text file
 * Utilizes scanner to iterate through text file
 * Finds "Start" String and reads each line and sets to corresponding object value
 * Adds new Room to Global empList ArrayList of object 'Room'
 */
public class ReadRooms {
  ReadRooms() {

    File roomFile = new File("lib/rooms.txt");
    Scanner scanner = null;
    try {
      scanner = new Scanner(roomFile);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }
    String currentLine;
    ArrayList<Room> rooms = new ArrayList<>();


    int lineNumberForRoom=0;
    Boolean passedLine = false;
    Room createRoom= new Room();
    while(scanner.hasNextLine())
    {
      currentLine = scanner.nextLine();
      if(passedLine)
      {

        if(!currentLine.equals("|_End_|"))
        {
          if (lineNumberForRoom==1){
            //Room name
            createRoom.setName(currentLine);
            createRoom.setnameProperty(currentLine);


          }
          if (lineNumberForRoom==2){
            //isAvailable
            createRoom.setAvailable(Boolean.valueOf(currentLine));

          }
          if (lineNumberForRoom==3){
            //room price
            createRoom.setPrice(Double.parseDouble(currentLine));


          }
          if (lineNumberForRoom==4){
            //PictureUrl
            createRoom.setPictureUrl(currentLine);

          }
          if (lineNumberForRoom==5){
            for (Guest g: Global.guestList){
              System.out.println( g.toString() + "  " + currentLine);
              if (currentLine.equals(g.toString())){
                createRoom.setOccupiedGuest(g);
              }

            }


          }
          if (lineNumberForRoom==6){
            //totalprice
            createRoom.setTotalPrice(Double.parseDouble(currentLine));

          }
          if (lineNumberForRoom==7){
            //day in
            if (currentLine.equals("null")){
              createRoom.setDayIn(null);
            }else{
              createRoom.setDayIn(LocalDate.parse(currentLine));
            }


          }
          if (lineNumberForRoom==8){
            //day out
            if (currentLine.equals("null")){
              createRoom.setDayOut(null);
            }else {
              createRoom.setDayOut(LocalDate.parse(currentLine));
            }
          }


        }else {
          rooms.add(createRoom);
          passedLine=false;

        }
        lineNumberForRoom++;

      }

      if(currentLine.equals("|_Start_|"))
      {
        //Do task
        createRoom = new Room();
        passedLine = true;
        lineNumberForRoom=1; //starting from 1
        //Room has 4 fields currently, 1.Name(Str)  2.IsAvaiable(bool) 3.Price(double) 4.PcitureURL(str)
      }

    }

    Global.roomList= rooms;
  }


  }

