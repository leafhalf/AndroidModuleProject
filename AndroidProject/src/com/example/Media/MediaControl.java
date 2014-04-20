package com.example.Media;

import android.media.MediaPlayer;

public class MediaControl extends MediaPlayer {
    public void start(){
    	MediaPlayer mediaPlayer=new MediaPlayer();
    	mediaPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {
			
			@Override
			public void onBufferingUpdate(MediaPlayer mp, int percent) {
				// TODO Auto-generated method stub
				
			}
		});
    }
}
