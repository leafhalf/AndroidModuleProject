package com.example.Media;

import java.io.IOException;
import java.util.Map;

import com.example.Media.Interface.IMediaStateListen;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.net.Uri;
import android.util.Log;

/**
 * Interface about playback operation it is around by the playback
 * state:Idle,Initialized
 * ,Prepared,Started,Paused,Stopped,PlaybackCompleted,Error.
 * 
 * @author Lin
 * 
 */
public class MediaPlayerHelper {

	private static String TAG = "MediaControlHelper";

	public static final int MEDIA_STATE_IDLE = 0;
	public static final int MEDIA_STATE_INITIALIZED = 1;
	public static final int MEDIA_STATE_PREPARING = 2;
	public static final int MEDIA_STATE_PREPARED = 3;
	public static final int MEDIA_STATE_STARTED = 4;
	public static final int MEDIA_STATE_PAUSED = 5;
	public static final int MEDIA_STATE_STOPPED = 6;
	public static final int MEDIA_STATE_PLAYBACKCOMPLETED = 7;
	public static final int MEDIA_STATE_ERROR = 8;
	public static int CURRENT_MEDIA_STATE = -1;
	
	private static int CURRENT_PLAY_MODE=0;

	private static IMediaStateListen mStateListen;
	private static ILinCompletedListen mCompletedListen;

	public static IMediaStateListen getmStateListen() {
		return mStateListen;
	}

	public static void setmStateListen(IMediaStateListen stateListen) {
		mStateListen = stateListen;
	}

	/**
	 * reset the playback,it will make the playback come into the Idle state;
	 * this API could be used in any state.
	 * 
	 * @param mPlayer
	 */
	public static void Linreset(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		mPlayer.reset();
		CURRENT_MEDIA_STATE = MEDIA_STATE_IDLE;
		Log.i(TAG, "mediaplay idel");
		if (mStateListen != null) {
			mStateListen.onLIdel(mPlayer);
		}
	}

