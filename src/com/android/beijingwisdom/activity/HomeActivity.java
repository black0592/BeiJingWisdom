package com.android.beijingwisdom.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.android.beijingwisdom.R;
import com.android.beijingwisdom.fragment.BaseFragment;
import com.android.beijingwisdom.fragment.LeftMenuFragment;
import com.android.beijingwisdom.fragment.MainMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


public class HomeActivity extends  SlidingFragmentActivity{
	public static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
	public static final String TAG_MAIN_MENU = "TAG_MAIN_MENU";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home_main);
		
		//����������
		setBehindContentView(R.layout.activity_home_left);
		SlidingMenu slidingMenu = getSlidingMenu();//���slidingmenu����
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//����ȫ������
		slidingMenu.setBehindOffset(400);//������ĻԤ��200����
		initFragment();
	}
	
	private void initFragment(){
		FragmentManager fm = getSupportFragmentManager();//�汾����
		FragmentTransaction transaction = fm.beginTransaction();//��������
		transaction.replace(R.id.fl_main, new MainMenuFragment(),TAG_MAIN_MENU);
		transaction.replace(R.id.fl_left, new LeftMenuFragment(),TAG_LEFT_MENU);
		transaction.commit();//�ύ����
	}
	
	public BaseFragment getMenuFragment(String TAG){
		FragmentManager fm = getSupportFragmentManager();
		BaseFragment fragment =  (BaseFragment) fm.findFragmentByTag(TAG);
		return fragment;
	}
}
