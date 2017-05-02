package view;

import java.io.File;
import java.net.URL;

import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
//import javafx.util.Duration;
import model.Listener;
import model.Model;

public class MediaController implements Listener  {

	public Model model;
	public int currentIndex;
	private Duration duration;
	
	
	@FXML
	MediaView mView;
	MediaPlayer player;

	@FXML
	Slider timeSlider;
	
	@FXML
	Slider volumeSlider;
	
	public void initialize() {
		//Initializes the index to an invalid value
		currentIndex = -1;
		/*
		timeSlider.valueProperty().addListener(new InvalidationListener() 
		{
		    public void invalidated(Observable ov) 
		    {
		       if (timeSlider.isValueChanging()) 
		       {
		       // multiply duration by percentage calculated by slider position
		          player.seek(duration.multipliedBy(timeSlider.getValue() / 100.0));
		       }
		    }

			
		});*/
		
		String path = new File("Media Player/src/1-03 Paradise Lost.mp3").getAbsolutePath();
		Media startMedia = new Media(new File(path).toURI().toString());
		player = new MediaPlayer(startMedia);
		
		timeSlider.valueProperty().addListener(new InvalidationListener() {
		    public void invalidated(Observable ov) {
		       if (timeSlider.isValueChanging()) {
		       // multiply duration by percentage calculated by slider position
		          player.seek(duration.multiply(timeSlider.getValue() / 100.0));
		       }
		    }
		});
		
		volumeSlider.valueProperty().addListener(new InvalidationListener() {
		    public void invalidated(Observable ov) {
		       if (volumeSlider.isValueChanging()) {
		           player.setVolume(volumeSlider.getValue() / 100.0);
		       }
		    }
		});
		
		player.currentTimeProperty().addListener(new InvalidationListener() 
        {
            public void invalidated(Observable ov) {
                updateValues();
            }
        });
	}

	/**
		 * This is pulled (and edited) from this guide:
		 * 
		 * http://docs.oracle.com/javafx/2/media/playercontrol.htm
		 */
	protected void updateValues() 
	{
		  if (timeSlider != null && volumeSlider != null) 
		  {
		     //Platform.runLater(new Runnable() 
		    // {
		      //  public void run() {
		          Duration currentTime = player.getCurrentTime();
		         // playTime.setText(formatTime(currentTime, duration));
		         // timeSlider.setDisable(duration.isUnknown());
		          if (!timeSlider.isDisabled() 
		                  && duration.greaterThan(Duration.ZERO) 
		                  && !timeSlider.isValueChanging()) 
		          {
		        	  timeSlider.setValue(currentTime.divide(duration).toMillis()
		                      * 100.0);
		          }
		          if (!volumeSlider.isValueChanging()) 
		          {
		            volumeSlider.setValue((int)Math.round(player.getVolume() 
		                  * 100));
		          }
		    //    }
		    // });
		  }
		  
	}
	
	public void play(ActionEvent pl)
	{
		if (player != null) 
			{
				player.play(); 
				player.setRate(1.0);
				duration = player.getMedia().getDuration();
				updateValues();
			}
		
		//System.out.println("Play pressed");
	}
	
	public void slow(ActionEvent rw)
	{
		if (player != null) player.setRate(0.5); 
		
		//System.out.println("Slow pressed");
	}
	
	public void fastForward(ActionEvent pl)
	{
		if (player != null) player.setRate(3.0); 
		
		//System.out.println("FF pressed");
	}
	
	public void pause(ActionEvent pl)
	{
		if (player != null) player.pause(); 
		
		//System.out.println("Pause pressed");
	}
	public void stop(ActionEvent pl)
	{
		if (player != null) player.stop(); 
		
		//System.out.println("Stop pressed");
	}
	
	
	public void updateMedia()
	{
		// Update the View's index
		currentIndex = model.index;
		
		player = new MediaPlayer(model.mediaCache[currentIndex]);
		mView.setMediaPlayer(player);
	}
	
	@Override
	public void updated() {
		//Updates if model's index has changed
		if (currentIndex != model.index)
			updateMedia();
		// Updates in the case that this is the first time anything was loaded
		if (model.mediaCache[0] == null )
			updateMedia();
	}


	public void openDirectory(ActionEvent od)
	{
		JFileChooser fileChooser = new JFileChooser();
		//only directories
		fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		// User choose a directory
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
		{				
			File dir = fileChooser.getSelectedFile();
			model.setDir(dir);
			//model.printFileNames();
		}	
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
