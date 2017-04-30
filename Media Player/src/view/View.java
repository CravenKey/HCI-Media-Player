package view;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import controller.Controller;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Listener;
import model.Model;

public class View implements Listener{
	
	
	ArrayList<Integer> SuccList = new ArrayList<Integer>();
	private final Controller controller;
	private final Model model;
	private final Stage primaryStage;
	private BorderPane borderPane;
	
	Scene scene;
	
	public View( final Model model, final Controller controller, final Stage stage, final BorderPane borderPane) 
	{
		model.setListener(this);
		
		this.borderPane = borderPane;
 	    this.primaryStage = stage;
 	    this.controller = controller;
		this.model = model; 
		
	
		inputScreen( controller, primaryStage);
		
		primaryStage.setScene( scene ); 
		primaryStage.show();
	}
	
	private void inputScreen( final Controller controller, Stage primaryStage  ) 
	{
		
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(View.class.getResource("view/RootLayout.fxml"));
            borderPane = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(borderPane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }	
	}
	
	private void PerformFittsTest( final Controller controller, Stage primaryStage) { 
	
	}

	@Override
	public void endTest() {
	
	}

	@Override
	public void clickRecorded() {
		
		
	
	}
	
	@Override
	public void startTest() {
		PerformFittsTest(controller, primaryStage);
	}
}
