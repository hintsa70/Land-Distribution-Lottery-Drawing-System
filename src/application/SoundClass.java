package application;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class SoundClass {
	File fileName=null;
	Media me=null;
	MediaPlayer mp=null;
	
	public SoundClass(){
		fileName=new File("sounds\\sound.mp3");
	 	me=new Media(fileName.toURI().toString());
	 	mp=new MediaPlayer(me);
                
                play();
	}
	
	public void play(){
		mp.play();
	}
	public void stop(){
		mp.stop();
	}
}
