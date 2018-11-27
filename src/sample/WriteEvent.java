package sample;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class WriteEvent {

  public WriteEvent(){
    //FileWrite Guest

    //ArrayList<Guest> emps = Global.guestList;
    // Global.empList = emps;
    //ArrayList<ResortEvent> resortEvents = new ArrayList<>();
    ArrayList<ResortEvent> events = new ArrayList<>();
    events.add((new ResortEvent("ZombieCon", "Nov 29")));
    events.add((new ResortEvent("Crab Race", "Nov 31")));
    events.add((new ResortEvent("Karaoke", "Dec 2")));
    events.add((new ResortEvent("Boat Show", "Dec 4")));
    events.add((new ResortEvent("ArtWalk", "Dec 5")));
    events.add((new ResortEvent("Car Show", "Dec 9")));
    //Global.eventList = events;

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
