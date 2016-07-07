package com.android.beijingwisdom.base.menu.imp;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.android.beijingwisdom.base.BaseMenuDetailPager;
/**
 * ��������ҳ
 * @author feng
 *
 */
public class TopicMenuDetail extends BaseMenuDetailPager {

	public TopicMenuDetail(Activity activity) {
		super(activity);
		
	}

	@Override
	public View initView() {
		TextView view = new TextView(mActivity);
		view.setText("��������ҳ");
		view.setTextColor(Color.RED);
		view.setGravity(Gravity.CENTER);
		return view;
	}

}
