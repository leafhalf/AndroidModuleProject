package com.example.customview;

import com.example.Utils.ApplicationUtils;
import com.example.androidproject.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

/**
 * a base dialog with background.there are two part for it,one is for
 * titlle,another is for content. the developer could set the tittle show or
 * not,or show text or custom layout.by the way,the developer also could set the
 * content with text or custom layout.
 * 
 * @author Lin
 * 
 */
public class CustomDialog extends Dialog {

	private LinearLayout llDialog;
	private LinearLayout llTittle;
	private LinearLayout llContent;
	private Context mContext;
	private OnCustomDialogListen mCustomDialogListen;
	private Boolean IS_WITH_TITTLE = false;

	private View TittleView;
	private View MainView;
	private Drawable BoardImage;
	private View SplitLine;

	private int mwidth;
	private int mheigth;

	private String TAG = "CustomDialog";

	/**
	 * 
	 * @param context
	 * @param DIALOG_MODE
	 *            set the dialog mode,DIALOG_WITH_TITTLE or
	 *            DIALOG_WITH_NO_TITTLE
	 * @param onCustomDialogListen
	 *            set the dialog operation listen
	 */
	public CustomDialog(Context context,
			OnCustomDialogListen onCustomDialogListen) {
		super(context);
		// TODO Auto-generated constructor stub
		mContext = context;
		mCustomDialogListen = onCustomDialogListen;

		llDialog = new LinearLayout(mContext);
		llDialog.setOrientation(LinearLayout.VERTICAL);

		// title
		llTittle = new LinearLayout(mContext);
		llTittle.setOrientation(LinearLayout.VERTICAL);

		// content
		llContent = new LinearLayout(mContext);
		llContent.setOrientation(LinearLayout.VERTICAL);

	}

	public interface OnCustomDialogListen {
		void DialogBegin();

		void DialogEnd();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		LinearLayout.LayoutParams mLinearParams = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		if (mheigth > 0 && mwidth > 0) {
			mLinearParams.height = mheigth;
			mLinearParams.width = mwidth;
		}
		llDialog.setLayoutParams(mLinearParams);
		
		//add the dialog board
		if (BoardImage != null) {
			llDialog.setBackgroundDrawable(BoardImage);
		} else {
			llDialog.setBackgroundResource(R.drawable.custom_dialog_bg);
		}

		//add the dialog tittle view
		try {
			llTittle.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));      
			llTittle.addView(TittleView);
			llDialog.addView(llTittle);
			llDialog.addView(SplitLine);
		} catch (Exception e) {
			llTittle.setVisibility(View.GONE);
			Log.i(TAG,"you can set the dialog tittle or splitLine");
		}

		//add the dialog main view
		try {
			llContent.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			llContent.addView(MainView);
			llDialog.addView(llContent);
		} catch (Exception e) {
			llContent.setVisibility(View.GONE);
			Log.i(TAG, "you can set the dialog main view");			
		}

		mCustomDialogListen.DialogBegin();
		setContentView(llDialog);
		super.setContentView(llDialog);
		super.onCreate(savedInstanceState);

	}

	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		try{
		llDialog.addView(View.inflate(mContext, layoutResID, null));
		}catch(Exception e){
			Log.i(TAG, "input an error content layout ID");
		} 
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub
		mCustomDialogListen.DialogEnd();
		super.cancel();
	}

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		mCustomDialogListen.DialogEnd();
		super.dismiss();
	}

	/**
	 * set the board view
	 * @param boardDrawable
	 */
	public void setBoardImage(Drawable boardDrawable) {
		BoardImage = boardDrawable;
	}

	public void setBoardImage(int DRAWABLE_ID) {
		BoardImage = mContext.getResources().getDrawable(DRAWABLE_ID);
	}

	public int getWidth() {
		return mwidth;
	}

	public void setDialogSize(int width, int heigth) {
		this.mwidth = ApplicationUtils.dip2px(mContext, width);
		this.mheigth = ApplicationUtils.dip2px(mContext, heigth);
	}

	public int getHeigth() {
		return mheigth;
	}

	public View getMainView() {
		return MainView;
	}

	public void setMainView(View mainView) {
		MainView = mainView;
	}

	public View getTittleView() {
		return TittleView;
	}

	public void setTittleView(View tittleView) {
		TittleView = tittleView;
	}

	/**
	 * through the layout ID to build the tittle View
	 * 
	 * @param TITTLE_LAYOUT
	 */
	public void setTittleView(int LAYOUT_ID) {
		try {
			View view = View.inflate(mContext, LAYOUT_ID, null);
			this.TittleView = view;
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}
	}

	public void setMainView(int LAYOUT_ID) {
		try {
			View view = View.inflate(mContext, LAYOUT_ID, null);
			this.MainView = view;
		} catch (Exception e) {
			Log.i(TAG, e.getMessage());
		}
	}
	
	public View getDialogView(){
		return llDialog;
				
	}

	public View getSplitLine() {
		return SplitLine;
	}

	public void setSplitLine(View splitLine) {
		SplitLine = splitLine;
	}
	


}
