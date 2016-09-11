/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.m120.la3075.addressbook;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fabia
 */
public class PersonViewController implements Initializable {

  @FXML
  private TextField firstNameInput;
  @FXML
  private TextField lastNameInput;
  @FXML
  private TextField plzInput;
  
  private Person person;
  private boolean committed;
  private Stage stage;

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  public boolean isCommitted() {
    return committed;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
    firstNameInput.setText(person.getFirstName());
    lastNameInput.setText(person.getLastName());
    plzInput.setText(Integer.toString(person.getPlz()));
  }

  // called when the `ok` button is beeing clicked
  @FXML
  private void onOk(ActionEvent event) {
    this.committed = true;
    person.setFirstName(firstNameInput.getText());
    person.setLastName(lastNameInput.getText());
    person.setPlz(Integer.valueOf(plzInput.getText()));
    stage.close();
  }

  @FXML
  private void onCancel(ActionEvent event) {
    this.committed = false;
    stage.close();
  }
}
