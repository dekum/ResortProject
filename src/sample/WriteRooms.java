package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteRooms {

  public WriteRooms(){
    Writer fileWriter = null;
    try {
      fileWriter = new FileWriter("./lib/rooms.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }


    PrintWriter printWriter = new PrintWriter(fileWriter);


    ArrayList<Room> rooms = (ArrayList<Room>) Global.roomList;

    for (Room r :rooms
    ) {
      //print rooms in room textfile
      printWriter.println("|_Start_|");
      printWriter.println(r.getName());
      printWriter.println(r.getAvailable());
      printWriter.println(r.getPrice());
      printWriter.println(r.getPictureUrl());
      printWriter.println(r.getOccupiedGuest());
      printWriter.println(r.getTotalPrice());
      printWriter.println(r.getDayIn());
      printWriter.println(r.getDayOut());
      printWriter.println("|_End_|");

    }

    printWriter.close();
    try {
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    File roomFile = new File("lib/rooms.txt");
    Scanner scanner = null;
    try {
      scanner = new Scanner(roomFile);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }
    String currentLine;
    int lineNumber = 0;
    Boolean passedLine = false;
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
          System.out.println(currentLine);
          //passedLine =false;

        }else {
          //Okay stop, current room data is over
          System.out.println("End of Room Data");
          passedLine=false;

        }

      }

      if(currentLine.equals("|_Start_|"))
      {
        //Do task
        passedLine = true;
      }



    }
  }
}
