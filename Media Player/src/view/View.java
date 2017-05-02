package view;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import model.Listener;
import model.Model;




public class View implements Listener, Initializable

{
	public int currentIndex;
	private final Model model;
	
	@FXML private MediaView mView;
	@FXML private ListView playlistView;
	
	
	MediaPlayer player;
	//Media media;
	
	public View(final Model model)
	{
		this.model = model;
	}
	
/*
	private void init()
	{
		
		
		//media = new Media(new File(path).toURI().toString());
		//player = new MediaPlayer(model.);
		mView.setMediaPlayer(player);
		player.setAutoPlay(true);
		playlistView.setVisible(false);
		
	}*/
	
	
	public void updateMedia()
	{
		// Update the View's index
		currentIndex = model.index;
		
		player = new MediaPlayer(model.mediaCache[currentIndex]);
		mView.setMediaPlayer(player);
	}
	
	@Override
	public void updated() {
		if (currentIndex != model.index)
			updateMedia();
		// Updates in the case that this is the first time anything was loaded
		if (model.mediaCache[0] == null )
			updateMedia();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	
}
