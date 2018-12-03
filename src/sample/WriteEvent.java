package sample;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 * Class used to write new event to text file
 * Gets current list of events from Global ArrayList eventList
 * FileWriter appends textfile with new event information
 */
public class WriteEvent {

  public WriteEvent(){

    ArrayList<ResortEvent> events =Global.eventList;

    Writer fileWriterEmp = null;
    try {
      fileWriterEmp = new FileWriter("./lib/resortEvents.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }

    PrintWriter printWriterEmp = new PrintWriter(fileWriterEmp);

    for (ResortEvent event: events
    ) {
      printWriterEmp.println("|_Start_|");//Needed so filereader nows where to start
      printWriterEmp.println(event.getName());
      printWriterEmp.println(event.getDate());
      printWriterEmp.println(event.getPrice());
      printWriterEmp.println(event.getEventDescription());

      printWriterEmp.println("|_End_|");//Needed so filereader knows where to end

    }


    printWriterEmp.close();
    try {
      fileWriterEmp.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
