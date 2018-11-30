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
//    ArrayList<ResortEvent> events = new ArrayList<>();
//    events.add((new ResortEvent("ZombieCon", "2018-11-29")));
//    events.get(0).setEventDescription("\n \n \n dfasfasd");
//    events.add((new ResortEvent("Crab Race", "2018-11-30")));
//    events.add((new ResortEvent("Karaoke", "2018-12-02")));
//    events.add((new ResortEvent("Boat Show", "2018-12-04")));
//    events.add((new ResortEvent("ArtWalk", "2018-12-05")));
//    events.add((new ResortEvent("Car Show", "2018-12-09")));
    //Global.eventList = events;
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
