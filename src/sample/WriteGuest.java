package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class WriteGuest {

  public WriteGuest(){
    //FileWrite Guest

    //ArrayList<Guest> emps = Global.guestList;
    // Global.empList = emps;
    ArrayList<Guest> guestList = new ArrayList<>();
    guestList.add(new Guest("njmartina","password1","Nic","Martin", LocalDate.of(1994, Month.MARCH,23)));
    guestList.add(new Guest("salazar","locket1","Salazar","Fernando", LocalDate.of(1984, Month.AUGUST,20)));
    guestList.add(new Guest("joepeteson","asdfgh1","Jack","Peterson", LocalDate.of(1950, Month.JULY,12)));
    guestList.add(new Guest("bobbyjohn","openseamse1","Bobby","John", LocalDate.of(1973, Month.NOVEMBER,6)));

    Writer fileWriterEmp = null;
    try {
      fileWriterEmp = new FileWriter("./lib/guests.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }

    PrintWriter printWriterEmp = new PrintWriter(fileWriterEmp);

    for (Guest guest: guestList
    ) {
      printWriterEmp.println("|_Start_|");//Needed so filereader nows where to start
      printWriterEmp.println(guest.getUserName());
      printWriterEmp.println(guest.getPassword());
      printWriterEmp.println(guest.getFirstName());
      printWriterEmp.println(guest.getLastName());
      printWriterEmp.println(guest.getDOB());

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
