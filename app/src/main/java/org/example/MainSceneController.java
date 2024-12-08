package org.example;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class MainSceneController implements Initializable {

    @FXML
    private Button fxAdd;

    @FXML
    private TableColumn<Donors, Integer> fxAge;

    @FXML
    private ComboBox<String> fxComboBox;

    @FXML
    private Button fxDelete;

    @FXML
    private TableColumn<Donors, Integer> fxID;

    @FXML
    private Button fxInsert;

    @FXML
    private TextField fxTextAge;

    @FXML
    private TextField fxTextFirstName;

    @FXML
    private TextField fxTextLastName;
    
    @FXML
    private TextField fxTextMiddleName;

    @FXML
    private TableColumn<Donors, String> fxUUID;

    @FXML
    private TableColumn<Donors, String> fxbloodType;

    @FXML
    private TableColumn<Donors, String> fxfirstName;

    @FXML
    private TableColumn<Donors, String> fxlastName;

    @FXML
    private TableColumn<Donors, String> fxmiddleName;

    @FXML
    private AnchorPane overlayPane;

    @FXML
    private TableView<Donors> table;


    // init
      // Initializing data from the database
    private ObservableList<Donors> initialData() {
          database b1 = new database();
          String init = "SELECT * FROM donor";
          LinkedList<Donors> data = b1.selectDonorsData(init);

          // kinda useless code
          ArrayList<Donors> data2 = new ArrayList<Donors>();
          for (int i = 0; i < data.size(); i++) {
              int id = data.get(i).getId();
              int age = data.get(i).getAge();
              String bloodtype = data.get(i).getBloodType();
              String firstName = data.get(i).getFirstName();
              String middleName = data.get(i).getMiddleName();
              String lastName = data.get(i).getLastName();
              String uuid = data.get(i).getUuid();
              Donors d1 = new Donors(id , uuid, bloodtype, firstName,middleName, lastName,age);
              data2.add(d1);
          }
          // kinda useless code cuz  setCellValueFactory(new setCellValueFactory) looks for getters in the donors 

          return FXCollections.observableArrayList(data);
      }

      String[] bloodTypes = {"A+" , "A-" ,"B+" ,"B-" ,"AB+" ,"AB-" , "O+" , "O-"};
      ObservableList<String> options = FXCollections.observableArrayList(bloodTypes);

        
  
      // Initialize TableView
      @Override
      public void initialize(URL location, ResourceBundle resources) { 
          // finds getter shits on the Donors class and retrieve the attr
          fxID.setCellValueFactory(new PropertyValueFactory<>("id"));
          fxUUID.setCellValueFactory(new PropertyValueFactory<>("uuid"));
          fxbloodType.setCellValueFactory(new PropertyValueFactory<>("bloodType"));
          fxfirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
          fxmiddleName.setCellValueFactory(new PropertyValueFactory<>("middleName"));
          fxlastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
          fxAge.setCellValueFactory(new PropertyValueFactory<>("age"));
          table.setItems(initialData());
          fxComboBox.setItems(options);
      }

/// ACTION SHIT ////



    @FXML
    void btnAdd(ActionEvent event) {
        showOverlay();
    }

    @FXML
    private void btnDelete(ActionEvent event) {

        // table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
        int SelectedId = table.getSelectionModel().getSelectedItem().getId();
        new database();

        database.deliverDonor(SelectedId);
        table.setItems(initialData());
    }

    @FXML
    private void showOverlay() {
        overlayPane.setVisible(true);
    }

    @FXML
    private void hideOverlay() {
        overlayPane.setVisible(false);
    }

    @FXML
    private void insertBtnAction() {
        new database();
        String firstName = fxTextFirstName.getText();
        String lastName = fxTextLastName.getText();
        String middleName = fxTextMiddleName.getText();
        int age = Integer.parseInt(fxTextAge.getText());
        String bloodType = fxComboBox.getValue();
        database.Insert(bloodType, firstName, middleName, lastName, age);
        // Clear the text fields after inserting
        fxTextFirstName.clear();
        fxTextLastName.clear();
        fxTextMiddleName.clear();
        fxTextAge.clear();  // If fxTextAge is a TextField for the age input
        fxComboBox.setValue(null);  // Optionally, clear the combo box
        table.setItems(initialData());
    }
}
  
      


