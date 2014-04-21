package com.example.Media;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.example.Media.bean.MediaInfo;

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
public class MediaControl {

	private static String TAG = "MediaControl";

	private static List<? extends MediaInfo> mInfos;
	private static MediaControl mediaControl;
	private static MediaPlayer mPlayer;
	private static Context mContext;
	private static int CUR_LOCATION = -1;

	// LOOP MODE
	private static int MODE_SINGLE_LOOP = 0;
	private static int MODE_ORDER_LOOP = 1;
	private static int MODE_RANDOM_LOOP = 2;

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
		LinMediaPlayerHelper.LinbufferingUpdate(mPlayer);
		LinMediaPlayerHelper.LinError(mPlayer);
		LinMediaPlayerHelper.LinplaybackComplete(mPlayer);
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
					switch (mInfos.get(location).getCUR_TYPE()) {
					case MediaInfo.TYPE_URI:
						LinMediaPlayerHelper.LinsetDataSource(mContext,
								mPlayer,
								Uri.parse(mInfos.get(location).getDATA()),
								mInfos.get(location).getHEADER());
						break;
					case MediaInfo.TYPE_ID:
						LinMediaPlayerHelper.LinsetDataSource(mContext,
								mPlayer, Integer.parseInt(mInfos.get(location)
										.getDATA()));
						break;
					default:
						break;
					}
					LinMediaPlayerHelper.LinprepareAsync(mPlayer);
					mPlayer.setOnPreparedListener(new OnPreparedListener() {

						@Override
						public void onPrepared(MediaPlayer mp) {
							// TODO Auto-generated method stub
							LinMediaPlayerHelper.Linstart(mPlayer);
						}
					});
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
			if (mPlayer == null) {
				LinMediaPlayerHelper.Linpause(mPlayer);
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
				LinMediaPlayerHelper.Linreset(mPlayer);
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
				CUR_LOCATION = mInfos.size();
			}
			if (mPlayer != null) {
				LinMediaPlayerHelper.Linreset(mPlayer);
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
			LinMediaPlayerHelper.LinseekTo(mPlayer, Location);
		} catch (Exception e) {
			Log.i(TAG, "Speed error:" + e.getMessage());
		}

	}

	public static void fallBack(int Location) {
		try {
			LinMediaPlayerHelper.LinseekTo(mPlayer, Location);
		} catch (Exception e) {
			Log.i(TAG, "fallBack error:" + e.getMessage());
		}
	}

	public static void destoryMediaplay() {
		try {
			LinMediaPlayerHelper.Linrelease(mPlayer);
		} catch (Exception e) {
			Log.i(TAG, "destoryMediaplay error:" + e.getMessage());
		}
	}

}
