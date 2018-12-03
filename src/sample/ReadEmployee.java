package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import sample.Employee;
import sample.Global;

/**
 * Class used to read employee info from text file
 * Utilizes scanner to iterate through text file
 * Finds "Start" String and reads each line and sets to corresponding object value
 * Adds new Employee to Global empList ArrayList of object 'Employee'
 */
public class ReadEmployee {
  ReadEmployee() {

    File roomFile = new File("lib/employees.txt");
    Scanner scanner = null;
    try {
      scanner = new Scanner(roomFile);
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
    }
    String currentLine;
    ArrayList<Employee> emps = new ArrayList<>();

    int lineNumberForEmployee=0;
    Boolean passedLine = false;
    Employee createEmployee= new Employee();
    while(scanner.hasNextLine())
    {

      currentLine = scanner.nextLine();//read next file line
      if(passedLine)
      {
        if(!currentLine.equals("|_End_|"))
        {

          if (lineNumberForEmployee==1){
            //employee first name
            createEmployee.setFirstName(currentLine);


          }
          if (lineNumberForEmployee==2){
            //employee last name
            createEmployee.setLastName(currentLine);

          }
          if (lineNumberForEmployee==3){
            //employee wages
            createEmployee.setWage(Double.parseDouble(currentLine));


          }
          if (lineNumberForEmployee==4){
            //employee date of birth
            createEmployee.setDOB(LocalDate.parse(currentLine));

          }
          if (lineNumberForEmployee==5){
            //SimpleString both name
            createEmployee.setBothNamesProperty(createEmployee.getFirstName() + " "+createEmployee.getLastName());

          }


        }else {
          emps.add(createEmployee);
          passedLine=false;

        }
        lineNumberForEmployee++;

      }

      if(currentLine.equals("|_Start_|"))
      {
        //Do task
        createEmployee = new Employee();
        passedLine = true;
        lineNumberForEmployee=1; //starting from 1
        //Room has 5 fields currently, 1. First Name 2. Last Name 3.Wages (double) 4. DOB (Local Date) 5. BothNamese (StringProperty)
      }
    }
    //Sets Global Employee ArrayList for use in other windows
    Global.empList= emps;
  }
}
