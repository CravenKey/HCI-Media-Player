package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.Media;

public class Model{

	/**An array for all of the files in the directory selected*/
	private File[] filesInDirr = null;
	/**The path file that was selected*/
	private File pathFile = null;
	
	private boolean isImported = false;
	
	private Media currFile;
	private Media[] mediaFiles = null;
	private Media currentMedia = null;
	
	private int numFiles = 0;
	
	public Model(){
		
		
	}
	
	public void readMediaToBuff(File path){
		
		filesInDirr = path.listFiles();					//Get files listed
		numFiles = filesInDirr.length;	
	}
	
	public void setCurrentMedia(Media mediaFile){
		this.currentMedia = mediaFile;
	}
	
	public File[] getFilesInDir(){
		return this.filesInDirr;
	}

	
private List<Listener> listeners = new ArrayList<Listener>();
	
	public void addListener( final Listener listener )
	{
		listeners.add( listener );
	}
	
	private void notifyListeners()
	{
		for( final Listener listener : listeners ) {
			listener.updated();
		}
	}
	
	
}
