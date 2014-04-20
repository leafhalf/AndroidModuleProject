package com.example.Media;

import java.io.IOException;
import java.util.Map;

import android.media.MediaPlayer;
import android.util.Log;
import android.media.MediaPlayer.OnPreparedListener;;

public class MediaControl extends MediaPlayer {
	
	private String TAG="MediaControl";
	
    
    private static  MediaControl mediaControl;
    private static MediaPlayer mediaPlayer;
    
    public static MediaControl getInstance(){
    	if(mediaControl!=null)
    		return mediaControl;
    	return null;
    }
    
    public static void initMediaPlay(){
    	if(mediaControl==null)
    		mediaControl=new MediaControl();
    	if(mediaPlayer==null)
    		mediaPlayer=new MediaPlayer();
    }
    
    public static void Play(){
    	
    }
    
    public static void Pause(){
    	
    }
    
    public static void Next(){
    	
    }
    
    public static void Previous(){
    	
    }
    
    public static void setLoopMode(int LOOP_MODE){
    	
    }
    
}
