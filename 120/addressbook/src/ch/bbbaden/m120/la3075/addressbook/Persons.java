/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.m120.la3075.addressbook;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fabia
 */
public class Persons {
  List<Person> persons = new ArrayList<>();

  public Persons() {
    persons.add(new Person("Hans", "Muster"));
    persons.add(new Person("Robert", "Marques"));
    persons.add(new Person("Filip", "Rajic"));
    persons.add(new Person("Fabian", "Eichenberger"));
    persons.add(new Person("Luca", "Häfeli"));
  }

  public List<Person> getPersons() {
    return persons;
  }
}
