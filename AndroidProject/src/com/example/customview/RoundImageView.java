package com.example.customview;

import com.example.Utils.ApplicationUtils;
import com.example.Utils.ImageUtils;
import com.example.androidproject.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
/**
 * an class which is to make the ImageView to be round,special for the  TransitionDrawable which is
 * known indirect subclasses.and this class is only can use to bitmap which is .png,.jpg or .gif.
 * pay attention that is not support to change the background image to round.if you want to set the background 
 * to be round,please try use the API RoundImage which is in the ImageUtils,it will make you bitmap become an round 
 * bitmap to return. 
 * @author Lin
 *
 */
public class RoundImageView extends ImageView {

    private Context mContext;  
    private int defaultWidth = 0;  
    private int defaultHeight = 0;  
    
    private int boardColor=0x00000000;
    private float boardWidth=2.0f;
    
    private String TAG="RoundImageView";
  
    public RoundImageView(Context context) {  
        super(context);  
        mContext = context;  
    }  
  
    public RoundImageView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        mContext = context;  
        setCustomAttributes(attrs);  
    }  
  
    public RoundImageView(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
        mContext = context;  
        setCustomAttributes(attrs);  
    }  
  
	private void setCustomAttributes(AttributeSet attrs) {  
        TypedArray typeArray = mContext.obtainStyledAttributes(attrs,R.styleable.RoundImageView);  
        boardColor =typeArray.getColor(R.styleable.RoundImageView_Board_Color,0x00000000);
        boardWidth =typeArray.getFloat(R.styleable.RoundImageView_Board_Width,1.0f); 
        typeArray.recycle();
    }  
  
    @Override  
    protected void onDraw(Canvas canvas) {  
        Drawable drawable = getDrawable();  
        if (drawable == null) {  
        	Log.i(TAG,"drawable");
            return;  
        }  
        
  
        if (getWidth() == 0 || getHeight() == 0) {  
            return;  
        }  
        this.measure(0, 0); 
        setDrawingCacheEnabled(true);
        Bitmap bitmap=null; 
        if (drawable.getClass() == NinePatchDrawable.class)  
            return;  
        else if(drawable instanceof BitmapDrawable){
        	bitmap = ((BitmapDrawable) drawable).getBitmap(); 
        }else if(drawable instanceof TransitionDrawable){
        	int layer=((TransitionDrawable)drawable).getNumberOfLayers();
        	Log.i(TAG, "hshsh="+String.valueOf(layer)+String.valueOf(((TransitionDrawable)drawable).getDrawable(0))+","+String.valueOf(((TransitionDrawable)drawable).getDrawable(1)));
        	/**
        	 * if the second drawable is the main or last drawable, get the second drawble to draw
        	 */
        	Drawable sDrawable=((TransitionDrawable)drawable).getDrawable(1);
        	bitmap=((BitmapDrawable) sDrawable).getBitmap(); 
        }else{
        	bitmap=getDrawingCache();
        }
        
        if(bitmap!=null){ 
        Bitmap bitmapCopy = bitmap.copy(Bitmap.Config.ARGB_8888, true);  
        Log.i(TAG,"bitmap:"+String.valueOf(bitmapCopy.getWidth())+"=="+String.valueOf(bitmapCopy.getHeight()));
        if (defaultWidth == 0) {  
            defaultWidth = getWidth();  
            Log.i(TAG,"getWidth:"+String.valueOf(getWidth()));
        }  
        if (defaultHeight == 0) {  
            defaultHeight = getHeight();  
            Log.i(TAG,"getHeight:"+String.valueOf( getHeight()));
        } 
        
        float biliwidth=defaultWidth/((float)bitmap.getWidth());
        float biliHeigth=defaultHeight/((float)bitmap.getHeight());
        bitmapCopy=ImageUtils.fixTheView(bitmapCopy,biliwidth,biliHeigth);
        
        canvas.drawARGB(0, 0, 0, 0);
        Rect mRect=new Rect(0, 0,getWidth(), getHeight());
        bitmapCopy=ImageUtils.RoundImage(bitmapCopy);
        bitmapCopy=ImageUtils.addRoundBoard(bitmapCopy, boardColor, boardWidth);
        Paint mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setDither(true);
        canvas.drawBitmap(bitmapCopy, mRect, mRect, mPaint);
        bitmap=null;
        bitmapCopy=null;
        }
        else{
        	Log.i(TAG,"bitmap=null");
        }
        setDrawingCacheEnabled(false);

    }  

}
