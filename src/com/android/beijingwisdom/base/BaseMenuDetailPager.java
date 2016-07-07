package com.android.beijingwisdom.base;

import android.app.Activity;
import android.view.View;

public abstract class BaseMenuDetailPager {
	protected Activity mActivity;
	public View mRootView;
	public BaseMenuDetailPager(Activity activity) {
		this.mActivity = activity;
		mRootView = initView();
	}
	//��ʼ�������������ʵ��
	public abstract View initView();
	//��ʼ������
	public void initData(){
		
	}
}
