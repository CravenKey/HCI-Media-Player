package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
    public void start(Stage primaryStage) {
        try {
        	Parent root = FXMLLoader.load(getClass().getResource("/view/Media.fxml"));
			Scene scene = new Scene(root);	
            primaryStage.setScene(scene);
            primaryStage.setTitle("My Media");
            primaryStage.show();
        } catch (Exception ex) {
        	System.out.println("Failed to load main Media fxml");
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}

