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


    int lineNumber = 0;
    int lineNumberForEmployee=0;
    Boolean passedLine = false;
    Employee createEmployee= new Employee();
    while(scanner.hasNextLine())
    {

      currentLine = scanner.nextLine();//read next file line
      lineNumber++;//increments
      //System.out.println( currentLine);
      if(passedLine)
      {
        //System.out.println("Worked!");
        //Do other task after passing the line.
        if(!currentLine.equals("|_End_|"))
        {
          //Read line of data for employee variable


       //   System.out.println(currentLine + "-"+ lineNumber+ "-"+lineNumberForEmployee);
          //passedLine =false;
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
          //Okay stop, current room data is over
          //System.out.println("End of Employee Data");
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
    for (Employee e :emps
    ) {
//      System.out.println(e.getFirstName());
//      System.out.println(e.getLastName());
//      System.out.println(e.getWage());
//      System.out.println(e.getDOB());
//      System.out.println(e.getBothNamesProperty());

    }

    System.out.println("Employee Read");
    Global.empList= emps;
  }
}
