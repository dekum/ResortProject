package sample;


import java.time.LocalDate;

public class CustomDate {
  String startHour;
  String endHour;
  int hoursWorked;
  LocalDate date;
  String startHourFirstTwoDigits;
  String endHourFirstTwoDigits;


  Boolean hadHours; //did employee work this day?

  public String getStartHour() {
    return startHour;
  }

  public void setStartHour(String startHour) {
    this.startHour = startHour;
  }

  public String getEndHour() {
    return endHour;
  }

  public void setEndHour(String endHour) {
    this.endHour = endHour;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public Boolean getHadHours() {
    return hadHours;
  }

  public void setHadHours(Boolean hadHours) {
    this.hadHours = hadHours;
  }

  public CustomDate(String startHour, String endHour) {
    this.startHour = startHour;
    this.endHour = endHour;
  }

  public CustomDate(String startHour, String endHour, LocalDate date) {
    this.startHour = startHour;//may need to covdert to 10:00 so i can get am and pm
    this.endHour = endHour;
    this.date = date;
//    int startHourint = Integer.parseInt(startHour);
//    int endHourint = Integer.parseInt(endHour);
//    if (startHourint<endHourint){
//      //Probably went from AM to PM
//      endHourint +=12;
//      hoursWorked= endHourint-startHourint;
//      System.out.println(hoursWorked+ "hours worked");//if doesnt work maybe i can do in for loop
//    }
   // hoursWorked= Integer.parseInt(startHour); NEED to check for AM and PM so cnat do this yet.
  }
}
