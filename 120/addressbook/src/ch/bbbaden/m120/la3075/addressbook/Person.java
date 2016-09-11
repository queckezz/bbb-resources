/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.m120.la3075.addressbook;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author fabia
 */
public class Person {
  private StringProperty firstName = new SimpleStringProperty("");
  private StringProperty lastName  = new SimpleStringProperty("");
  private IntegerProperty plz = new SimpleIntegerProperty();

  public Person() {}
  
  public Person(String firstName, String lastName) {
    this.firstName.setValue(firstName);
    this.lastName.setValue(lastName);
  }

  public String getFirstName() {
    return firstName.getValue();
  }

  public String getLastName() {
    return lastName.getValue();
  }

  public int getPlz() {
    return plz.getValue();
  }

  public void setFirstName(String firstName) {
    this.firstName.setValue(firstName);
  }

  public void setLastName(String lastName) {
    this.lastName.setValue(lastName);
  }

  public void setPlz(int plz) {
    this.plz.set(plz);
  }

  public StringProperty firstNameProperty() {
    return firstName;
  }

  public StringProperty lastNameProperty() {
    return lastName;
  }
  
  public IntegerProperty plzProperty() {
    return plz;
  }
}
