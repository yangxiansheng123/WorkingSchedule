package com.retrofit.workingschedule.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.retrofit.workingschedule.R;
import com.retrofit.workingschedule.bean.IsChecked;

import java.util.List;

public class AddTimeAdapter extends BaseAdapter {

	private Context mcontext;
	private LayoutInflater mInflater;
	private String opType;
	private List<IsChecked> listData ;
	public AddTimeAdapter(Activity activity) {
		super();
		this.mcontext = activity;
		this.mInflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setListData(List<IsChecked> listData) {
		this.listData = listData;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}


	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.outpatient_pattern, null);
			holder.one = (ImageView) convertView.findViewById(R.id.one_day);
			holder.tv_am_pm_night = (TextView) convertView.findViewById(R.id.tv_am_pm_night);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (position%3==0){
			holder.tv_am_pm_night.setText(mcontext.getString(R.string.am));
			if (listData.get(position).isChecked()){
				holder.one.setImageResource(R.mipmap.ico_am);

			}else{
				holder.one.setImageResource(R.mipmap.ico_seltime);
			}
		}else if (position%3==1){
			holder.tv_am_pm_night.setText(mcontext.getString(R.string.pm));
			if (listData.get(position).isChecked()){
				holder.one.setImageResource(R.mipmap.ico_pm);

			}else{
				holder.one.setImageResource(R.mipmap.ico_seltime);
			}
		}else if (position%3==2){
			if (listData.get(position).isChecked()){
				holder.one.setImageResource(R.mipmap.ico_night);

			}else{
				holder.one.setImageResource(R.mipmap.ico_seltime);
			}
			holder.tv_am_pm_night.setText(mcontext.getString(R.string.night));
		}

		return convertView;
	}

	public static class ViewHolder {
		public ImageView one;
		private TextView tv_am_pm_night;
	}



}
