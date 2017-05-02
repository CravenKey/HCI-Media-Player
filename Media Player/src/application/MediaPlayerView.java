package application;

import java.io.File;
import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class MediaPlayerView {

	private Media media;
	private MediaPlayer mediaPlayer;
	private MediaView mediaView;
	
	public MediaPlayerView(){
		
	}
	
	public MediaView createView(File filePath){
		File file = new File(filePath.getAbsolutePath());
		String path = file.toURI().toASCIIString();
		
		media = new Media(path);
		
		// Create the player and set to play automatically.
		mediaPlayer = new MediaPlayer(media);
		mediaView = new MediaView(mediaPlayer);
			     
		mediaView.setX(100);
		mediaView.setY(250);
		
		mediaView.setFitWidth(800);
		mediaView.setFitHeight(800);
		
		mediaPlayer.setAutoPlay(true);
		
		return mediaView;
	}
	
	public MediaPlayer createNewMedia(File path){
		File file = new File(path.getAbsolutePath());
		String filePath = file.toURI().toASCIIString();
		
		Media media = new Media(filePath);
		MediaPlayer medPlayer = new MediaPlayer(media);
		
		return medPlayer;
	}
	
	public MediaPlayer getMediaPlayer(){
		return this.mediaPlayer;
	}
	
	public Media getMedia(){
		return this.media;
	}
	
	public void setMediaPlayer(MediaPlayer player){
		this.mediaPlayer = player;
	}
	
	public void setMedia(Media med){
		this.media = med;
	}
	
	public String formatPath(String filePath){
		
		char a = '/';
		
		char b = '\\';
		
		filePath = filePath.replace(b, a);
		
		return filePath;
	}
	
}
