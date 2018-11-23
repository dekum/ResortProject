package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResortEvent {
    private String name;
    private StringProperty nameproperty;

    public ResortEvent(String name){
      this.name = name;
      nameproperty = new SimpleStringProperty();
      nameproperty.set(name);
    }

    public StringProperty getNameproperty(){
      return nameproperty;
    }
}
