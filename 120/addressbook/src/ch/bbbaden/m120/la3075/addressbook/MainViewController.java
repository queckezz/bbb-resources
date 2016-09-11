/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bbbaden.m120.la3075.addressbook;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fabia
 */
public class MainViewController implements Initializable {

  private Person selectedPerson;
  private ListView<Person> listView;
  private ObservableList<Person> persons;
  @FXML
  private TableView<Person> tableView;
  @FXML
  private TableColumn<Person, String> firstNameColumn;
  @FXML
  private TableColumn<Person, String> lastNameColumn;
  @FXML
  private Label firstNameValue;
  @FXML
  private Label lastNameValue;
  @FXML
  private Label plzValue;
  @FXML
  private Button deleteButton;
  @FXML
  private Button AddButton;
  @FXML
  private Button EditButton;

  // gets called when the scene is active
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // create an observable list with some defaults
    persons = FXCollections.observableArrayList(new Persons().getPersons());
    
    // given the `setItems(object)` object, bind a value to a column
    firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
    lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    
    // set the object persons into the tableview
    tableView.setItems(persons);
    
    // `tableView` is now reactive, so you can add entities into `persons`
    // observableList and it gets added to the GUI visually.
    persons.add(new Person("Florian", "Muellerson"));
    
    tableView
      .getSelectionModel().
      selectedItemProperty()
      .addListener((obs, oldValue, newValue) -> {
        // set the currently selected person to a variable so we can access it
        // when we click the `edit` button for example
        selectedPerson = newValue;
        
        // When selecting a row, show the new persons details calling
        // `showPersonDetails`
        showPersonDetails(newValue);
      });
  }
  
  // function to create information dialogs easily
  static private void showInfoDialog(String headerText) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Adressbook information");
    alert.setHeaderText(headerText);
    alert.showAndWait();
  }
  
  static private void showNoPersonsDialog () {
    showInfoDialog("No person has been selected yet");
  }
  
  public void showPersonDetails(Person person) {
    firstNameValue.setText(person.firstNameProperty().getValue());
    lastNameValue.setText(person.lastNameProperty().getValue());
    plzValue.setText(Integer.toString(person.plzProperty().getValue()));
  }

  // gets called when clicking on the delete button
  @FXML
  private void onDelete(ActionEvent event) {
    // get the current selected index int the table view
    int index = tableView.getSelectionModel().getSelectedIndex();
    
    // if nothing has been selected yet, show an alert. Otherwise remove the
    // person
    if (index != -1) {
      // remove the person from the list. This also removes it from the GUI
      // since it has been bound with an observable.
      persons.remove(index); 
    } else {
      showNoPersonsDialog();
    }
  }

  private boolean initPersonViewDialog (Person person) throws IOException {
    // basically just boot up the new view `PersonView.fxml`
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonView.fxml"));
    Parent root = (Parent) fxmlLoader.load();
    PersonViewController controller = fxmlLoader.getController();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));

    // delegate to the controller the selected person so that it can be
    // displayed in the new view
    controller.setPerson(person);

    // set the stage so we can close the dialog from the controller
    controller.setStage(stage);

    // Show the dialog and wait until the user closes it
    stage.showAndWait();

    // return whether we want to commit/edit the person or not
    return controller.isCommitted();
  }
  
  // gets called when clicking on the create button
  @FXML
  private void onCreate(ActionEvent event) throws IOException {
    // create an empty person to edit
    Person newPerson = new Person();
    boolean canCommit = initPersonViewDialog(newPerson);
    if (canCommit) {
      persons.add(newPerson);
    }
  }

  // gets called when clicking on the edit button
  @FXML
  private void onEdit(ActionEvent event) throws IOException {
    if (selectedPerson != null) {
      boolean canCommit = initPersonViewDialog(selectedPerson);
      if (canCommit) {
        showPersonDetails(selectedPerson);
      }
    } else {
      showNoPersonsDialog();
    }
  }
}
