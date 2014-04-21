package com.example.Media;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;

public class MediaStateListen implements OnPreparedListener,
		OnVideoSizeChangedListener, OnSeekCompleteListener,
		OnCompletionListener, OnBufferingUpdateListener, OnErrorListener {

	private MediaPlayer mPlayer;
	private IMediaStateListen mIMediaStateListen;

	public MediaStateListen(MediaPlayer player,
			IMediaStateListen iMediaStateListen) {
		super();
		this.mIMediaStateListen = iMediaStateListen;
		this.mPlayer = player;
		if (mPlayer != null) {
			mPlayer.setOnPreparedListener(this);
			mPlayer.setOnVideoSizeChangedListener(this);
			mPlayer.setOnSeekCompleteListener(this);
			mPlayer.setOnCompletionListener(this);
			mPlayer.setOnBufferingUpdateListener(this);
			mPlayer.setOnErrorListener(this);
		}
	}

	@Override
	public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
		// TODO Auto-generated method stub
		this.mIMediaStateListen.onLVideoSizeChanged(mp, width, height);
	}

	public void onPreparing(MediaPlayer mp) {
		this.mIMediaStateListen.onLPreparing(mp);
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		this.mIMediaStateListen.onLPrepared(mp);
	}

	public void onStarted(MediaPlayer mp) {
		this.mIMediaStateListen.onLStarted(mp);
	}

	public void onPaused(MediaPlayer mp) {
		this.mIMediaStateListen.onLPaused(mp);
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		return this.mIMediaStateListen.onLError(mp, what, extra);
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		// TODO Auto-generated method stub
		this.mIMediaStateListen.onLBufferingUpdate(mp, percent);
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		this.mIMediaStateListen.onLCompletion(mp);
	}

	public  void onStoped(MediaPlayer mp){
		this.mIMediaStateListen.onLStoped(mp);
	}

	public  void OnEnd(){
		this.mIMediaStateListen.OnLEnd();
	}

	@Override
	public void onSeekComplete(MediaPlayer mp) {
		// TODO Auto-generated method stub
		this.mIMediaStateListen.onLSeekComplete(mp);
	}

}
