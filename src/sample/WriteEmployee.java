package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class used to write new employees to text file
 * Gets current list of employees from Global ArrayList empList
 * FileWriter appends textfile with new employee information
 */
public class WriteEmployee {

  public WriteEmployee(){

    ArrayList<Employee> emps = Global.empList;

    Writer fileWriterEmp = null;
    try {
      fileWriterEmp = new FileWriter("./lib/employees.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }

    PrintWriter printWriterEmp = new PrintWriter(fileWriterEmp);

    for (Employee emp: emps
    ) {
      printWriterEmp.println("|_Start_|");//Needed so filereader nows where to start
      printWriterEmp.println(emp.getFirstName());
      printWriterEmp.println(emp.getLastName());
      printWriterEmp.println(emp.getWage());
      printWriterEmp.println(emp.getDOB());
      printWriterEmp.println(emp.getBothNamesPropertyForReader());

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
