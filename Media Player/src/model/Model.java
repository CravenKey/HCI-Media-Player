
package model;

import java.io.File;
import java.util.ArrayList;

public class Model {


	private Listener mouseListener;
	ArrayList<File> files = new ArrayList();
	
	
	public Model() {
			
	}
	
	public void addFile(File fileName){
		this.files.add(fileName);
	}
	
	public File getFile(int i){
		return this.files.get(i);
	}
	
	public int getSize(){
		return this.files.size();
	}
	
	public void setListener(final Listener listener )
	{
		this.mouseListener = listener;
	}
	
}
	
	
	
	
