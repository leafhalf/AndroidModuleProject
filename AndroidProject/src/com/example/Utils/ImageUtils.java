package com.example.Utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ImageUtils {
	/**
	 * make an bitmap become round and return an round bitmap
	 * 
	 * @param bitmap
	 * 
	 * @return
	 */
	public static Bitmap RoundImage(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		// draw board
		canvas.drawCircle(bitmap.getWidth() / 2.0f, bitmap.getHeight() / 2.0f,
				bitmap.getWidth() / 2.0f-2, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_ATOP));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		
		return output;
	}
	
	/**
	 * add an round board to the bitmap
	 * @param bitmap
	 * @param color
	 * @param boardWidth
	 * @return
	 */
	public static Bitmap addRoundBoard(Bitmap bitmap,int color,float boardWidth){
		Canvas mCanvas=new Canvas(bitmap);
		mCanvas.drawARGB(0, 0, 0, 0);
		final Rect mrect=new Rect(0, 0, bitmap.getWidth(),bitmap.getHeight());
		final RectF mRectF=new RectF(mrect);
		Paint mPaint=new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(color);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setFilterBitmap(true);
		mPaint.setDither(true);
		mPaint.setStrokeWidth(boardWidth);
		mCanvas.drawCircle(bitmap.getWidth()/2.0f,bitmap.getHeight()/2.0f,bitmap.getWidth()/2.0f-0.3f-boardWidth, mPaint);
		return bitmap;
	}
	
	/**
	 * flex the bitmap
	 * @param bitmap
	 * @param widthRatio
	 * flex the ratio with width
	 * @param heigthRatio
	 * flex the ratio witj heigth
	 * @return
	 */
	public static Bitmap fixTheView(Bitmap bitmap,float widthRatio,float heigthRatio){
		  Matrix matrix = new Matrix();
		  matrix.postScale(widthRatio,heigthRatio); //长和宽放大缩小的比例
		  bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		return bitmap;
	}
	
	public static Bitmap drawable2Bitmap(Drawable drawable){
		return  ((BitmapDrawable)drawable).getBitmap();
	}
	
	public static Drawable bitmap2Drawable(Bitmap bitmap){
		return new BitmapDrawable(bitmap);
	}
	
}
