package com.example.Media.Interface;

import android.media.MediaPlayer;
/**
 * 
 * @author Administrator
 *
 */
public interface IMediaStateListen {
	void onLVideoSizeChanged(MediaPlayer mPlayer, int width, int height);
	
	void onLIdel(MediaPlayer mPlayer);
	
	void onLInitialized(MediaPlayer mPlayer);
	
	void onLPreparing(MediaPlayer mPlayer);

	void onLPrepared(MediaPlayer mPlayer);
	
	void onLStarted(MediaPlayer mPlayer);
	
	void onLPaused(MediaPlayer mPlayer);
	
	boolean onLError(MediaPlayer mPlayer, int what, int extra);

	void onLBufferingUpdate(MediaPlayer mPlayer, int percent);

	void onLCompletion(MediaPlayer mPlayer);
	
	void onLStoped(MediaPlayer mPlayer);
	
	void OnLEnd();

	void onLSeekComplete(MediaPlayer mPlayer);
}
