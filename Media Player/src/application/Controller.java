package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import Model.Model;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label leftDurationTime;

    @FXML
    private Label rightDurationTime;
    
    @FXML
    private Button fastForwardButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;
    

    @FXML
    private Button rewindButton;

    @FXML
    private Button stopButton;

    @FXML
    private Slider volumeSlider;
    
    @FXML
    private Slider seekSlider;

    @FXML
    private MenuItem aboutItem;

    @FXML
    private MenuItem closeItem;

    @FXML
    private MenuItem deleteItem;
    
    @FXML
    private MenuItem importItem;
    
    @FXML
    private RadioButton muteButton;
    
    @FXML
    private ListView<String> fileList;
    
    @FXML
    private VBox mediaView;
      
    private Stage primaryStage;
    private MediaView v = new MediaView();
    private MediaPlayerView medV = new MediaPlayerView();
    
    private MediaPlayer currentMedia;
    
    private FileChooser fileChooser = new FileChooser();
    private Model model = new Model();
    
    ObservableList<String> fileNames;
    private List<String> ls = new ArrayList<String>();
    
    private boolean isImported = false;
    
    private DirectoryChooser dc;
    private File selectedDir;
    private File[] files;
    
    @FXML
    void initialize() {
        assert fastForwardButton != null : "fx:id=\"fastForwardButton\" was not injected: check your FXML file 'Scene.fxml'.";
        assert pauseButton != null : "fx:id=\"pauseButton\" was not injected: check your FXML file 'Scene.fxml'.";
        assert playButton != null : "fx:id=\"playButton\" was not injected: check your FXML file 'Scene.fxml'.";
        assert rewindButton != null : "fx:id=\"rewindButton\" was not injected: check your FXML file 'Scene.fxml'.";
        assert stopButton != null : "fx:id=\"stopButton\" was not injected: check your FXML file 'Scene.fxml'.";
        assert volumeSlider != null : "fx:id=\"volumeSlider\" was not injected: check your FXML file 'Scene.fxml'.";

        importItem.setOnAction(e ->{
        	dc = new DirectoryChooser();
        	dc.setTitle("Import Media");
        	selectedDir = dc.showDialog(this.primaryStage);
        	
        	model.readMediaToBuff(selectedDir);
        	
        	files = model.getFilesInDir();
        	
        	for(int i = 0; i < files.length; i++){
        		this.ls.add(files[i].getName());
        		System.out.println("File: " + files[i].getPath());
        	}
        	
        	fileNames = FXCollections.observableArrayList(ls);
        	fileList.setItems(fileNames);
        	
        	System.out.println(files[0].getPath());

        	v = medV.createView(files[0]);
        	
        	v.getMediaPlayer().setOnReady(new Runnable() {

				@Override
				public void run() {
					
					System.out.println("Duration: " + (Double.toString(v.getMediaPlayer().getTotalDuration().toSeconds() / 60.0)));
					rightDurationTime.setText(    (Double.toString(v.getMediaPlayer().getTotalDuration().toMinutes() / 60.0)  ));
				}
        		
        	});
            mediaView.getChildren().add(v);

            this.isImported = true;
        	System.out.println("Directory: " + selectedDir.getAbsolutePath());
        });
        
        fileList.setOnMouseClicked(e -> {
        	System.out.println("Item: " + fileList.getSelectionModel().getSelectedItem());
  
        	v.getMediaPlayer().stop();
        	v.setMediaPlayer(this.medV.createNewMedia(files[fileList.getSelectionModel().getSelectedIndex()]));
        	v.getMediaPlayer().play();
        });
        
        aboutItem.setOnAction(e ->{
        	System.out.println("About being pushed!");
        });
        
        closeItem.setOnAction(e ->{
        	System.exit(0);
        });
        
        deleteItem.setOnAction(e ->{
        	System.out.println("Delete being pushed!");
        });
        
        pauseButton.setOnAction(e ->{
        	v.getMediaPlayer().pause();
        	System.out.println("Pause Being Pushed!");
        });
        
       fastForwardButton.setOnAction(e ->{
    	   double currRate = v.getMediaPlayer().getRate();
    	   currRate += 1.0;
    	   
    	   v.getMediaPlayer().setRate(currRate);
        	System.out.println("FF Being Pushed!");
        });
        
        playButton.setOnAction(e ->{
        	v.getMediaPlayer().setRate(1.0);
        	v.getMediaPlayer().play();
        	System.out.println("Play Being Pushed!");
        });
        
        rewindButton.setOnAction(e ->{
        	Duration skipTo = v.getMediaPlayer().getCurrentTime().subtract(new Duration(1.0));
        	v.getMediaPlayer().seek(skipTo);
        	System.out.println("RW Being Pushed!");
        });
        
        stopButton.setOnAction(e ->{
        	v.getMediaPlayer().stop();
        	System.out.println("Stop Being Pushed!");
        });
        
        muteButton.setOnAction(e -> {
        	if(muteButton.isSelected() == true){
        		v.getMediaPlayer().setMute(true);
        	}else
        		v.getMediaPlayer().setMute(false);
        	System.out.println("Mute Button");
        });
        
        if(isImported == true){
        seekSlider.setMax(v.getMediaPlayer().getTotalDuration().toSeconds());
        System.out.println("Slider Max: " + seekSlider.getMax());
        }
        
        seekSlider.setMin(0.0);
        seekSlider.setOnDragDetected(e -> {
        	seekSlider.setValue(v.getMediaPlayer().getCurrentTime().toSeconds());
        	v.getMediaPlayer().seek(new Duration(seekSlider.getValue()));
        });
        
        volumeSlider.setMax(1.0);
    	volumeSlider.setMin(0.0);
    	volumeSlider.setValue(1.0);

        volumeSlider.setOnDragDetected(e ->{
        	v.getMediaPlayer().volumeProperty().bindBidirectional(volumeSlider.valueProperty());
        	System.out.println("Being Dragged!");
        });
        
        

    }

}