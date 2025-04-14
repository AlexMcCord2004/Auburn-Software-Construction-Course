package edu.au.cpsc.module4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FlightScheduleApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("flight-schedule-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Flight Scheduler");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> {
            Db.saveDatabase();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
