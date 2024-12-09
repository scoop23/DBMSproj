package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
public class App extends Application implements EventHandler<ActionEvent>{
    
    public static String getGreeting() {
        return "Hello from App!";
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/main.fxml"));
            Parent root = loader.load(); 
            Scene scene = new Scene(root);


            
            primaryStage.setTitle("Blood Donation System");
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
 
    public static void main(String[] args) {
        //new database();
        //database.Insert("B+", "Jenn Carlo", "Jenn" ,"Piamonte", 19);
        launch(args);
    }


    @Override
    public void handle(ActionEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }

}

