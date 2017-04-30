package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.Model;

public class Controller 

{
	private final Model model;
	private final Stage stage;
		
	public Controller( final Model model, Stage stage ) 
	{
		
	this.stage = stage;	
	this.model = model;
		
	}
	public EventHandler<MouseEvent> getTargetClickHandler(final Model model, final Node target)
	{
		return e -> {
			System.out.println("Action Performed: Click Record");
			
			
	
			double x = e.getSceneX(); 
			double y = e.getSceneY(); 
		};
	}
	public EventHandler<ActionEvent> getStartTestHandler(

		final Spinner<Integer> clickCounter, 
		final Model model )
		{
		return e -> {
		int count = clickCounter.getValue(); 
		System.out.println("Action Performed: Test Started");
		
		};
	}	
}
