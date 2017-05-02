package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import view.MediaController;
import view.View;

public class Main extends Application {
	@Override
    public void start(Stage primaryStage) {
	Model model = new Model();
		
        try {
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource("/view/Media.fxml"));
        	Parent root = loader.load();
			Scene scene = new Scene(root);	
            primaryStage.setScene(scene);
            primaryStage.setTitle("My Media");
            primaryStage.show();
            
            
            // Give the controller access to the model.
            MediaController controller = loader.getController();
            controller.setModel(model);
            model.addListener(controller);
        } catch (Exception ex) {
        	System.out.println("Failed to load main Media fxml");
        	ex.printStackTrace();
        }
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}

