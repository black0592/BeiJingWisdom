package com.android.beijingwisdom.base.imp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.AvoidXfermode.Mode;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.beijingwisdom.activity.HomeActivity;
import com.android.beijingwisdom.base.BaseMenuDetailPager;
import com.android.beijingwisdom.base.BasePager;
import com.android.beijingwisdom.base.menu.imp.InteractMenuDetail;
import com.android.beijingwisdom.base.menu.imp.NewsMenuDetail;
import com.android.beijingwisdom.base.menu.imp.PhotosMenuDetail;
import com.android.beijingwisdom.base.menu.imp.TopicMenuDetail;
import com.android.beijingwisdom.domain.NewsMenu;
import com.android.beijingwisdom.domain.NewsMenu.NewsLeftMenu;
import com.android.beijingwisdom.fragment.LeftMenuFragment;
import com.android.beijingwisdom.global.GlobalConstants;
import com.android.beijingwisdom.utils.CacheUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class Newscenter_Pager extends BasePager {
	private static final String TAG = "Newscenter_Pager";
	public SharedPreferences sp;
	private ArrayList<BaseMenuDetailPager> menuDetails;//����ҳ����ļ���
	private  ArrayList<NewsLeftMenu> itemData;
	public Newscenter_Pager(Activity mActivity) {
		super(mActivity);
		
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
		
		sp = mActivity.getSharedPreferences("config", mActivity.MODE_PRIVATE);
		//�ж��Ƿ��л���
		String cache = CacheUtils.getCache(GlobalConstants.CATEGORY_URL, sp);
		if(TextUtils.isEmpty(cache)){
			//��ʼ��ʱ�����������ȡ������Դ
			getDataFromServer();
		}else{
			//���л��棬ֱ�Ӵ�������
			System.out.println("��ȡ����ֱ�Ӽ��ء�������");
			processJson(cache);
		}
		
	}
	/**
	 * �ӷ�������ȡ����
	 */
	private void getDataFromServer() {
		HttpUtils utils = new HttpUtils();
		utils.send(HttpMethod.GET, GlobalConstants.CATEGORY_URL, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				//����ɹ�
				String result = responseInfo.result;
				System.out.println(result);
				processJson(result);
				CacheUtils.setCache(sp, GlobalConstants.CATEGORY_URL, result);
			}

			

			@Override
			public void onFailure(HttpException error, String msg) {
				//����ʧ��
				error.printStackTrace();
				Toast.makeText(mActivity, msg, 0).show();
			}
		});
		
	}
	protected void processJson(String json) {
		Gson gson  = new Gson();
		NewsMenu newdata = gson.fromJson(json, NewsMenu.class);
		System.out.println(newdata.retcode);
		
		HomeActivity mainUI = (HomeActivity) mActivity;
		LeftMenuFragment leftfragment = (LeftMenuFragment) mainUI.getMenuFragment(mainUI.TAG_LEFT_MENU);
		//���������������
		itemData = newdata.data;
		leftfragment.setMenuData(itemData);
		//��ʼ������ҳ����
		menuDetails = new ArrayList<BaseMenuDetailPager>();
		menuDetails.add(new NewsMenuDetail(mActivity,itemData.get(0).children));
		
//		Log.i(TAG,itemData.get(0).children.toString());
		
		menuDetails.add(new TopicMenuDetail(mActivity));
		menuDetails.add(new PhotosMenuDetail(mActivity));
		menuDetails.add(new InteractMenuDetail(mActivity));
		//Ĭ�ϼ���������������ҳ
		setCurrentDetailPager(0);
		
	}
	//���ò˵�����ҳ
	public void  setCurrentDetailPager(int position){
		//���¸�framelayout�������
		BaseMenuDetailPager detailpager = menuDetails.get(position);
		View view = detailpager.mRootView;
		//���֮ǰ��ӵĲ���
		fl_content.removeAllViews();
		fl_content.addView(view);
		//��ʼ��ҳ������
		detailpager.initData();
		//���±���
		tv_title.setText(itemData.get(position).title);
	}
}