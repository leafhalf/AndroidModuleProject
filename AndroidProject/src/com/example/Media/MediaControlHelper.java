package com.example.Media;

import java.io.IOException;
import java.util.Map;

import android.media.MediaPlayer;
import android.util.Log;

public class MediaControlHelper implements IMediaControl {
	
	private String TAG="MediaControlHelper";
	
    private static int MEDIA_STATE_IDLE=0;
    private static int MEDIA_STATE_INITIALIZED=1;
    private static int MEDIA_STATE_PREPARING=2;
    private static int MEDIA_STATE_PREPARED=3;
    private static int MEDIA_STATE_STARTED=4;
    private static int MEDIA_STATE_PAUSED=5;
    private static int MEDIA_STATE_STOPPED=6;
    private static int MEDIA_STATE_PLAYBACKCOMPLETED=7;
    private static int MEDIA_STATE_ERROR;
    private static int CURRENT_MEDIA_STATE=-1;
	
	@Override
	public void Linreset(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		mPlayer.reset();
		CURRENT_MEDIA_STATE = MEDIA_STATE_IDLE;
	}

	@Override
	public void LinsetDataSource(MediaPlayer mPlayer, String path,
			Map<String, String> head) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Linprepare(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		try {
			mPlayer.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i(TAG, e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i(TAG, e.getMessage());
		}

	}

	@Override
	public void LinprepareAsync(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		mPlayer.prepareAsync();
	}

	@Override
	public void Linstart(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void LinseekTo(MediaPlayer mPlayer, int location) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Linpause(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Linstop(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void Linrelease(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		mPlayer.release();
		mPlayer = null;
	}

	@Override
	public int LingetCurrentPosition(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		return mPlayer.getCurrentPosition();
	}

	@Override
	public int LingetDuration(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		return mPlayer.getDuration();
	}
}
