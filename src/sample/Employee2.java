package sample;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee2 {
    private String firstName;
    private String lastName;
    private double wage;
    private LocalDate DOB;
    private String bothNames;
    private StringProperty bothNamesProperty;

    public Employee2(String firstName, String lastName, double wage, LocalDate DOB){
      this.firstName = firstName;
      this.lastName = lastName;
      this.wage = wage;
      this.DOB = DOB;
      this.bothNames = firstName + " " + lastName;
      this.bothNamesProperty = new SimpleStringProperty();
      this.bothNamesProperty.set(bothNames);
    }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public double getWage() {
    return wage;
  }

  public void setWage(double wage) {
    this.wage = wage;
  }

  public LocalDate getDOB(){
      return DOB;
  }

  public void setDOB(LocalDate DOB){
      this.DOB = DOB;
  }

  public StringProperty getBothNamesProperty(){
      return bothNamesProperty;
  }

}