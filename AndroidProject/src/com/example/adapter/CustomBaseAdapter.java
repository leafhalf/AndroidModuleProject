package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/**
 * base adapter
 * @author Lin
 *
 */
public class CustomBaseAdapter extends BaseAdapter {

	private Context mContext=null;
	private LayoutInflater mInflater = null; 
	private List<?> mItemInfos;
	
	
	public CustomBaseAdapter(Context context, List<?> itemInfos) {
		super();		
		this.mContext = context;
		this.mItemInfos = itemInfos;
		mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(mItemInfos!=null){
			return mItemInfos.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		if(mItemInfos!=null){
			return mItemInfos.get(arg0);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected View getView(int LAYOUT_ID){
		return mInflater.inflate(LAYOUT_ID, null);
	}

}
