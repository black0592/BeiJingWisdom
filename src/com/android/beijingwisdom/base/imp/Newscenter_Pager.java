package com.android.beijingwisdom.base.imp;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.beijingwisdom.base.BasePager;
import com.android.beijingwisdom.domain.NewsMenu;
import com.android.beijingwisdom.global.GlobalConstants;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class Newscenter_Pager extends BasePager {

	public Newscenter_Pager(Activity mActivity) {
		super(mActivity);
		
	}
	
	@Override
	public void initData() {
		// TODO Auto-generated method stub
		TextView view = new TextView(mActivity);
		view.setText("����");
		view.setTextColor(Color.RED);
		view.setGravity(Gravity.CENTER);
		fl_content.addView(view);
		
		tv_title.setText("����");
		//��ʼ��ʱ�����������ȡ������Դ
		getDataFromServer();
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
			}

			private void processJson(String json) {
				Gson gson  = new Gson();
				NewsMenu newdata = gson.fromJson(json, NewsMenu.class);
				System.out.println(newdata.retcode);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				//����ʧ��
				error.printStackTrace();
				Toast.makeText(mActivity, msg, 0).show();
			}
		});
	}
	
}