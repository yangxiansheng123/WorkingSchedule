package com.retrofit.workingschedule.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.retrofit.workingschedule.R;

import java.util.List;

public class ShowListAdapter extends BaseAdapter {

	private Context mcontext;
	private LayoutInflater mInflater;
	private String opType;
	private List<String[]> listData ;
	public ShowListAdapter(Activity activity) {
		super();
		this.mcontext = activity;
		this.mInflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setList(List<String[]> list) {
		this.listData = list;
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
			convertView = mInflater.inflate(R.layout.clinc_day_pattern, null);
			holder.one = (TextView) convertView.findViewById(R.id.one_day);
			holder.two = (TextView) convertView.findViewById(R.id.two_day);
			holder.three = (TextView) convertView.findViewById(R.id.three_day);
			holder.four = (TextView) convertView.findViewById(R.id.four_day);
			holder.five = (TextView) convertView.findViewById(R.id.five_day);
			holder.six = (TextView) convertView.findViewById(R.id.six_day);
			holder.seven = (TextView) convertView.findViewById(R.id.seven_day);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		String monday_plan  = listData.get(position)[0];
		String tuesday_plan  = listData.get(position)[1];
		String wednesday_plan  = listData.get(position)[2];
		String thursday_plan  = listData.get(position)[3];
		String friday_plan  = listData.get(position)[4];
		String saturday_plan  = listData.get(position)[5];
		String sunday_plan  = listData.get(position)[6];
		if (monday_plan.equals("0")){
			holder.one.setBackgroundResource(R.mipmap.btn_blockblue);
			holder.one.setText(mcontext.getString(R.string.am));
		}else if (monday_plan.equals("1")){
			holder.one.setBackgroundResource(R.mipmap.btn_blockbrown);
			holder.one.setText(mcontext.getString(R.string.pm));
		}else if (monday_plan.equals("2")){
			holder.one.setBackgroundResource(R.mipmap.btn_blocknight);
			holder.one.setText(mcontext.getString(R.string.night));
		}else if (monday_plan.equals("4")){
			holder.one.setText("");
		}else {
		}

		if (tuesday_plan.equals("0")){
			holder.two.setBackgroundResource(R.mipmap.btn_blockblue);
			holder.two.setText(mcontext.getString(R.string.am));
		}else if (tuesday_plan.equals("1")){
			holder.two.setBackgroundResource(R.mipmap.btn_blockbrown);
			holder.two.setText(mcontext.getString(R.string.pm));
		}else if (tuesday_plan.equals("2")){
			holder.two.setBackgroundResource(R.mipmap.btn_blocknight);
			holder.two.setText(mcontext.getString(R.string.night));
		}else {

		}

		if (wednesday_plan.equals("0")){
			holder.three.setBackgroundResource(R.mipmap.btn_blockblue);
			holder.three.setText(mcontext.getString(R.string.am));
		}else if (wednesday_plan.equals("1")){
			holder.three.setBackgroundResource(R.mipmap.btn_blockbrown);
			holder.three.setText(mcontext.getString(R.string.pm));
		}else if (wednesday_plan.equals("2")){
			holder.three.setBackgroundResource(R.mipmap.btn_blocknight);
			holder.three.setText(mcontext.getString(R.string.night));
		}else {

		}
		if (thursday_plan.equals("0")){
			holder.four.setBackgroundResource(R.mipmap.btn_blockblue);
			holder.four.setText(mcontext.getString(R.string.am));
		}else if (thursday_plan.equals("1")){
			holder.four.setBackgroundResource(R.mipmap.btn_blockbrown);
			holder.four.setText(mcontext.getString(R.string.pm));
		}else if (thursday_plan.equals("2")){
			holder.four.setBackgroundResource(R.mipmap.btn_blocknight);
			holder.four.setText(mcontext.getString(R.string.night));
		}else {

		}
		if (friday_plan.equals("0")){
			holder.five.setBackgroundResource(R.mipmap.btn_blockblue);
			holder.five.setText(mcontext.getString(R.string.am));
		}else if (friday_plan.equals("1")){
			holder.five.setBackgroundResource(R.mipmap.btn_blockbrown);
			holder.five.setText(mcontext.getString(R.string.pm));
		}else if (friday_plan.equals("2")){
			holder.five.setBackgroundResource(R.mipmap.btn_blocknight);
			holder.five.setText(mcontext.getString(R.string.night));
		}else {

		}
		if (saturday_plan.equals("0")){
			holder.six.setBackgroundResource(R.mipmap.btn_blockblue);
			holder.six.setText(mcontext.getString(R.string.am));
		}else if (saturday_plan.equals("1")){
			holder.six.setBackgroundResource(R.mipmap.btn_blockbrown);
			holder.six.setText(mcontext.getString(R.string.pm));
		}else if (saturday_plan.equals("2")){
			holder.six.setBackgroundResource(R.mipmap.btn_blocknight);
			holder.six.setText(mcontext.getString(R.string.night));
		}else {

		}
		if (sunday_plan.equals("0")){
			holder.seven.setBackgroundResource(R.mipmap.btn_blockblue);
			holder.seven.setText(mcontext.getString(R.string.am));
		}else if (sunday_plan.equals("1")){
			holder.seven.setBackgroundResource(R.mipmap.btn_blockbrown);
			holder.seven.setText(mcontext.getString(R.string.pm));
		}else if (sunday_plan.equals("2")){
			holder.seven.setBackgroundResource(R.mipmap.btn_blocknight);
			holder.seven.setText(mcontext.getString(R.string.night));
		}else {

		}

		return convertView;
	}

	private class ViewHolder {
		private TextView one, two, three, four, five, six, seven;
	}



}
