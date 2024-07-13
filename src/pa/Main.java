package pa;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button; // Import Button class
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Database Connection");

        // Create DBConnectionPanel and Connect to Database button
        DBConnectionPanel dbConnectionPanel = new DBConnectionPanel();
        Button connectButton = new Button("Connect to Database");
        connectButton.setOnAction(e -> {
            dbConnectionPanel.connectToDatabase();
        });

        VBox root = new VBox(10);
        root.getChildren().add(dbConnectionPanel);
        root.getChildren().add(connectButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