	/**
	 * set the playback data source,it will make the playback come into the
	 * Initialized state; this API only could be used in Idle state,cound not be
	 * used in other state,such as Initialized,
	 * Prepared,Started,Paused,Stopped,PlaybackCompleted or Error state.
	 * 
	 * @param mPlayer
	 * @param path
	 *            the path where is the mediaplay data source
	 * @param head
	 *            when the path is an http or https request,set the request
	 *            parameter,if there is no data input,set it null
	 */
	public static void LinsetDataSource(Context context, MediaPlayer mPlayer,
			Uri uri, Map<String, String> headers) {
		// TODO Auto-generated method stub
		try {

			if (android.os.Build.VERSION.SDK_INT >= 14) {
				mPlayer.setDataSource(context, uri, headers);
			} else {
				mPlayer.setDataSource(context, uri);
			}
			CURRENT_MEDIA_STATE = MEDIA_STATE_INITIALIZED;
			Log.i(TAG, "mediaplay initialized");
			if (mStateListen != null) {
				mStateListen.onLInitialized(mPlayer);
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i(TAG, "LinsetDataSource failed:" + e.getMessage());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i(TAG, "LinsetDataSource failed:" + e.getMessage());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i(TAG, "LinsetDataSource failed:" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i(TAG, "LinsetDataSource failed:" + e.getMessage());
		}

	}

	public static void LinsetDataSource(Context context, MediaPlayer mPlayer,
			int resID) {
		// TODO Auto-generated method stub
		try {
			AssetFileDescriptor afd = context.getResources().openRawResourceFd(
					resID);
			if (afd == null)
				return;
			mPlayer.setDataSource(afd.getFileDescriptor(),
					afd.getStartOffset(), afd.getLength());
			CURRENT_MEDIA_STATE = MEDIA_STATE_INITIALIZED;
			afd.close();
		} catch (IOException ex) {
			Log.i(TAG, "LinsetDataSource failed:" + ex.getMessage());
			// fall through
		} catch (IllegalArgumentException ex) {
			Log.i(TAG, "LinsetDataSource failed:" + ex.getMessage());
			// fall through
		} catch (SecurityException ex) {
			Log.i(TAG, "LinsetDataSource failed:" + ex.getMessage());

		}

	}

	/**
	 * it is an block API which able take much time to return,if it,this may
	 * take a noresponding that the system will kill it.it also be used in an
	 * thread for safe.after it,the playback will come into the Prepared
	 * state.this API only could be used in Initialized and Stopped state.
	 * 
	 * @param mPlayer
	 */
	public static void Linprepare(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		try {
			mPlayer.setLooping(true);
			mPlayer.prepare();
			CURRENT_MEDIA_STATE = MEDIA_STATE_PREPARED;
			Log.i(TAG, "mediaplay prepared");
			if (mStateListen != null) {
				mStateListen.onLPrepared(mPlayer);
			}
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

	/**
	 * an asynchronous API,which is non-blocking.it will run in a thread,it will
	 * come back right now if be invoked. then it will may the playback come
	 * into the Preparing state and then the Prepared state.the developer could
	 * use the OnPreparedListen to listen it.
	 * 
	 * @param mPlayer
	 */
	public static void LinprepareAsync(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		mPlayer.prepareAsync();
		CURRENT_MEDIA_STATE = MEDIA_STATE_PREPARING;
		Log.i(TAG, "mediaplay preparing...");
		if (mStateListen != null) {
			mStateListen.onLPreparing(mPlayer);
			mPlayer.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer mp) {
					// TODO Auto-generated method stub
					CURRENT_MEDIA_STATE = MEDIA_STATE_PREPARED;
					Log.i(TAG, "mediaplay prepared");
					mStateListen.onLPrepared(mp);
				}
			});
		}
	}

	/**
	 * Starts or resumes playback. If playback had previously been paused,
	 * playback will continue from where it was paused. If playback had been
	 * stopped, or never started before, playback will start at the
	 * beginning.,it will make the playback come from the Started state to the
	 * PlaybackCompleted state,the developer could set the OnCompletionListen to
	 * listen. this API could be used in Prepared,Started, Paused or
	 * PlaybackCompleted state.
	 * 
	 * @param mPlayer
	 */
	public static void Linstart(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		mPlayer.start();
		CURRENT_MEDIA_STATE = MEDIA_STATE_STARTED;
		Log.i(TAG, "mediaplay started");
		if (mStateListen != null) {
			mStateListen.onLStarted(mPlayer);
		}
	}

	/**
	 * Seeks to specified time position,the developer could set
	 * OnSeekCompleteListen to listen,this API could be used in
	 * Prepared,Started, Paused,Stopped or PlaybackCompleted state.
	 * 
	 * @param mPlayer
	 * @param location
	 */
	public static void LinseekTo(MediaPlayer mPlayer, int location) {
		// TODO Auto-generated method stub
		mPlayer.seekTo(location);
		Log.i(TAG, "mediaplay begin seeking");
		mPlayer.setOnSeekCompleteListener(new OnSeekCompleteListener() {

			@Override
			public void onSeekComplete(MediaPlayer mp) {
				// TODO Auto-generated method stub
				Log.i(TAG, "mediaplay seek completed");
				if (mStateListen != null) {
					mStateListen.onLSeekComplete(mp);
				}
			}
		});
	}

	/**
	 * Pauses playback. Call start() to resume.it will make the playback come
	 * into the Pasued state. this API could be used in Started, Paused or
	 * PlaybackCompleted state.
	 * 
	 * @param mPlayer
	 */
	public static void Linpause(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		CURRENT_MEDIA_STATE = MEDIA_STATE_PAUSED;
		Log.i(TAG, "mediaplay catch an pause");
		if (mStateListen != null) {
			mStateListen.onLPaused(mPlayer);
		}
	}

	/**
	 * Stops playback after playback has been stopped or paused,it will make the
	 * playback come into the Stoped state,it could be used in Prepared,
	 * Started, Stopped, Paused or PlaybackCompleted state.could not be used in
	 * Idle, Initialized or Error state
	 * 
	 * @param mPlayer
	 */
	public static void Linstop(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		CURRENT_MEDIA_STATE = MEDIA_STATE_STOPPED;
		Log.i(TAG, "mediaplay stop");
		if (mStateListen != null) {
			mStateListen.onLStoped(mPlayer);
		}
	}

	/**
	 * Register a callback to be invoked when the end of a media source has been
	 * reached during playback.
	 * 
	 * @param mPlayer
	 */
	public static void LinplaybackComplete(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		try {
			mPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					CURRENT_MEDIA_STATE = MEDIA_STATE_PLAYBACKCOMPLETED;
					Log.i(TAG, "mediaplay play completed");
					if (mStateListen != null) {
						mStateListen.onLCompletion(mp);
						if (mCompletedListen != null) {
							mCompletedListen.OnLinCompleted(mp,CURRENT_PLAY_MODE);
						}
					}
				}
			});
		} catch (Exception e) {
           Log.i(TAG,"LinplaybackComplete error:"+e.getMessage());
		}
	}

	public interface ILinCompletedListen {
		void OnLinCompleted(MediaPlayer mPlayer,int LOOP_MODE);
	}

	/**
	 * Register a callback to be invoked when an error has happened during an
	 * asynchronous operation.
	 * 
	 * @param mPlayer
	 */
	public static void LinError(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		CURRENT_MEDIA_STATE = MEDIA_STATE_ERROR;
		mPlayer.setOnErrorListener(new OnErrorListener() {

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				// TODO Auto-generated method stub
				switch (what) {
				case 1: // MEDIA_ERROR_UNKNOWN
					Log.i(TAG,
							"mediaplay catch an error:Unspecified media player error.");
					break;
				case 100: // MEDIA_ERROR_SERVER_DIED:
					Log.i(TAG, "mediaplay catch an error:Media server died.");
					break;
				default:
					break;
				}
				switch (extra) {
				case -1004: // MEDIA_ERROR_IO:
					Log.i(TAG,
							"mediaplay catch an error:File or network related operation errors.");
					break;
				case -1007: // MEDIA_ERROR_MALFORMED:
					Log.i(TAG,
							"mediaplay catch an error:Bitstream is not conforming to the related coding standard or file spec.");
					break;
				case -1010:// MEDIA_ERROR_UNSUPPORTED:
					Log.i(TAG,
							"mediaplay catch an error:Bitstream is conforming to the related coding standard or file spec.");
					break;
				case -110: // MEDIA_ERROR_TIMED_OUT:
					Log.i(TAG,
							"mediaplay catch an error:Some operation takes too long to complete, usually more than 3-5 seconds.");
					break;
				default:
					break;
				}
				if (mStateListen != null) {
					mStateListen.onLError(mp, what, extra);
				}
				return true;
			}
		});
	}

	/**
	 * Register a callback to be invoked when the status of a network stream's
	 * buffer has changed
	 * 
	 * @param mPlayer
	 */
	public static void LinbufferingUpdate(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		mPlayer.setOnBufferingUpdateListener(new OnBufferingUpdateListener() {

			@Override
			public void onBufferingUpdate(MediaPlayer mp, int percent) {
				// TODO Auto-generated method stub
				Log.i(TAG, "mediaplay buffering:" + String.valueOf(percent)
						+ "%");
				if (mStateListen != null) {
					mStateListen.onLBufferingUpdate(mp, percent);
				}
			}
		});
	}

	/**
	 * end the playback and could be used in any state this API could be used in
	 * any state
	 * 
	 * @param mPlayer
	 */
	public static void Linrelease(MediaPlayer mPlayer) {
		// TODO Auto-generated method stub
		Log.i(TAG, "mediaplay release");
		mPlayer.release();
		mPlayer = null;
		if (mStateListen != null) {
			mStateListen.OnLEnd();
		}
	}

	public static void setPlayLoop(int LOOP_MODE,
			ILinCompletedListen lCompletedListen) {
		CURRENT_PLAY_MODE=LOOP_MODE;
		mCompletedListen = lCompletedListen;
	}

}
