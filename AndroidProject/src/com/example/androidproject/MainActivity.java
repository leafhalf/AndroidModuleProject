package com.example.androidproject;

import com.example.Utils.ApplicationUtils;
import com.example.Utils.ImageUtils;
import com.example.customview.CustomDialog;
import com.example.customview.CustomDialog.OnCustomDialogListen;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Button mbtn;
	private Context mContext;
	private Button mbtn2;
	private ImageView mView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mbtn=(Button)findViewById(R.id.click);
		mbtn2=(Button)findViewById(R.id.click2);
		mView=(ImageView)findViewById(R.id.mimage);
		mContext=this;
		mbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CustomDialog mCustomDialog=new CustomDialog(mContext,new OnCustomDialogListen() {
					
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
				mCustomDialog.setDialogSize(200,300);
				mCustomDialog.show();
			}
		});
		
		mbtn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Bitmap mBitmap=BitmapFactory.decodeResource(getResources(), R.drawable.me_fx_icon);
				Bitmap mbitmap2= ImageUtils.RoundImage(mBitmap);
				Bitmap mbitmap3=ImageUtils.addRoundBoard(mbitmap2, Color.RED,3.0f);
//				mView.setBackgroundDrawable(new BitmapDrawable(mbitmap2));
				mView.setImageBitmap(mbitmap3);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
