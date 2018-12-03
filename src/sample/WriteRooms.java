package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Class used to write new room to text file
 * Gets current list of rooms from Global ArrayList roomList
 * FileWriter appends textfile with new room information
 */
public class WriteRooms {

  public WriteRooms(){
    Writer fileWriter = null;
    try {
      fileWriter = new FileWriter("./lib/rooms.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }


    PrintWriter printWriter = new PrintWriter(fileWriter);


    ArrayList<Room> rooms = Global.roomList;

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
  }

}
