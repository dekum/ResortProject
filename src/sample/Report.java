package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Report {
    private String summaryField;
    private double summaryCount;
    private StringProperty fieldProperty;
    private StringProperty countProperty;

    public Report(String field, int count){
      this.summaryField = field;
      this.summaryCount = count;
      fieldProperty = new SimpleStringProperty();
      this.fieldProperty.set(field);
      countProperty = new SimpleStringProperty();
      this.countProperty.set(String.valueOf(count));
    }

  public String getSummaryField() {
    return summaryField;
  }

  public void setSummaryField(String summaryField) {
    this.summaryField = summaryField;
  }

  public double getSummaryCount() {
    return summaryCount;
  }

  public void setSummaryCount(double summaryCount) {
    this.summaryCount = summaryCount;
  }

  public String getFieldProperty() {
    return fieldProperty.get();
  }

  public StringProperty fieldPropertyProperty() {
    return fieldProperty;
  }

  public void setFieldProperty(String fieldProperty) {
    this.fieldProperty.set(fieldProperty);
  }

  public String getCountProperty() {
    return countProperty.get();
  }

  public StringProperty countPropertyProperty() {
    return countProperty;
  }

  public void setCountProperty(String countProperty) {
    this.countProperty.set(countProperty);
  }
}
