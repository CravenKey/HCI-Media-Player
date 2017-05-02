package model;


import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.scene.media.Media;

public class Model 
{
	public File[] files;
	public Media[] mediaCache;
	public int index;
	
	public Model()
	{
		// Initialize everything to 0
		files = new File[0];
		mediaCache = new Media[0];
		index = 0;
	}
	
	
	
	public void setMedia(int index)
	{
		//don't do anything if a directory with usable files hasn't been selected
				if( files == null || files.length == 0 ) {
					return;
				}
				
		// Go to the end if the index has moved back past the beginning
		if (index < 0)
			index += files.length;
		
		if (index >= files.length )
			index = (index % files.length);
		
		// save the new index
		this.index = index;
		
		//if the file hasn't been read do so
		if( mediaCache[index] == null ) {
			try {
				
				Media m = new Media(files[index].getAbsolutePath());
				mediaCache[index] = m;
			}
			catch( final Exception ioe ) {
				System.err.println( "There was an error reading " + files[index] );
			}
		}
		
		notifyListeners();
	}
	
	
/*	
	public void printFileNames()
	{
		for(File file : files)
		{
			System.out.println(file.getName());
		}
	}
*/
	
	public void setDir(final File dir)
	{
		// 
		File[] directoryFiles = dir.listFiles();
		// Clears the validFiles vector
		Vector<File> validFiles = new Vector<File>();
		
		FileNameExtensionFilter ff = new FileNameExtensionFilter("flac" ,"m4a", "m4p", "mp3", "wav", "alac", "webm", "flv", "avi", "mp4", "wmv", "rm", "amv", "m4v", "mpg", "mpeg", "m2v");

		// Check if files are accepted media files
		for ( File file : directoryFiles ) 
		{
		    if(	ff.accept(file))
		    {
		    	validFiles.addElement(file);
		    }
		}
		
		this.files = validFiles.toArray(new File[validFiles.size()]);
		this.mediaCache = new Media[ validFiles.size() ];
	
		setMedia(0);
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