package com.example.androidproject;

import java.util.ArrayList;
import java.util.List;

import com.example.Media.MediaControl;
import com.example.Media.bean.MediaInfo;
import com.example.Utils.ApplicationUtils;
import com.example.Utils.ImageUtils;
import com.example.customview.CustomDialog;
import com.example.customview.CustomDialog.OnCustomDialogListen;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	private Button mbtn;
	private Context mContext;
	private Button mbtn2,mbtn3,mbtn4,mbtn5;
	private ImageView mView;
	private TextView mText;
	private String TAG = "MainActivity";
	private List<MediaInfo> mlists;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mbtn = (Button) findViewById(R.id.click);
		mbtn2 = (Button) findViewById(R.id.click2);
		mbtn3 = (Button) findViewById(R.id.click3);
		mbtn4 = (Button) findViewById(R.id.click4);
		mbtn5 = (Button) findViewById(R.id.click5);
		mView = (ImageView) findViewById(R.id.mimage);
		mContext = this;
		mlists=new ArrayList<MediaInfo>();
		mbtn.setOnClickListener(this);

		mbtn2.setOnClickListener(this);
		mbtn3.setOnClickListener(this);
		mbtn4.setOnClickListener(this);
		mbtn5.setOnClickListener(this);

		mText = (TextView) findViewById(R.id.mtext);
		MediaDatabaseTest();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void MediaDatabaseTest() {
		ContentResolver mContentResolver = this.getContentResolver();
		Cursor mCursor = mContentResolver.query(
				MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null,
				null);
		// if(mCursor.moveToFirst()){
		StringBuffer mBuffer = new StringBuffer();
		String[] mNames = mCursor.getColumnNames();
		for (String name : mNames) {
			mBuffer.append(name + "--");
		}
		mBuffer.append("\n");
		Log.i(TAG, "movetofirst:" + String.valueOf(mCursor.getCount()));
		if (mCursor.moveToNext()) {

			do {
				String mname;
				mname = mCursor.getString(mCursor
						.getColumnIndex(MediaStore.Audio.Media.TITLE));
				mBuffer.append(mname + "--");
				mname = mCursor.getString(mCursor
						.getColumnIndex(MediaStore.Audio.Media.SIZE));
				mBuffer.append(mname + "--");
				mname = mCursor.getString(mCursor
						.getColumnIndex(MediaStore.Audio.Media.MIME_TYPE));
				mBuffer.append(mname + "--");
				mname = mCursor.getString(mCursor
						.getColumnIndex(MediaStore.Audio.Media.DURATION));
				mBuffer.append(mname + "--");
				mname = mCursor.getString(mCursor
						.getColumnIndex(MediaStore.Audio.Media.DATA));
				mBuffer.append(mname + "--");
			} while (mCursor.moveToNext());
		}
		mText.setText(mBuffer.toString());
		// }
	}
	
	public void iniMusic(){
		ContentResolver mContentResolver = mContext.getContentResolver();
		Cursor mCursor = mContentResolver.query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				null);
		// if(mCursor.moveToFirst()){
		StringBuffer mBuffer = new StringBuffer();
		String[] mNames = mCursor.getColumnNames();
		for (String name : mNames) {
			mBuffer.append(name + "--");
		}
		mBuffer.append("\n");
		Log.i(TAG, "movetofirst:" + String.valueOf(mCursor.getCount()));
		if (mCursor.moveToFirst()) {

			do {
				MediaInfo mInfo=new MediaInfo();
				String mname;
				mname = mCursor.getString(mCursor
						.getColumnIndex(MediaStore.Audio.Media.TITLE));
				mBuffer.append(mname + "--");
				mname = mCursor.getString(mCursor
						.getColumnIndex(MediaStore.Audio.Media.DATA));
				mBuffer.append(mname + "--");
				mname = mCursor.getString(mCursor
						.getColumnIndex(MediaStore.Audio.Media.SIZE));
				mBuffer.append(mname + "--");
				mname = mCursor.getString(mCursor
						.getColumnIndex(MediaStore.Audio.Media.DURATION));
				mBuffer.append(mname + "--");
				mname = mCursor.getString(mCursor
						.getColumnIndex(MediaStore.Audio.Media._ID));
				mBuffer.append(mname + "--");
				mBuffer.append("\n");
				mInfo.setTITLE(mCursor.getString(mCursor
						.getColumnIndex(MediaStore.Audio.Media.TITLE)));
				mInfo.setDATA(mCursor.getString(mCursor
						.getColumnIndex(MediaStore.Audio.Media.DATA)));
				mlists.add(mInfo);
			} while (mCursor.moveToNext());
		}
		MediaControl.initMediaPlay(mContext, mlists);
		mText.setText(mBuffer.toString());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.click:
			MediaControl.Previous();
			break;
		case R.id.click2:
            MediaControl.Play(MediaControl.getCurrentLocation());
			break;
		case R.id.click3:
			MediaControl.Pause();
			break;
		case R.id.click4:
			MediaControl.Next();
			break;
		case R.id.click5:
			iniMusic();
			break;
		default:
			break;
		}
	}

}
