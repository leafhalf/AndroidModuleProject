package com.example.Media;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.example.Media.MediaPlayerHelper.ILinCompletedListen;
import com.example.Media.bean.MediaInfo;
import com.example.Utils.MediaUtils;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;

;
/**
 * 
 * @author Administrator
 * 
 */
public class MediaControl implements ILinCompletedListen{

	private static String TAG = "MediaControl";

	private static List<? extends MediaInfo> mInfos;
	private static MediaControl mediaControl;
	private static MediaPlayer mPlayer;
	private static Context mContext;
	public static int CUR_LOCATION =0;
	private static int RANDOM_NUM;

	// LOOP MODE
	public static final int PLAY_SINGLE_LOOP = 0;
	public static final int PLAY_ORDER = 1;
	public static final int PLAY_ORDER_LOOP = 2;
	public static final int PLAY_RANDOM_LOOP = 3;
	public static int CURRENT_PLAY_MODE = 0;

	public static MediaControl getInstance() {
		if (mediaControl != null)
			return mediaControl;
		return null;
	}

	public static void initMediaPlay(Context context,
			List<? extends MediaInfo> playlist) {
		if (mediaControl == null)
			mediaControl = new MediaControl();
		if (mPlayer == null)
			mPlayer = new MediaPlayer();
		MediaPlayerHelper.setPlayLoop(PLAY_SINGLE_LOOP,getInstance());
		MediaPlayerHelper.LinbufferingUpdate(mPlayer);
		MediaPlayerHelper.LinError(mPlayer);
		MediaPlayerHelper.LinplaybackComplete(mPlayer);
		mContext = context;
		setPlayList(playlist);
	}

	private static void setPlayList(List<? extends MediaInfo> playlist) {
		mInfos = playlist;
	}

	public static List<? extends MediaInfo> getmInfos() {
		return mInfos;
	}

	public static void Play(int location) {
		try {
			CUR_LOCATION = location;
			if (mPlayer == null) {
				Log.i(TAG, "can not init the mediaplay");
				return;
			}
			if (mInfos != null && mInfos.size() > 0) {
				if (location > mInfos.size()) {
					Log.i(TAG, "current play is not exist");
					return;
				} else {
					if (MediaPlayerHelper.CURRENT_MEDIA_STATE != MediaPlayerHelper.MEDIA_STATE_PAUSED) {
						switch (mInfos.get(location).getCUR_TYPE()) {
						case MediaInfo.TYPE_URI:
							MediaPlayerHelper.LinsetDataSource(mContext,
									mPlayer,
									Uri.parse(mInfos.get(location).getDATA()),
									mInfos.get(location).getHEADER());
							break;
						case MediaInfo.TYPE_ID:
							MediaPlayerHelper.LinsetDataSource(mContext,
									mPlayer, Integer.parseInt(mInfos.get(
											location).getDATA()));
							break;
						default:
							break;
						}
						MediaPlayerHelper.LinprepareAsync(mPlayer);
						mPlayer.setOnPreparedListener(new OnPreparedListener() {

							@Override
							public void onPrepared(MediaPlayer mp) {
								// TODO Auto-generated method stub
								MediaPlayerHelper.Linstart(mPlayer);
							}
						});
					} else {
						MediaPlayerHelper.Linstart(mPlayer);    
					}
				}
			} else {
				Log.i(TAG, "can not play an empty play list");
			}
		} catch (Exception e) {
			Log.i(TAG, "start error:" + e.getMessage());
		}
	}

	public static int Pause() {
		try {
			if (mPlayer != null) {
				MediaPlayerHelper.Linpause(mPlayer);
				return mPlayer.getCurrentPosition();
			}
		} catch (Exception e) {
			Log.i(TAG, "pause error:" + e.getMessage());

		}
		return 0;
	}

	public static boolean Next() {
		try {
			if ((CUR_LOCATION + 1) <= mInfos.size()) {
				CUR_LOCATION++;
			} else {
				CUR_LOCATION = 0;
			}
			if (mPlayer != null) {
				Play(CUR_LOCATION);
				return true;
			}
		} catch (Exception e) {
			Log.i(TAG, "Next error:" + e.getMessage());
		}
		return false;
	}

	public static boolean Previous() {
		try {
			if ((CUR_LOCATION - 1) >= 0) {
				CUR_LOCATION--;
			} else {
				CUR_LOCATION = mInfos.size()-1;
			}
			if (mPlayer != null) {
				Play(CUR_LOCATION);
				return true;
			}
		} catch (Exception e) {
			Log.i(TAG, "Previous error:" + e.getMessage());
		}
		return false;
	}

	public static void setLoopMode(int LOOP_MODE) {
           
	}

	public static void Speed(int Location) {
		try {
			MediaPlayerHelper.LinseekTo(mPlayer, Location);
		} catch (Exception e) {
			Log.i(TAG, "Speed error:" + e.getMessage());
		}

	}

	public static void fallBack(int Location) {
		try {
			MediaPlayerHelper.LinseekTo(mPlayer, Location);
		} catch (Exception e) {
			Log.i(TAG, "fallBack error:" + e.getMessage());
		}
	}

	public static void destoryMediaplay() {
		try {
			MediaPlayerHelper.Linrelease(mPlayer);
		} catch (Exception e) {
			Log.i(TAG, "destoryMediaplay error:" + e.getMessage());
		}
	}
	
	public static int getCurrentLocation(){
		return CUR_LOCATION;
	}

	@Override
	public void OnLinCompleted(MediaPlayer mPlayer, int LOOP_MODE) {
		// TODO Auto-generated method stub
		switch (LOOP_MODE) {
		case PLAY_SINGLE_LOOP:
			Play(CUR_LOCATION);
			break;
		case PLAY_ORDER:
			if((CUR_LOCATION+1)<(mInfos.size())){
				Next();
			}else{
				Log.i(TAG,"PLAY_ORDER FINISH");
			}
			break;
		case PLAY_ORDER_LOOP:
			Next();
			break;
		case PLAY_RANDOM_LOOP:
			Play(MediaUtils.getRandom(mInfos.size()));
			break;
		default:
			break;
		}
	}


}
