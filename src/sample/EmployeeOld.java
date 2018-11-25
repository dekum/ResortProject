package sample;
/**
 * EmployeeOld.java Class
 * Simple____Property allows for ease in making columsn in table views
 * Look up how SimpleStringPropertyWorks
 *....
 */

import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public  class EmployeeOld {

  private final SimpleStringProperty name;
  private final SimpleDoubleProperty payHourly;
  private final SimpleIntegerProperty employeeID;
  private final SimpleStringProperty title;
  private final SimpleStringProperty shiftStart;
  private final SimpleStringProperty shiftEnd;
  private ArrayList<CustomDate> workDays = new ArrayList<>();

  public Boolean checkIfWorkDayExists(LocalDate localdate){
    boolean isOriginal= true;
    for (CustomDate d:workDays
    ) {
      //check if local dates match, if they do then that day already exists in workDays list
      //System.out.println(d.date+" vs. "+localdate);
      if(d.date.isEqual(localdate)){
        //System.out.println(d.date);
        isOriginal = false;
      }


    }
    return isOriginal; //If true create new CustomDay, if false dont create new one

  }

  public ArrayList<CustomDate> getWorkDays() {
    return workDays;
  }

  public void setWorkDays(ArrayList<CustomDate> workDays) {
    this.workDays = workDays;
  }

  public EmployeeOld(String fName, double payme, Integer id, String title,
      String fstart, String fend) {
    this.name = new SimpleStringProperty(fName);
    this.payHourly = new SimpleDoubleProperty(payme);
    this.employeeID = new SimpleIntegerProperty(id);
    this.title = new SimpleStringProperty(title);
    this.shiftStart =new  SimpleStringProperty(fstart);
    this.shiftEnd = new SimpleStringProperty(fend);
  }

  public EmployeeOld(String fName, double payme, Integer id) {
    this.name = new SimpleStringProperty(fName);
    this.payHourly = new SimpleDoubleProperty(payme);
    this.employeeID = new SimpleIntegerProperty(id);
    this.title = new SimpleStringProperty("Unpaid Intern");
    this.shiftStart =new  SimpleStringProperty("N/A");
    this.shiftEnd = new SimpleStringProperty("N/A");


  }



  public String getName() {
    return name.get();
  }

  public void setName(String fName) {
    name.set(fName);
  }

  public String getTitle() {
    return title.get();
  }

  public void setTitle(String Ftitle) {
    title.set(Ftitle);
  }

  public String getShiftStart() {
    return shiftStart.get();
  }

  public void setShiftStart(String Fstart) {
    shiftStart.set(Fstart);
  }

  public String getShiftEnd() {
    return shiftEnd.get();
  }

  public void setShiftEnd(String Fend) {
    shiftEnd.set(Fend);
  }

  public Double getPayHourly() {
    // System.out.println("HERE");
    //return "out";
    return payHourly.get();
  }

  public void setPayHourly(Double payme) {
    // System.out.println("paymee");
    payHourly.set(payme);

  }



  public Integer getEmployeeID() {
    return employeeID.get();
  }

  public void setEmployeeID(Integer id) {
    employeeID.set(id);
  }
}