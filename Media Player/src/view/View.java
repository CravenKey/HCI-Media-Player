package view;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import model.Listener;
import model.Model;




public class View implements Listener

{
	public int currentIndex;
	private final Model model;
	
	public View(final Model model, final Controller controller)
	{
		this.model = model;
	}
	
	/*
	{
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(View.class.getResource(".fxml"));
            borderPane = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(borderPane);
            primaryStage.setScene(scene);
            primaryStage.show();	
	}*/
	
	public void updateMedia()
	{
		
	}
	
	@Override
	public void updated() {
		if (currentIndex != model.index)
		updateMedia();
		
		if (model.mediaCache[0] == null )
			updateMedia();
	}
	
}
