package com.example.Media;

import java.util.Map;

import android.media.MediaPlayer;

/**
 * Interface about playback operation it is around by the playback
 * state:Idle,Initialized
 * ,Prepared,Started,Paused,Stopped,PlaybackCompleted,Error.
 * 
 * @author Lin
 * 
 */
public interface IMediaControl {
	/**
	 * reset the playback,it will make the playback come into the Idle state;
	 * this API could be used in any state.
	 * 
	 * @param mPlayer
	 */
	void Linreset(MediaPlayer mPlayer);

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
	void LinsetDataSource(MediaPlayer mPlayer, String path,
			Map<String, String> head);

	/**
	 * it is an block API which able take much time to return,if it,this may
	 * take a noresponding that the system will kill it.it also be used in an
	 * thread for safe.after it,the playback will come into the Prepared
	 * state.this API only could be used in Initialized and Stopped state.
	 * 
	 * @param mPlayer
	 */
	void Linprepare(MediaPlayer mPlayer);

	/**
	 * an asynchronous API,which is non-blocking.it will run in a thread,it will
	 * come back right now if be invoked. then it will may the playback come
	 * into the Preparing state and then the Prepared state.the developer could
	 * use the OnPreparedListen to listen it.
	 * 
	 * @param mPlayer
	 */
	void LinprepareAsync(MediaPlayer mPlayer);

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
	void Linstart(MediaPlayer mPlayer);

	/**
	 * Seeks to specified time position,the developer could set
	 * OnSeekCompleteListen to listen,this API could be used in
	 * Prepared,Started, Paused,Stopped or PlaybackCompleted state.
	 * 
	 * @param mPlayer
	 * @param location
	 */
	void LinseekTo(MediaPlayer mPlayer, int location);

	/**
	 * Pauses playback. Call start() to resume.it will make the playback come
	 * into the Pasued state. this API could be used in Started, Paused or
	 * PlaybackCompleted state.
	 * 
	 * @param mPlayer
	 */
	void Linpause(MediaPlayer mPlayer);

	/**
	 * Stops playback after playback has been stopped or paused,it will make the
	 * playback come into the Stoped state,it could be used in Prepared,
	 * Started, Stopped, Paused or PlaybackCompleted state.could not be used in
	 * Idle, Initialized or Error state
	 * 
	 * @param mPlayer
	 */
	void Linstop(MediaPlayer mPlayer);

	/**
	 * end the playback and could be used in any state this API could be used in
	 * any state
	 * 
	 * @param mPlayer
	 */
	void Linrelease(MediaPlayer mPlayer);

	/**
	 * Gets the current playback position.it only could not be used in Error
	 * state.
	 * 
	 * @return the current position in milliseconds
	 */
	int LingetCurrentPosition(MediaPlayer mPlayer);

	/**
	 * Gets the duration of the file.it only could not be used in Idle,
	 * Initialized or Error state
	 * 
	 * @param mPlayer
	 * @return the duration in milliseconds, if no duration is available (for
	 *         example, if streaming live content), -1 is returned.
	 */
	int LingetDuration(MediaPlayer mPlayer);

}
