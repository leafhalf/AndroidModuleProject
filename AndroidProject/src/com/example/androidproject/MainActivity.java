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

public class MainActivity extends Activity {

	private Button mbtn;
	private Context mContext;
	private Button mbtn2;
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
		mView = (ImageView) findViewById(R.id.mimage);
		mContext = this;
		mlists=new ArrayList<MediaInfo>();
		mbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CustomDialog mCustomDialog = new CustomDialog(mContext,
						new OnCustomDialogListen() {

							@Override
							public void DialogEnd() {
								// TODO Auto-generated method stub

							}

							@Override
							public void DialogBegin() {
								// TODO Auto-generated method stub

							}
						});
				mCustomDialog.setBoardImage(R.drawable.ic_launcher);
				mCustomDialog.setContentView(R.layout.activity_main);
				mCustomDialog.setTittleView(R.layout.activity_main);
				mCustomDialog.setDialogSize(200, 300);
				mCustomDialog.show();
			}
		});

		mbtn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Bitmap mBitmap = BitmapFactory.decodeResource(getResources(),
//						R.drawable.me_fx_icon);
//				Bitmap mbitmap2 = ImageUtils.RoundImage(mBitmap);
//				Bitmap mbitmap3 = ImageUtils.addRoundBoard(mbitmap2, Color.RED,
//						3.0f);
//				// mView.setBackgroundDrawable(new BitmapDrawable(mbitmap2));
//				mView.setImageBitmap(mbitmap3);
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
				MediaControl.Play(0);
				mText.setText(mBuffer.toString());
				
			}
		});

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

}
